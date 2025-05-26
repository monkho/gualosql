package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Values extends Application {
    private VBox layout;
    private BaseDatos db;
    private BDTabla tabla;
    
    // Lista para mantener referencia a los campos de entrada
    private List<Object> inputFields;
    
    // Variables para modo edición
    private boolean isEditMode = false;
    private List<String> currentValues;
    private int editRowIndex;

    private DBConnect connection = new DBConnect();
    
    private Runnable onCloseCallback;

    // Constructor para nuevo registro
    public Values(BaseDatos db, BDTabla tabla) {
        layout = new VBox(15);  // Aumentar spacing
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("form-container");  // Agregar clase CSS
        inputFields = new ArrayList<>();
        
        this.db = db;
        this.tabla = tabla;
        this.isEditMode = false;
    }
    
    // Constructor para editar registro existente
    public Values(BaseDatos db, BDTabla tabla, List<String> currentValues, int rowIndex) {
        layout = new VBox(15);  // Aumentar spacing
        layout.setPadding(new Insets(30));
        layout.getStyleClass().add("form-container");  // Agregar clase CSS
        inputFields = new ArrayList<>();
        
        this.db = db;
        this.tabla = tabla;
        this.currentValues = currentValues;
        this.editRowIndex = rowIndex;
        this.isEditMode = true;
    }
    
    public void setOnCloseCallback(Runnable callback) {
        this.onCloseCallback = callback;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void start(Stage primaryStage) throws Exception {

        Label formTitle = new Label(isEditMode ? "✏ Editar Registro" : "✚ Nuevo Registro");
        formTitle.getStyleClass().add("form-title");
        layout.getChildren().add(formTitle);

        Region separator = new Region();
        separator.getStyleClass().add("form-separator");
        layout.getChildren().add(separator);
        
        List<BDCampo> campos = tabla.getCampos();
        
        for(int i = 0; i < campos.size(); i++) {
            BDCampo c = campos.get(i);
            Object inputField = null;
            
            Label fieldLabel = new Label(c.getNombreCampo());
            fieldLabel.getStyleClass().add("field-label");
            layout.getChildren().add(fieldLabel);
            
            String currentValue = "";
            if (isEditMode && currentValues != null && i < currentValues.size()) {
                currentValue = currentValues.get(i);
            }
            if(c.getNombreCampo() == tabla.getForeingKey()) {
            	String id = (c.getValores().size()>0) ? c.getValores().get(c.getValores().size()-1) : "1";
            	int intid = Integer.parseInt(id);
                inputField = new Spinner<>(0, 999999, intid++);
                ((Spinner) inputField).getStyleClass().add("spinner");
                
                if (isEditMode && !currentValue.isEmpty()) {
                    try {
                        ((Spinner) inputField).getValueFactory().setValue(Integer.parseInt(currentValue));
                    } catch (NumberFormatException e) {
                        System.out.println("Error al parsear número: " + currentValue);
                    }
                }
                
                layout.getChildren().add((Spinner) inputField);
            }
            
            switch(c.getNombreTipo()) {
                case "PRIMARY KEY AUTOINCREMENT INT NOT NULL":
                	String id = (c.getValores().size()>0) ? c.getValores().get(c.getValores().size()-1) : "1";
                	int intid = Integer.parseInt(id);
                	
                    inputField = new Spinner<>(0, 999999, intid++);
                    ((Spinner) inputField).getStyleClass().add("spinner");
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode && !currentValue.isEmpty()) {
                        try {
                            ((Spinner) inputField).getValueFactory().setValue(Integer.parseInt(currentValue));
                        } catch (NumberFormatException e) {
                            System.out.println("Error al parsear número: " + currentValue);
                        }
                    }
                    
                    layout.getChildren().add((Spinner) inputField);
                    break;

                case "texto":
                    inputField = new TextField();
                    ((TextField) inputField).getStyleClass().add("text-field");
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode) {
                        ((TextField) inputField).setText(currentValue);
                    }
                    
                    layout.getChildren().add((TextField) inputField);
                    break;

                case "numero":
                    inputField = new Spinner<>(0, 999999, 1);
                    ((Spinner) inputField).setEditable(true);
                    ((Spinner) inputField).getStyleClass().add("spinner");
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode && !currentValue.isEmpty()) {
                        try {
                            ((Spinner) inputField).getValueFactory().setValue(Integer.parseInt(currentValue));
                        } catch (NumberFormatException e) {
                            System.out.println("Error al parsear número: " + currentValue);
                        }
                    }
                    
                    layout.getChildren().add((Spinner) inputField);
                    break;

                case "fecha":
                    inputField = new DatePicker();
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode && !currentValue.isEmpty()) {
                        try {
                            LocalDate date = LocalDate.parse(currentValue);
                            ((DatePicker) inputField).setValue(date);
                            ((DatePicker) inputField).getStyleClass().add("date-picker");
                        } catch (Exception e) {
                            System.out.println("Error al parsear fecha: " + currentValue);
                        }
                    }
                    
                    layout.getChildren().add((DatePicker) inputField);
                    break;

                case "tiempo":
                    inputField = new TextField();
                    ((TextField) inputField).getStyleClass().add("text-field");
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode) {
                        ((TextField) inputField).setText(currentValue);
                    }
                    
                    layout.getChildren().add((TextField) inputField);
                    break;

                default:
                    System.out.println("Error, tipo: " + c.getNombreTipo() + " no encontrado");
                    break;
            }
            
            // Agregar el campo de entrada a nuestra lista de referencia
            if(inputField != null) {
                inputFields.add(inputField);
            }
        }

        HBox buttonContainer = new HBox();
        buttonContainer.getStyleClass().add("button-container");

        Button acceptButton = new Button(isEditMode ? "💾 Actualizar" : "💾 Guardar");
        acceptButton.getStyleClass().add("primary-button");
        
        acceptButton.setOnAction((_) -> {
            String values = "(";
            // Ahora usar la lista de inputFields en lugar de layout.getChildren()
            for(int i = 0; i < campos.size() && i < inputFields.size(); i++) {
                Object input = inputFields.get(i);
                BDCampo campo = campos.get(i);

                if(input instanceof TextField) {
                    String v = ((TextField) input).getText();
                    values += "'" + v + "',";
                    
                    if (isEditMode) {
                        // Actualizar valor existente
                        if (campo.getValores() != null && editRowIndex < campo.getValores().size()) {
                            campo.getValores().set(editRowIndex, v);
                        }
                    } else {
                        // Agregar nuevo valor
                        campo.addValor(v);
                    }
                }
                else if(input instanceof DatePicker) {
                    LocalDate date = ((DatePicker) input).getValue();
                    String v = (date != null) ? date.toString() : "";
                    values += "'" + v + "',";
                    
                    if (isEditMode) {
                        // Actualizar valor existente
                        if (campo.getValores() != null && editRowIndex < campo.getValores().size()) {
                            campo.getValores().set(editRowIndex, v);
                        }
                    } else {
                        // Agregar nuevo valor
                        campo.addValor(v);
                    }
                }
                else if(input instanceof Spinner) {
                    String v = ((Spinner) input).getValue().toString();
                    values += v + ",";
                    
                    if (isEditMode) {
                        // Actualizar valor existente
                        if (campo.getValores() != null && editRowIndex < campo.getValores().size()) {
                            campo.getValores().set(editRowIndex, v);
                        }
                    } else {
                        // Agregar nuevo valor
                        campo.addValor(v);
                    }
                }
            }
            values = values.substring(0, values.length()-1);
            values += ")";
            
            try {
            	String fields = "(";
            	for(BDCampo c : tabla.getCampos()) {
            		fields += c.getNombreCampo() + ",";
            	}

                fields = fields.substring(0, fields.length()-1);
                fields += ')';
            	String query = "INSERT INTO " + tabla.getNombreTabla() + fields + " VALUES" + values;
            	System.out.println(query);
				connection.insertTable(query, db.getNombreDB());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        	// Ejecutar callback si existe
            if (onCloseCallback != null) {
                onCloseCallback.run();
            }
            primaryStage.close();
        });

        buttonContainer.getChildren().add(acceptButton);
        layout.getChildren().add(buttonContainer);

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("values_styles.css").toExternalForm());

        primaryStage.setTitle(isEditMode ? "Editar Valores" : "Nuevos Valores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}