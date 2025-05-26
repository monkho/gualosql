package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeGenerated extends Application {
    private VBox layout;
    private BDTabla table;
    private List<BDCampo> campos;
    private TableView<List<String>> tableContent = new TableView<>();

    public CodeGenerated() {}

    public CodeGenerated(BDTabla table) {
        layout = new VBox(10);
        layout.setPadding(new Insets(20));

        this.table = table;
        this.campos = this.table.getCampos();

        setupTable();
        loadTableData();
        
        layout.getChildren().add(tableContent);
    }

    private void setupTable() {
        // Limpiar columnas existentes
        tableContent.getColumns().clear();
        
        // Crear columnas para cada campo
        for(int i = 0; i < campos.size(); i++) {
            BDCampo campo = campos.get(i);
            TableColumn<List<String>, String> column = new TableColumn<>(campo.getNombreCampo());
            
            // Configurar el valor de la celda
            final int columnIndex = i;
            column.setCellValueFactory(cellData -> {
                List<String> row = cellData.getValue();
                if (row != null && columnIndex < row.size()) {
                    return new SimpleStringProperty(row.get(columnIndex));
                } else {
                    return new SimpleStringProperty("");
                }
            });
            
            column.setPrefWidth(150); // Ancho por defecto
            tableContent.getColumns().add(column);
        }
    }

    private void loadTableData() {
        // Limpiar datos existentes
        tableContent.getItems().clear();
        
        if (campos.isEmpty()) {
            return;
        }

        // Determinar el número máximo de filas
        int maxRows = 0;
        for (BDCampo campo : campos) {
            if (campo.getValores() != null) {
                maxRows = Math.max(maxRows, campo.getValores().size());
            }
        }

        // Crear filas de datos
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            List<String> row = new ArrayList<>();
            
            for (BDCampo campo : campos) {
                if (campo.getValores() != null && rowIndex < campo.getValores().size()) {
                    row.add(campo.getValores().get(rowIndex));
                } else {
                    row.add(""); // Celda vacía si no hay valor
                }
            }
            
            tableContent.getItems().add(row);
        }
    }
    
    public void refreshTable() {
        loadTableData();
    }

    public void newField(BDTabla table, Stage stage) {
        Values valuesWindow = new Values(table);
        valuesWindow.setOnCloseCallback(() -> refreshTable());
        try {
            valuesWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editField(BDTabla table, int rowIndex, Stage stage) {
        List<String> currentValues = new ArrayList<>();
        
        for (BDCampo campo : campos) {
            if (campo.getValores() != null && rowIndex < campo.getValores().size()) {
                currentValues.add(campo.getValores().get(rowIndex));
            } else {
                currentValues.add("");
            }
        }
        
        Values valuesWindow = new Values(table, currentValues, rowIndex);
        valuesWindow.setOnCloseCallback(() -> refreshTable());
        try {
            valuesWindow.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @Override
    public void start(Stage primaryStage) throws Exception {
        ButtonBar buttons = new ButtonBar();
        Button newButton = new Button("Insertar");
        Button editButton = new Button("Editar");
        Button refreshButton = new Button("Refrescar");

        editButton.setDisable(true);
        buttons.getButtons().addAll(newButton, editButton, refreshButton);

        newButton.setOnAction((_) -> {
            newField(table, primaryStage);
        });

        // Configurar menú contextual
        ContextMenu contextMenu = new ContextMenu();
        MenuItem addValue = new MenuItem("Agregar registro");
        MenuItem removeValue = new MenuItem("Eliminar");
        MenuItem editValue = new MenuItem("Editar");
        
        contextMenu.getItems().addAll(addValue, editValue, removeValue);
        
        addValue.setOnAction(_ -> {
            newField(table, primaryStage);
        });
        
        editValue.setOnAction(_ -> {
            List<String> selectedItem = tableContent.getSelectionModel().getSelectedItem();
            int selectedIndex = tableContent.getSelectionModel().getSelectedIndex();
            
            if (selectedItem != null && selectedIndex >= 0) {
                editField(table, selectedIndex, primaryStage);
            } else {
                System.out.println("No hay ninguna fila seleccionada para editar");
            }
        });
        
        removeValue.setOnAction(_ -> {
            int selectedIndex = tableContent.getSelectionModel().getSelectedIndex();
            
            if (selectedIndex >= 0) {
                // Eliminar valores de todos los campos en el índice seleccionado
                for (BDCampo campo : campos) {
                    if (campo.getValores() != null && selectedIndex < campo.getValores().size()) {
                        campo.getValores().remove(selectedIndex);
                    }
                }
                // Refrescar la tabla
                loadTableData();
                System.out.println("Registro eliminado en índice: " + selectedIndex);
            } else {
                System.out.println("No hay ninguna fila seleccionada para eliminar");
            }
            refreshTable();
        });
        
        // Asignar el menú contextual a la tabla
        tableContent.setContextMenu(contextMenu);
        
        refreshButton.setOnAction((_) -> {
            System.out.println("Refrescando tabla...");
            
            // Mostrar valores actuales en consola para debug
            for(int i = 0; i < campos.size(); i++) {
                BDCampo c = campos.get(i);
                System.out.println("Campo: " + c.getNombreCampo());
                
                if(c.getValores() != null && c.getValores().size() > 0) {
                    c.getValores().forEach(v -> {
                        System.out.println("\t\tValor: " + v);
                    });
                } else {
                    System.out.println("\t\tSin valores");
                }
            }
            
            // Recargar los datos de la tabla
            loadTableData();
            System.out.println("Tabla refrescada. Filas: " + tableContent.getItems().size());
        });

        layout.getChildren().add(buttons);

        Scene scene = new Scene(layout, 900, 500);
        primaryStage.setTitle("Tabla: " + table.getNombreTabla());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}