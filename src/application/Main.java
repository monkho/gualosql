package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.stream.Collectors;

//import org.antlr.runtime.ANTLRInputStream;
//import org.antlr.runtime.CommonTokenStream;
//import org.antlr.runtime.RecognitionException;
//import org.antlr.runtime.TokenSource;
//import org.antlr.runtime.TokenStream;
import org.antlr.v4.runtime.CharStream;
//import org.antlr.v4.runtime.ANTLRInputStream;  // Reemplazado por CharStreams (ver nota abajo)
import org.antlr.v4.runtime.CharStreams;       // Nueva forma de manejar input
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.TokenSource;
//import org.antlr.v4.runtime.TokenStream;


public class Main extends Application {
    private TextArea textArea = new TextArea();
    private TextArea terminalArea = new TextArea();
    private TextArea errorsArea = new TextArea();
    private TextArea lineNumbers = new TextArea();
    private File currentFile = null;

    @Override
    public void start(Stage primaryStage) {
        // Configuración del terminal (solo lectura)
        terminalArea.setEditable(false);
        terminalArea.setStyle("-fx-background-color: #f4f4f4;");
        terminalArea.setPrefWidth(700);

        errorsArea.setEditable(false);
        errorsArea.setStyle("-fx-background-color: #f4f4f4;");
        errorsArea.setPrefWidth(600);

        // Configuración de los números de línea
        lineNumbers.setEditable(false);
        lineNumbers.setStyle("-fx-background-color: #eaeaea; -fx-text-fill: #555;");
        lineNumbers.setPrefWidth(30);
        lineNumbers.setId("line-numbers");

        // Sincronizar el número de líneas con el área de texto
        textArea.requestFocus();
        textArea.textProperty().addListener((_, _, newText) -> updateLineNumbers(newText));
        textArea.scrollTopProperty().addListener((_, _, newVal) -> lineNumbers.setScrollTop(newVal.doubleValue()));
        
        // Menú de archivom
        Menu fileMenu = new Menu("Archivo");
        MenuItem openItem = new MenuItem("Abrir (ctrl+o)");
        MenuItem saveItem = new MenuItem("Guardar (ctrl+s)");
        MenuItem saveAsItem = new MenuItem("Guardar Como...(ctrl+shift+s)");
        MenuItem exitItem = new MenuItem("Salir");

        Menu runMenu = new Menu("Run");
        MenuItem compileItem = new MenuItem("Compilar (ctrl+p)");
        MenuItem runItem = new MenuItem("Ejecutar (ctrl+r)");
        MenuItem clearTerminalItem = new MenuItem("Limpiar terminal (ctrl+alt+c)");
        
        fileMenu.getItems().addAll(openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);
        runMenu.getItems().addAll(compileItem, runItem, new SeparatorMenuItem(), clearTerminalItem);

        openItem.setOnAction(_ -> openFile(primaryStage));
        saveItem.setOnAction(_ -> saveFile(primaryStage, false));
        saveAsItem.setOnAction(_ -> saveFile(primaryStage, true));
        exitItem.setOnAction(_ -> primaryStage.close());
        compileItem.setOnAction(_ -> {
			try {
				executeFile(primaryStage);
			} catch (IOException | RecognitionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        clearTerminalItem.setOnAction(_ -> clearTerminal(primaryStage));

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, runMenu);

        // Contenedor del área de texto con números de línea
        HBox textContainer = new HBox(lineNumbers, textArea);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        HBox.setHgrow(lineNumbers, Priority.NEVER);

        SplitPane splitTerminal = new SplitPane();
        splitTerminal.setOrientation(javafx.geometry.Orientation.HORIZONTAL);
        splitTerminal.getItems().addAll(terminalArea, errorsArea);
        splitTerminal.setDividerPositions(0.5);
        
        HBox outputContainer = new HBox(splitTerminal);
        HBox.setHgrow(terminalArea, Priority.ALWAYS);
        HBox.setHgrow(errorsArea, Priority.ALWAYS);
        
     // SplitPane para ajustar tamaños dinámicamente
        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        splitPane.getItems().addAll(textContainer, outputContainer);
        splitPane.setDividerPositions(0.7); // Inicialmente, 70% para el área de texto

        // Layout principal con márgenes
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(16));
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        
        layout.getChildren().addAll(menuBar, splitPane);
        
        Scene scene = new Scene(layout, 900, 500);
        
        scene.getAccelerators().put(
    		new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
    		() -> saveFile(primaryStage, false)
		);
        
        scene.getAccelerators().put(
    		new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN),
    		() -> saveFile(primaryStage, true)
		);

        scene.getAccelerators().put(
    		new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN),
    		() -> openFile(primaryStage)
		);
        
        scene.getAccelerators().put(
    		new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN),
    		() -> {
				try {
					executeFile(primaryStage);
				} catch (IOException | RecognitionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		);
        
        scene.getAccelerators().put(
    		new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN), 
    		() -> clearTerminal(primaryStage)
		);
        
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Editor de Texto Simple");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String content = reader.lines().collect(Collectors.joining("\n"));
                textArea.setText(content);
                updateLineNumbers(content);
                currentFile = file;
                terminalArea.appendText("\t\nArchivo cargado: " + file.getName() + "\n\n");
            } catch (IOException e) {
                terminalArea.appendText("\t\nError al abrir el archivo\n\n");
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Stage stage, boolean saveAs) {
        if (currentFile == null || saveAs) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"));
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                currentFile = file;
            } else {
                return;
            }
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(textArea.getText());
            terminalArea.appendText("\t\nArchivo guardado: " + currentFile.getName() + "\n\n");        } 
        catch (IOException e) {
            terminalArea.appendText("\t\n\nError al guardar el archivo\n\n");
            e.printStackTrace();
        }
    }
    
    private void executeFile(Stage stage) throws UnsupportedEncodingException, RecognitionException, IOException {
    	CharStream input = null;
    	String s = textArea.getText();
//    		input = CharStreams.fromFileName(currentFile.getAbsolutePath());
		input = CharStreams.fromString(s);
    	
    	lenguajeLexer lexer = new lenguajeLexer(input);
    	CommonTokenStream tokens = new CommonTokenStream((TokenSource) lexer);
    	
    	lenguajeParser parser = new lenguajeParser(tokens);
    	
    	parser.prog();
    	clearTerminal(stage);
    	terminalArea.appendText(parser.getCompiled());
    	errorsArea.appendText(parser.getErrors());
    }
    
    private void clearTerminal(Stage stage) {
    	terminalArea.clear();
    	errorsArea.clear();
    }

    private void updateLineNumbers(String content) {
        StringBuilder numbers = new StringBuilder();
        int lineCount = content.split("\n", -1).length;
        for (int i = 1; i <= lineCount; i++) {
            numbers.append(i).append("\n");
        }
        lineNumbers.setText(numbers.toString());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}