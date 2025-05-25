package application;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CodeGenerated extends Application {
	private VBox layout;
//	private BaseDatos db;
	private BDTabla table;
	private List<BDCampo> campos;
	
	private TableView<Object> tableContent = new TableView<>();
	
	public CodeGenerated() {}
	
	public CodeGenerated(BDTabla table) {
		layout = new VBox(10);
        layout.setPadding(new Insets(20));
        
//		this.db = db;
		this.table = table;
		this.campos = this.table.getCampos();
		
		for(BDCampo c : campos) {
			TableColumn<Object, Object> campo = new TableColumn<>(c.getNombreCampo());
			tableContent.getColumns().add(campo);
			if(c.getValores() != null) {
				for(String v : c.getValores()) {
					tableContent.getItems().add(v);
				}
			}
		}
		layout.getChildren().add(tableContent);
	}
	
	public void newField(BDTabla table, Stage stage) {
		Values valuesWindow = new Values(table);
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
        
        refreshButton.setOnAction((_) -> {
        	for(int i=0; i<campos.size(); i++) {
        		BDCampo c = campos.get(i);

        		System.out.println("Nombre del campo: " + c.getNombreCampo());
				
        		if(c.getValores().size() > 0) {
        			c.getValores().forEach(v -> {
        				System.out.println("\t\tValor: " + v);
        				tableContent.getItems().add(v);
        			});
        		}
    		}

        });
        
        layout.getChildren().add(buttons);
        
        Scene scene = new Scene(layout, 900, 500);
        
        primaryStage.setTitle("Tabla: " + table.getNombreTabla());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
