# Proyecto 2
El proyecto consiste en la creacion de un lenguaje de alto nivel, en español, con el cual utilizando una sintaxis lo mas acercado a la humana, permita la creación de consultas SQL para que cualquier persona sin importar el tipo de preparación técnica le sea posible interactuar con una base de datos.

## Gramática
La gramatica de este lenguaje, esta pensada para simplificar las consultas consisitendo en:


```sql
-- El imput:
crea la base de datos <nombre_bd>
-- Genera el SQL
CREATE DATABASE <nombre_bd>

-- El input:
crea la tabla <nombre_tabla>
con campos
    <campo1> en <tipo1>,
    ..
referenciado con <tabla_referenciada> -- (Para la creación de llaves foraneas, opcional)
termina la tabla
-- Genera el SQL:
CREATE TABLE <nombre_tabla>(
    id_<nombre_tabla> INT PRIMARY KEY AUTO INCREMENT, 
    <campo1> <tipo1>, 
    .., 
    (fk_id_<tabla_referenciada> INT, 
    FOREIGN KEY (fk_id_<tabla_referenciada>) 
        REFERENCES <tabla_referenciada>(id_<tabla_referenciada>) ON DELETE CASCADE))

-- El input: 
inserta en la tabla <nombre_tabla>
    <campo1> con <valor1>
    ..
termina la insercion
-- Genera el SQL:
INSERT INTO <nombre_tabla>(campo1, ..) 
    VALUES(valor1,..)

-- El input:
selecciona todo/<campo_1,campo_2,..> de la tabla <nombre_tabla>
-- Genera el SQL:
SELECT */<campo1>,.. FROM <nombre_tabla>

selecciona todo/<campo_1,campo_2,..> entre <tabla_1> y <tabla_2> -- (Equivalente a una consulta INNER JOIN)
-- Genera el SQL:
SELECT */<campo1>,.. FROM <tabla1> INNER JOIN <tabla2> ON <tabla1>.<id> = <tabla2>.<fk_id>

-- El input:
borra de la tabla <nombre_tabla> cuando <campo> sea igual/mayor/menor/diferente a <valor>
-- Genera el SQL:
DELETE FROM <nombre_tabla> WHERE <campo> ==,<,>,!= <valor>
```

Los tipos de datos que se manejan con este lenguaje son:

1. INT: numero, con formato 2,344,3233,etc.
2. TEXT: texto, con formato 'abc..'
3. DATE: fecha, con formato 'AAAA-MM-DD'
4. TIME: tiempo, con formato 'HH:MM:SS'

La validación de la existencia de la base de datos, tabla dentro de una base de datos, campo dentro de una tabla, se realiza dentro de la gramatica `lenguaje.g4`. Para ello se utilizan clases de apoyo `BaseDatos.java` la cual almacena el nombre de la base de datos y una lista de tablas; `DBTabla.java` almacenando el nombre de la tabla y una lista de los campos que contiene; finalmente `DBCampo.java` almacena el nombre y tipo de campo asi como una lista conteniendo los valores ingresados.
