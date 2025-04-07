package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	private Connection connector;
	private Statement stmt;
	private String db = ""; 

	
	public DBConnect () {}
	
	public void open() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/?allowMultiQueries=true", "root", null);
		stmt = connector.createStatement();
	}
	
	public void open(String db) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db+"?allowMultiQueries=true", "root", null);
		stmt = connector.createStatement();
	}
	
	public void close() throws SQLException {
		stmt.close();
		connector.close();
	}
	
	public void showDbs() throws SQLException, ClassNotFoundException {
		open();
		String sql = "SHOW DATABASES";
		ResultSet rs = null;
		try {
			if(stmt.execute(sql)) {
				rs = stmt.getResultSet();
				System.out.println(rs.getCursorName());
			}
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT:: " + e.getMessage());
		} finally {
			close();
		}
	}
	
	public void exec(String query) throws SQLException, ClassNotFoundException {
		open();
		ResultSet rs = null;
		if(stmt.execute(query)) {
			rs = stmt.getResultSet();
			System.out.println(rs.getCursorName());
		}
		close();
	}
	
	public String executeQuery(String query) {
		if(query.contains("Create and use Database")) {
			try {
				createDB(query);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Create Table")) {
			try {
				createTable(query, db);
				System.out.println(db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Insert Table")) {
			try {
				insertTable(query, db);
				System.out.println(db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Select Table")) {
			try {
				return selectTable(query, db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Delete from Table")) {
			try {
				deleteFromTable(query, db);
				System.out.println(db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Drop Table")) {
			try {
				dropTable(query, db);
				System.out.println(db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(query.contains("Drop Database")) {
			try {
				dropDB(query);
				System.out.println(db);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
	
	public void createDB(String query) throws SQLException, ClassNotFoundException {
		open();
		this.db = query.split("\n")[2].split(" ")[5];
		db = db.substring(0, db.length()-1);
		System.out.println(db);
		try {
			boolean res = stmt.execute(query);
			System.out.println("Creating DB... " + res);
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (CreatingDB):: " + e.getMessage());
		} finally {
			close();
		}
	}

	public void createTable(String query, String db) throws SQLException, ClassNotFoundException {
		open(db);
		try {
//			int li = query.lastIndexOf(",");
//			query = query.substring(0, li) + query.substring(li+1, query.length()-1);
			int res = stmt.executeUpdate(query);
			System.out.println("Creating tABLE... " + res);
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (CreatingTable):: " + e.getMessage());
		} 
		close();
	}

	public void insertTable(String query, String db) throws SQLException, ClassNotFoundException {
		open(db);
		try {
//			int li = query.lastIndexOf(",");
//			query = query.substring(0, li) + query.substring(li+1, query.length());
			int res = stmt.executeUpdate(query);
			System.out.println("Seedind table:: " + res);
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (InsertingTable):: " + e.getMessage());
		}
		close();
	}

	public String selectTable(String query, String db) throws SQLException, ClassNotFoundException {
		open(db);
		String selected = "";

		StringBuilder resultado = new StringBuilder();
		
		try{
			ResultSet rs = stmt.executeQuery(query);
			

            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            while (rs.next()) {
            	for (int i = 1; i <= columnas; i++) {
            	    resultado.append(meta.getColumnName(i)).append(i < columnas ? "\t|\t" : "");
            	}
            	resultado.append("\n");
            	
                for (int i = 1; i <= columnas; i++) {
                    Object valor = rs.getObject(i);
                    resultado.append(valor).append(i < columnas ? "\t|\t" : "");
                }
                resultado.append("\n");
            }

            System.out.println(resultado.toString());
			selected += "Seleccion:===========\n";
			selected += resultado.toString();

		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (SelectingTable):: " + e.getMessage());
		}
		close();
		return selected;
	}

	public void deleteFromTable(String query, String db) throws SQLException, ClassNotFoundException {
		open(db);
		try{
			ResultSet res = stmt.executeQuery(query);
			System.out.println("Deleted: " + res);			
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (DeletingFromTable):: " + e.getMessage());
		}
		close();
	}

	public void dropTable(String query, String db) throws SQLException, ClassNotFoundException {
		open(db);
		try{
			ResultSet res = stmt.executeQuery(query);
			System.out.println("Droped: " + res);
		} catch(SQLException e) {
			System.out.println(" EXCEPTION CAUGHT (DropingTable):: " + e.getMessage());
		}
		close();
	}

	public void dropDB(String query) throws SQLException, ClassNotFoundException {
		open(db);
		
		close();
	}
}
