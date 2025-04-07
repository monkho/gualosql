module proyecto_2 {
	requires javafx.controls;
	requires antlr;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
