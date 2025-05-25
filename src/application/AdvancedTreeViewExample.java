package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdvancedTreeViewExample extends Application {
    
    // Custom class for tree items
    static class Person {
        private String name;
        private String role;
        
        public Person(String name, String role) {
            this.name = name;
            this.role = role;
        }
        
        @Override
        public String toString() {
            return name + " (" + role + ")";
        }
        
        // Getters
        public String getName() { return name; }
        public String getRole() { return role; }
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage primaryStage) {
        // Create root with custom object
        TreeItem<Person> rootItem = new TreeItem<>(new Person("Company", "Organization"));
        rootItem.setExpanded(true);
        
        // Create departments
        TreeItem<Person> engineering = new TreeItem<>(new Person("Engineering", "Department"));
        TreeItem<Person> marketing = new TreeItem<>(new Person("Marketing", "Department"));
        TreeItem<Person> hr = new TreeItem<>(new Person("Human Resources", "Department"));
        
        // Add employees to engineering
        engineering.getChildren().addAll(
            new TreeItem<>(new Person("Alice Johnson", "Senior Developer")),
            new TreeItem<>(new Person("Bob Smith", "Junior Developer")),
            new TreeItem<>(new Person("Carol Brown", "Tech Lead"))
        );
        
        // Add employees to marketing
        marketing.getChildren().addAll(
            new TreeItem<>(new Person("David Wilson", "Marketing Manager")),
            new TreeItem<>(new Person("Eve Davis", "Content Creator"))
        );
        
        // Add employees to HR
        hr.getChildren().addAll(
            new TreeItem<>(new Person("Frank Miller", "HR Manager")),
            new TreeItem<>(new Person("Grace Lee", "Recruiter"))
        );
        
        // Add departments to root
        rootItem.getChildren().addAll(engineering, marketing, hr);
        
        // Create TreeView
        TreeView<Person> treeView = new TreeView<>(rootItem);
        
        // Add selection listener
        treeView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    Person person = newValue.getValue();
                    System.out.println("Selected: " + person.getName() + " - " + person.getRole());
                }
            }
        );
        
        // Create context menu
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addItem = new MenuItem("Add Employee");
        MenuItem removeItem = new MenuItem("Remove");
        MenuItem editItem = new MenuItem("Edit");
        
        addItem.setOnAction(e -> {
            TreeItem<Person> selected = treeView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TreeItem<Person> newEmployee = new TreeItem<>(
                    new Person("New Employee", "Employee")
                );
                selected.getChildren().add(newEmployee);
                selected.setExpanded(true);
            }
        });
        
        removeItem.setOnAction(e -> {
            TreeItem<Person> selected = treeView.getSelectionModel().getSelectedItem();
            if (selected != null && selected.getParent() != null) {
                selected.getParent().getChildren().remove(selected);
            }
        });
        
        editItem.setOnAction(e -> {
            TreeItem<Person> selected = treeView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog(selected.getValue().getName());
                dialog.setTitle("Edit Employee");
                dialog.setHeaderText("Edit employee name:");
                dialog.showAndWait().ifPresent(name -> {
                    Person oldPerson = selected.getValue();
                    selected.setValue(new Person(name, oldPerson.getRole()));
                });
            }
        });
        
        contextMenu.getItems().addAll(addItem, removeItem, editItem);
        treeView.setContextMenu(contextMenu);
        
        // Add double-click handler
        treeView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<Person> selected = treeView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Employee Details");
                    alert.setHeaderText(null);
                    Person person = selected.getValue();
                    alert.setContentText("Name: " + person.getName() + "\nRole: " + person.getRole());
                    alert.showAndWait();
                }
            }
        });
        
        // Create label to show selection
        Label selectionLabel = new Label("Select an item from the tree");
        treeView.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectionLabel.setText("Selected: " + newValue.getValue().toString());
                } else {
                    selectionLabel.setText("No selection");
                }
            }
        );
        
        // Layout
        VBox root = new VBox(10);
        root.getChildren().addAll(
            new Label("Company Organization Chart:"),
            treeView,
            selectionLabel,
            new Label("Right-click for context menu, double-click for details")
        );
        
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Advanced TreeView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}