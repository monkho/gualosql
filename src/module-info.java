module proyecto_2 {
	requires javafx.controls;
	requires antlr;
	requires java.sql;
	requires javafx.graphics;
//	requires MaterialFX;
	
	opens application to javafx.graphics, javafx.fxml;
}
