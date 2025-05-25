grammar lenguaje;

@header {
package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
}

@members {
HashMap baseDatos = new HashMap();
BaseDatos baseDatosActual;
BDTabla tablaActual;
List<String> camposActuales = new ArrayList<>();
List<String> valoresActuales = new ArrayList<>();
String query = "";
List<String> compiled = new ArrayList<>();
List<String> errors = new ArrayList<>();

boolean error = false;

public void pushDB(String nombre, BaseDatos db) {
    if (baseDatos.containsKey(nombre)) {
        System.out.println("Base de datos: " + nombre + " ya existente");
        errors.add("Base de datos: " + nombre + " ya existente");
        error = true;
        return;
    }
    baseDatos.put(nombre, db);
}

public BaseDatos findDB(String db) {
    if (!baseDatos.containsKey(db)) {
        System.out.println("No existe la base de datos: " + db);
        errors.add("No existe la base de datos: " + db);
        error = true;
        return null;
    }
    return (BaseDatos) baseDatos.get(db);
}

public void removeDB(String db) {
    if(findDB(db) != null){
        baseDatos.remove(db);
    }
    else
        error = true;
}

public void removeTabla(String tabla, BaseDatos db) {
    BDTabla t = findTabla(tabla, db);
    if(t != null) {
        db.removeTabla(t);
    }
    else
        error = true;
}

public void pushTabla(BaseDatos currdb, BDTabla t) {
    BaseDatos db = findDB(currdb.getNombreDB());
    if(db!=null) {
        if(db.tablaDup(t.getNombreTabla() ) ) {
            errors.add("Tabla: " + t.getNombreTabla() + " ya existe en la base de datos: " + db.getNombreDB());
            System.out.println("Tabla: " + t.getNombreTabla() + " ya existe en la base de datos: " + db.getNombreDB());
            error = true;
            return;
        }
        db.addTabla(t);
    }
}

public BDTabla findTabla(String tabla, BaseDatos db) {
    if (baseDatos.containsKey(db.getNombreDB())) {
        List<BDTabla> tablas = db.getTablas();
        for (BDTabla t : tablas) {
            if (t.getNombreTabla().equals(tabla)) return t;
        }
    }
    errors.add("Tabla: " + tabla + ", no existe en la base de datos: " + db.getNombreDB());
    System.out.println("Tabla: " + tabla + ", no existe en la base de datos: " + db.getNombreDB());
    error = true;
    return null;
}

public BDCampo findCampo(String campo, BDTabla tabla) {
    List<BDCampo> campos = null;
    if(tabla != null) {
        campos = tabla.getCampos();

        if(campos != null) {
            for (BDCampo c : campos) {
                if (c.getNombreCampo().equals(campo)) return c;
            }
        }
        errors.add("Campo: " + campo + ", no existe en la tabla: " + tabla.getNombreTabla());
        System.out.println("Campo: " + campo + ", no existe en la tabla: " + tabla.getNombreTabla());
    }
    error = true;
    return null;
}

public void printQuery(String query) {
    System.out.println("\nQuery generado:==========");
    System.out.print(query);
    System.out.println("=========================");
}

public String getCampos(List<String> campos) {
    String res = "";
    for(int i=0; i<campos.size(); i++) {
        if(i == campos.size() - 1) {
            System.out.println("Last element of campos, so it dont need comma: " + campos.get(i));
            res += campos.get(i);
        } else {
            res += campos.get(i) + ',';
        }
    }
    return res;
}
public String getValores(List<String> valores) {
    String res = "";
    for(int i=0; i<valores.size(); i++) {
//        if(i == valores.size() - 1) {
//            System.out.println("Last element of valores, so it dont need comma: " + valores.get(i));
//            res += valores.get(i);
//        } else {
//            res += valores.get(i) + ',';
//        }
        
        res += valores.get(i) + ',';
    }
    return res;
}

public String getCompiled() {
    String r = "";
    for(String s : compiled){
        r += s + '\n';
    }
    return r;
}

public String getErrors() {
    String r = "";
    for(String s : errors) {
        r += s + '\n';
    }
    return r;
}

public String rmComma(String query) {
    int li = query.lastIndexOf(",");
    return query.substring(0, li) + query.substring(li+1, query.length()-1);
}
}

// Regla de entrada: una secuencia de sentencias
prog: statement* EOF
    {
        for (Object bd: baseDatos.values()) {
            if(bd != null)
                System.out.println(bd.toString());
        }
    }
;

// Sentencias principales
statement
    : createbaseDatostmt (crudStmt)+ finishDB (extraStmt)?
    ;

crudStmt
    : createTableStmt
    | insertStmt
    | selectStmt
    | deleteStmt
    | updateStmt
    | deleteDBStmt
    | deleteTableStmt
    ;

extraStmt
    : deleteDBStmt
    ;

// -----------------------------------------------------------------------------
// Crear base de datos
createbaseDatostmt
    : 'crea' 'la' 'base' 'de' 'datos' IDENTIFIER NEWLINE
    {
        baseDatosActual = new BaseDatos($IDENTIFIER.text);
        pushDB($IDENTIFIER.text, baseDatosActual);
        if(!error) {
            query += "-- =====================\n-- Create and use Database: " + $IDENTIFIER.text + "\n";
            query += "CREATE DATABASE IF NOT EXISTS " + $IDENTIFIER.text + ';' + '\n';
//            query += "USE DATABASE " + $IDENTIFIER.text + ";\n";
            printQuery(query);
            compiled.add(query);
            query = "";
        }
        error = false;
    }
    ;

// -----------------------------------------------------------------------------
// Crear tabla con definición de campos
createTableStmt
    : 'crea' 'la' 'tabla' IDENTIFIER
      {
        BDTabla t = new BDTabla($IDENTIFIER.text);
        t.addCampo(new BDCampo("id_" + $IDENTIFIER.text, "PRIMARY KEY AUTOINCREMENT INT NOT NULL"));
        pushTabla(baseDatosActual, t); 
      }
      NEWLINE?
      {
        tablaActual = findTabla($IDENTIFIER.text, baseDatosActual);
      }
      'con' 'campos' NEWLINE?
      fieldList
      {
        if(!error) {
            query += "-- =====================\n-- Create Table: " + $IDENTIFIER.text + "\n";
            query += "CREATE TABLE IF NOT EXISTS " + $IDENTIFIER.text + "(\n"
                + "id_" + $IDENTIFIER.text + " INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n";
            for(BDCampo c : tablaActual.getCampos()) {
                String tipo = c.getNombreTipo();
                String nombre = c.getNombreCampo();
                switch(tipo) {
                    case "texto":
                        query += nombre + " TEXT,";
                        break;
                    case "numero":
                        query += nombre + " INT,";
                        break;
                    case "fecha":
                        query += nombre + " DATE,";
                        break;
                    case "tiempo":
                        query += nombre + " TIME,";
                        break;
                }
            }
        }
        error = false;
      }
      (foreignRef
      {
        if(!error && $foreignRef.fk != null)
            query += $foreignRef.fk;
      }
      )?
      'termina' 'la' 'tabla' NEWLINE
      {
        if(!error) {
            int li = query.lastIndexOf(",");
            query = query.substring(0, query.length()-1);
            query += ");";
            
            tablaActual.setQuery(query);

            printQuery(query);
            compiled.add(query);
            query = "";
        }
        tablaActual = null;
      }
    ;

// Lista de campos (cada uno en una línea o separados por espacios)
fieldList
    : (field NEWLINE?)+
    ;

// Cada campo se define con su nombre y tipo de dato.
field
    : IDENTIFIER 'en' dataType (',')?
    {
        tablaActual.addCampo(new BDCampo($IDENTIFIER.text, $dataType.t));
    }
    ;

foreignRef returns [String fk]
    : 'referenciado' 'con' IDENTIFIER NEWLINE?
    {
        $fk = "";
        BDTabla t = findTabla($IDENTIFIER.text, baseDatosActual);
        String type = "";
        if(t != null) {
            type = " INT, FOREIGN KEY (fk_"+t.getIdTabla()+") REFERENCES " + t.getNombreTabla() + "("+t.getIdTabla()+") ON DELETE CASCADE,";
            tablaActual.addCampo(new BDCampo("fk_" + t.getIdTabla(), type));
            $fk = "fk_" + t.getIdTabla() + type;
        }
    }
    ;
// -----------------------------------------------------------------------------
// Insertar datos en una tabla
insertStmt
    : 'inserta' 'en' 'la' 'tabla' IDENTIFIER NEWLINE?
      {
        tablaActual = findTabla($IDENTIFIER.text, baseDatosActual);
      }
      insertFieldList
      'termina' 'la' 'insercion' NEWLINE
      {
        if(!error) {
            query += "-- =====================\n-- Insert Table: " + $IDENTIFIER.text + "\n";
            String v = getValores(valoresActuales);
            v = v.substring(0, v.length()-1);
            query += "INSERT INTO " + $IDENTIFIER.text + " (" + getCampos(camposActuales) + ") \nVALUES(" + v + ");";
            printQuery(query);
            compiled.add(query);
            query = "";
        }
        error = false;
        camposActuales = new ArrayList<>();
        valoresActuales = new ArrayList<>();
      }
    ;

// Lista de asignaciones de valor a un campo
insertFieldList returns [List<BDCampo> campos]
    : (insertField NEWLINE?)+
    ;

insertField returns [BDCampo campo]
    : IDENTIFIER 'con' 'valor' literal (',')?
    {
        camposActuales.add($IDENTIFIER.text);
        valoresActuales.add($literal.t);
        $campo = findCampo($IDENTIFIER.text, tablaActual);
        if($campo != null) {
            $campo.addValor($literal.t);
        }
    }
    ;

// -----------------------------------------------------------------------------
// Seleccionar datos (con o sin condición, y con join entre dos tablas)
selectStmt
    : 'selecciona' selectFields
    {
            query += "-- =====================\n-- Select Table: \n";
        if(!error) {
            if($selectFields.t != null){
                query += "SELECT * FROM ";
            }
            else {
                query += "SELECT " + getCampos(camposActuales) + " FROM ";
            }
       }
        error = false;
    }
    selectFrom
    {
        if(!error) {
            if($selectFrom.tabla == null)
                query += tablaActual.getNombreTabla() + ' ';
            else {
                BDTabla t1 = $selectFrom.tabla.get(0);
                BDTabla t2 = $selectFrom.tabla.get(1);
                query += t1.getNombreTabla() + " INNER JOIN " + t2.getNombreTabla() + " ON " + t1.getNombreTabla() + '.' + t1.getIdTabla() + " = " + t2.getNombreTabla() + '.' + t2.getForeingKey();
            }
        }
    }
    (whereClause {
        if(!error) {
            query += "WHERE " + $whereClause.c ;
        }
    } )? NEWLINE
    {
        if(!error) {
            query += ";\n";
            compiled.add(query);
            printQuery(query);
        }
        query = "";
        camposActuales = new ArrayList<>();
    }
    ;

// Se puede seleccionar todos los campos o una lista de campos
selectFields returns [String t]
    : 'todo' { $t = "todo"; }
    | fieldListSpec NEWLINE? { $t = null; }
    ;

// Lista de campos a seleccionar (separados por espacios)
fieldListSpec returns [List<String> campos]
    : (IDENTIFIER (',')?)+
    {
        camposActuales.add($IDENTIFIER.text);
    }
    ;

// Dos alternativas para el origen de los datos:
// - De una tabla simple
// - Entre dos tablas (con opción de especificar la base de datos)
selectFrom returns [List<BDTabla> tabla]
    : 'de' 'la' 'tabla' IDENTIFIER
    {
        tablaActual = findTabla($IDENTIFIER.text, baseDatosActual);
        for(String c : camposActuales) {
            findCampo(c, tablaActual);
        }
        $tabla = null;
    }
    | 'entre' id1=IDENTIFIER 'y' id2=IDENTIFIER
    {
        BDTabla t1 = findTabla($id1.text, baseDatosActual);
        BDTabla t2 = findTabla($id2.text, baseDatosActual);
        for(String c : camposActuales) {
            BDCampo c1 = findCampo(c, t1);
            BDCampo c2 = findCampo(c, t2);
            if(c1 != null || c2 != null) error = false;
        }
        $tabla = new ArrayList();
        $tabla.add(t1);
        $tabla.add(t2);
    }
    ;

// Clausula opcional para filtrar resultados
whereClause returns [String c, String v, String cond]
    : 'cuando' IDENTIFIER condiciones literal
    {
        $c = $IDENTIFIER.text;
        $v = $literal.t;
        $cond = $condiciones.cond;
    }
    | 'cuando' 'el' 'campo' IDENTIFIER condiciones literal
    {
        $c = $IDENTIFIER.text;
        $v = $literal.t;
        $cond = $condiciones.cond;
    }
    ;

condiciones returns [String cond]
    : 'sea' 'igual' 'a' { $cond = "="; }
    | 'sea' 'mayor' 'a' { $cond = ">"; }
    | 'sea' 'menor' 'a' { $cond = "<"; }
    | 'sea' 'menor' 'o' 'igual' 'a' { $cond = "<="; }
    | 'sea' 'mayor' 'o' 'igual' 'a' { $cond = ">="; }
    | 'sea' 'distinto' 'de' { $cond = "<>"; }
    ;

// -----------------------------------------------------------------------------
// Borrar registros
deleteStmt
    : 'borra' 'de' 'la' 'tabla' IDENTIFIER whereClause NEWLINE
    {
        tablaActual = findTabla($IDENTIFIER.text, baseDatosActual);
        if(!error) {
            query += "-- =====================\n-- Delete from Table: " + $IDENTIFIER.text + "\n";
            query += "DELETE FROM " + $IDENTIFIER.text + " WHERE " + $whereClause.c + " " + $whereClause.cond + " " + $whereClause.v + ";\n";
            printQuery(query);
            compiled.add(query);
            query = "";
        }
        error = false;
    }
    ;

deleteTableStmt
    : 'borra' 'la' 'tabla' tabla=IDENTIFIER NEWLINE
    {
        removeTabla($tabla.text, baseDatosActual);
        if(!error) {
            query += "-- =====================\n-- Drop Table: " + $tabla.text + "\n";
            query += "DROP TABLE IF EXISTS " + $tabla.text + ";\n";
            printQuery(query);
            query = "";
        }
    }
    ;

deleteDBStmt
    : 'borra' 'la' 'base' 'de' 'datos' IDENTIFIER NEWLINE
    {
        removeDB($IDENTIFIER.text);
        if(!error) {
            query += "-- =====================\n-- Drop Database: " + $IDENTIFIER.text + "\n";
            query += "DROP DATABASE IF EXISTS " + $IDENTIFIER.text + ";\n";
            printQuery(query);
            compiled.add(query);
            query = "";
        }
        error = false;
    }
    ;

// -----------------------------------------------------------------------------
// Modificar registros
updateStmt
    : 'modifica' 'de' 'la' 'tabla' IDENTIFIER NEWLINE?
    {
        tablaActual = findTabla($IDENTIFIER.text, baseDatosActual);
    }
      updateFieldList
      whereClause NEWLINE
    ;

// Lista de actualizaciones (campo con nuevo valor)
updateFieldList returns [List<BDCampo> campos]
    : (updateField NEWLINE?)+
    ;

updateField returns [BDCampo campo]
    : IDENTIFIER 'con' literal (',')?
    {
    }
    ;

// -----------------------------------------------------------------------------
// terminar bd
finishDB
    : 'termina' 'la' 'base' 'de' 'datos' IDENTIFIER NEWLINE?
    {
        if(findDB($IDENTIFIER.text) == baseDatosActual)
            baseDatosActual = null;
    }
    ;


// -----------------------------------------------------------------------------
// Literales: se admiten cadenas, números, fechas, horas o identificadores
literal returns [String t]
    : STRING { $t = $STRING.text; }
    | NUMBER { $t = $NUMBER.text; }
    | DATE { $t = $DATE.text; }
    | TIME { $t = $TIME.text; }
    ;

dataType returns [String t]
    : TEXTO
    {
        $t= $TEXTO.text;
    }
    | NUMERO
    {
        $t= $NUMERO.text;
    }
    | FECHA
    {
        $t= $FECHA.text;
    }
    | TIEMPO
    {
        $t= $TIEMPO.text;
    }
    ;

TEXTO: 'texto';
NUMERO: 'numero';
FECHA: 'fecha';
TIEMPO: 'tiempo';

// -----------------------------------------------------------------------------
// Reglas para fecha y hora
DATE: '\'' DIGIT DIGIT DIGIT DIGIT '-' DIGIT DIGIT '-' DIGIT DIGIT '\'';   // YYYY-MM-DD
TIME: '\'' DIGIT DIGIT ':' DIGIT DIGIT ':' DIGIT DIGIT '\'';                // HH:MM:SS

// -----------------------------------------------------------------------------
// Tokens básicos
NUMBER: [0-9]+;
STRING: '\'' (~['\r\n])* '\'';
IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;

// Fragmentos para dígitos (utilizados en las fechas y horas)
fragment DIGIT: [0-9];

// Se reconoce nueva línea para facilitar la lectura de las sentencias
NEWLINE: ('\r'? '\n')+;

// Espacios en blanco (se omiten)
WS: [ \t]+ -> skip;
