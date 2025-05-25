package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

//import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Application;
import javafx.css.converter.StringConverter;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Values extends Application {
	private VBox layout;
	
	private BDTabla tabla;
	
	public Values(BDTabla tabla) {
		layout = new VBox(10);
        layout.setPadding(new Insets(20));
        
		this.tabla = tabla;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		for(BDCampo c : tabla.getCampos()) {
			Object inputField = null;
			layout.getChildren().addAll(new Label(c.getNombreCampo()));
			switch(c.getNombreTipo()) {
				case "PRIMARY KEY AUTOINCREMENT INT NOT NULL":
					inputField = new Spinner<>(0, 999999, 1);
					((Spinner) inputField).setEditable(true);
					layout.getChildren().add((Node) inputField);
					break;
				case "texto":
					inputField = new TextField();
					layout.getChildren().add((TextField) inputField);
					break;
				case "numero":
					inputField = new Spinner<>(0, 999999, 1);
					((Spinner) inputField).setEditable(true);
					layout.getChildren().add((Spinner) inputField);
					break;
				case "fecha":
					inputField = new DatePicker();
					layout.getChildren().add((DatePicker) inputField);
					break;
				case "tiempo":
					inputField = new TextField();
					layout.getChildren().add((TextField) inputField);
					break;
				default:
					System.out.println("Error, tipo: " + c.getNombreTipo() + " no encontrado");
					break;
			}
			
		}
		
		Button acceptButton = new Button("Aceptar");
		acceptButton.setOnAction((_) -> {
			List<BDCampo> campos = tabla.getCampos();
			String values = "Valores agregados:\n";
			for(int i=0; i<campos.size(); i++) {
				Node input = layout.getChildren().get(i);
				
				if(input instanceof TextField) {
					String v = ((TextField) input).getText().toString();
					values += "\tTexto: " + v;
					campos.get(i).addValor(v);
				}
				else if(input instanceof DatePicker) {
					String v = ((DatePicker) input).getValue().toString();
					values += "\tFecha: " + v;
					campos.get(i).addValor(v);
				}
				else if(input instanceof Spinner) {
					String v = ((Spinner) input).getValue().toString();
					values += "\tNumero: " + v;
					campos.get(i).addValor(v);
				}
				values += "\n";
			}
			System.out.println(values);
			primaryStage.close();
		});
		
		
		layout.getChildren().add(acceptButton);
		
		Scene scene = new Scene(layout, 600, 400);
		primaryStage.setTitle("Valores");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
