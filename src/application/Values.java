package application;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Values extends Application {
    private VBox layout;
    private BDTabla tabla;
    
    // Lista para mantener referencia a los campos de entrada
    private List<Object> inputFields;
    
    // Variables para modo edición
    private boolean isEditMode = false;
    private List<String> currentValues;
    private int editRowIndex;
    
    private Runnable onCloseCallback;

    // Constructor para nuevo registro
    public Values(BDTabla tabla) {
        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        inputFields = new ArrayList<>();
        this.tabla = tabla;
        this.isEditMode = false;
    }
    
    // Constructor para editar registro existente
    public Values(BDTabla tabla, List<String> currentValues, int rowIndex) {
        layout = new VBox(10);
        layout.setPadding(new Insets(20));
        inputFields = new ArrayList<>();
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

        List<BDCampo> campos = tabla.getCampos();
        
        for(int i = 0; i < campos.size(); i++) {
            BDCampo c = campos.get(i);
            Object inputField = null;
            layout.getChildren().addAll(new Label(c.getNombreCampo()));
            
            String currentValue = "";
            if (isEditMode && currentValues != null && i < currentValues.size()) {
                currentValue = currentValues.get(i);
            }
            
            switch(c.getNombreTipo()) {
                case "PRIMARY KEY AUTOINCREMENT INT NOT NULL":
                	String id = (c.getValores().size()>0) ? c.getValores().get(c.getValores().size()-1) : "1";
                	int intid = Integer.parseInt(id);
                	
                    inputField = new Spinner<>(0, 999999, intid++);
                    
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
                    
                    // Establecer valor actual si está en modo edición
                    if (isEditMode) {
                        ((TextField) inputField).setText(currentValue);
                    }
                    
                    layout.getChildren().add((TextField) inputField);
                    break;

                case "numero":
                    inputField = new Spinner<>(0, 999999, 1);
                    ((Spinner) inputField).setEditable(true);
                    
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
                        } catch (Exception e) {
                            System.out.println("Error al parsear fecha: " + currentValue);
                        }
                    }
                    
                    layout.getChildren().add((DatePicker) inputField);
                    break;

                case "tiempo":
                    inputField = new TextField();
                    
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

        Button acceptButton = new Button(isEditMode ? "Actualizar" : "Aceptar");
        acceptButton.setOnAction((_) -> {
            String values = isEditMode ? "Valores actualizados:\n" : "Valores agregados:\n";
            
            // Ahora usar la lista de inputFields en lugar de layout.getChildren()
            for(int i = 0; i < campos.size() && i < inputFields.size(); i++) {
                Object input = inputFields.get(i);
                BDCampo campo = campos.get(i);

                if(input instanceof TextField) {
                    String v = ((TextField) input).getText();
                    values += "\tTexto: " + v;
                    
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
                    values += "\tFecha: " + v;
                    
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
                    values += "\tNumero: " + v;
                    
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
                values += "\n";
            }
            System.out.println(values);
         // Ejecutar callback si existe
            if (onCloseCallback != null) {
                onCloseCallback.run();
            }
            primaryStage.close();
        });

        layout.getChildren().add(acceptButton);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle(isEditMode ? "Editar Valores" : "Nuevos Valores");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}