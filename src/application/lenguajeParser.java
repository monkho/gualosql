// Generated from lenguaje.g4 by ANTLR 4.13.2

package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class lenguajeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		TEXTO=32, NUMERO=33, FECHA=34, TIEMPO=35, DATE=36, TIME=37, NUMBER=38, 
		STRING=39, IDENTIFIER=40, NEWLINE=41, WS=42;
	public static final int
		RULE_prog = 0, RULE_statement = 1, RULE_crudStmt = 2, RULE_extraStmt = 3, 
		RULE_createbaseDatostmt = 4, RULE_createTableStmt = 5, RULE_fieldList = 6, 
		RULE_field = 7, RULE_foreignRef = 8, RULE_insertStmt = 9, RULE_insertFieldList = 10, 
		RULE_insertField = 11, RULE_selectStmt = 12, RULE_selectFields = 13, RULE_fieldListSpec = 14, 
		RULE_selectFrom = 15, RULE_whereClause = 16, RULE_condiciones = 17, RULE_deleteStmt = 18, 
		RULE_deleteTableStmt = 19, RULE_deleteDBStmt = 20, RULE_updateStmt = 21, 
		RULE_updateFieldList = 22, RULE_updateField = 23, RULE_finishDB = 24, 
		RULE_literal = 25, RULE_dataType = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "statement", "crudStmt", "extraStmt", "createbaseDatostmt", "createTableStmt", 
			"fieldList", "field", "foreignRef", "insertStmt", "insertFieldList", 
			"insertField", "selectStmt", "selectFields", "fieldListSpec", "selectFrom", 
			"whereClause", "condiciones", "deleteStmt", "deleteTableStmt", "deleteDBStmt", 
			"updateStmt", "updateFieldList", "updateField", "finishDB", "literal", 
			"dataType"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'crea'", "'la'", "'base'", "'de'", "'datos'", "'tabla'", "'con'", 
			"'campos'", "'termina'", "'en'", "','", "'referenciado'", "'inserta'", 
			"'insercion'", "'valor'", "'selecciona'", "'todo'", "'entre'", "'y'", 
			"'cuando'", "'el'", "'campo'", "'sea'", "'igual'", "'a'", "'mayor'", 
			"'menor'", "'o'", "'distinto'", "'borra'", "'modifica'", "'texto'", "'numero'", 
			"'fecha'", "'tiempo'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "TEXTO", "NUMERO", "FECHA", 
			"TIEMPO", "DATE", "TIME", "NUMBER", "STRING", "IDENTIFIER", "NEWLINE", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "lenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	HashMap baseDatos = new HashMap();
	BaseDatos baseDatosActual;
	BDTabla tablaActual;
	List<String> camposActuales = new ArrayList<>();
	List<String> valores = new ArrayList<>();
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
	        if(db.getTablas().contains(t)) {
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
	    for(String c : campos)
	        res += c + ',';
	    res += '\b';
	    return res;
	}
	public String getValores(List<String> valores) {
	    String res = "";
	    for(String c : valores)
	        res += c + ',';
	    res += '\b';
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

	public lenguajeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(lenguajeParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(54);
				statement();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(EOF);

			        for (Object bd: baseDatos.values()) {
			            if(bd != null)
			                System.out.println(bd.toString());
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public CreatebaseDatostmtContext createbaseDatostmt() {
			return getRuleContext(CreatebaseDatostmtContext.class,0);
		}
		public FinishDBContext finishDB() {
			return getRuleContext(FinishDBContext.class,0);
		}
		public List<CrudStmtContext> crudStmt() {
			return getRuleContexts(CrudStmtContext.class);
		}
		public CrudStmtContext crudStmt(int i) {
			return getRuleContext(CrudStmtContext.class,i);
		}
		public ExtraStmtContext extraStmt() {
			return getRuleContext(ExtraStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			createbaseDatostmt();
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				crudStmt();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 3221299202L) != 0) );
			setState(69);
			finishDB();
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(70);
				extraStmt();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CrudStmtContext extends ParserRuleContext {
		public CreateTableStmtContext createTableStmt() {
			return getRuleContext(CreateTableStmtContext.class,0);
		}
		public InsertStmtContext insertStmt() {
			return getRuleContext(InsertStmtContext.class,0);
		}
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public DeleteStmtContext deleteStmt() {
			return getRuleContext(DeleteStmtContext.class,0);
		}
		public UpdateStmtContext updateStmt() {
			return getRuleContext(UpdateStmtContext.class,0);
		}
		public DeleteDBStmtContext deleteDBStmt() {
			return getRuleContext(DeleteDBStmtContext.class,0);
		}
		public DeleteTableStmtContext deleteTableStmt() {
			return getRuleContext(DeleteTableStmtContext.class,0);
		}
		public CrudStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_crudStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterCrudStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitCrudStmt(this);
		}
	}

	public final CrudStmtContext crudStmt() throws RecognitionException {
		CrudStmtContext _localctx = new CrudStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_crudStmt);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				createTableStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				insertStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(75);
				selectStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(76);
				deleteStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(77);
				updateStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(78);
				deleteDBStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(79);
				deleteTableStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtraStmtContext extends ParserRuleContext {
		public DeleteDBStmtContext deleteDBStmt() {
			return getRuleContext(DeleteDBStmtContext.class,0);
		}
		public ExtraStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extraStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterExtraStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitExtraStmt(this);
		}
	}

	public final ExtraStmtContext extraStmt() throws RecognitionException {
		ExtraStmtContext _localctx = new ExtraStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_extraStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			deleteDBStmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreatebaseDatostmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public CreatebaseDatostmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createbaseDatostmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterCreatebaseDatostmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitCreatebaseDatostmt(this);
		}
	}

	public final CreatebaseDatostmtContext createbaseDatostmt() throws RecognitionException {
		CreatebaseDatostmtContext _localctx = new CreatebaseDatostmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_createbaseDatostmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__0);
			setState(85);
			match(T__1);
			setState(86);
			match(T__2);
			setState(87);
			match(T__3);
			setState(88);
			match(T__4);
			setState(89);
			((CreatebaseDatostmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(90);
			match(NEWLINE);

			        baseDatosActual = new BaseDatos((((CreatebaseDatostmtContext)_localctx).IDENTIFIER!=null?((CreatebaseDatostmtContext)_localctx).IDENTIFIER.getText():null));
			        pushDB((((CreatebaseDatostmtContext)_localctx).IDENTIFIER!=null?((CreatebaseDatostmtContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			        if(!error) {
			            query += "CREATE DATABASE " + (((CreatebaseDatostmtContext)_localctx).IDENTIFIER!=null?((CreatebaseDatostmtContext)_localctx).IDENTIFIER.getText():null) + ';' + '\n';
			            query += "USE DATABASE " + (((CreatebaseDatostmtContext)_localctx).IDENTIFIER!=null?((CreatebaseDatostmtContext)_localctx).IDENTIFIER.getText():null) + ";\n";
			            printQuery(query);
			            compiled.add(query);
			            query = "";
			        }
			        error = false;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableStmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public ForeignRefContext foreignRef;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public FieldListContext fieldList() {
			return getRuleContext(FieldListContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public ForeignRefContext foreignRef() {
			return getRuleContext(ForeignRefContext.class,0);
		}
		public CreateTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterCreateTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitCreateTableStmt(this);
		}
	}

	public final CreateTableStmtContext createTableStmt() throws RecognitionException {
		CreateTableStmtContext _localctx = new CreateTableStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_createTableStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__0);
			setState(94);
			match(T__1);
			setState(95);
			match(T__5);
			setState(96);
			((CreateTableStmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);

			        BDTabla t = new BDTabla((((CreateTableStmtContext)_localctx).IDENTIFIER!=null?((CreateTableStmtContext)_localctx).IDENTIFIER.getText():null));
			        t.addCampo(new BDCampo("id_" + (((CreateTableStmtContext)_localctx).IDENTIFIER!=null?((CreateTableStmtContext)_localctx).IDENTIFIER.getText():null), "PRIMARY KEY AUTOINCREMENT INT NOT NULL"));
			        pushTabla(baseDatosActual, t); 
			      
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(98);
				match(NEWLINE);
				}
			}


			        tablaActual = findTabla((((CreateTableStmtContext)_localctx).IDENTIFIER!=null?((CreateTableStmtContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			      
			setState(102);
			match(T__6);
			setState(103);
			match(T__7);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(104);
				match(NEWLINE);
				}
			}

			setState(107);
			fieldList();

			        if(!error) {
			            query += "CREATE TABLE " + (((CreateTableStmtContext)_localctx).IDENTIFIER!=null?((CreateTableStmtContext)_localctx).IDENTIFIER.getText():null) + "(\n"
			                + "id_" + (((CreateTableStmtContext)_localctx).IDENTIFIER!=null?((CreateTableStmtContext)_localctx).IDENTIFIER.getText():null) + " PRIMARY KEY AUTOINCREMENT INT NOT NULL,\n";
			            for(BDCampo c : tablaActual.getCampos()) {
			                String tipo = c.getNombreTipo();
			                String nombre = c.getNombreCampo();
			                switch(tipo) {
			                    case "texto":
			                        query += nombre + " TEXT, ";
			                        break;
			                    case "numero":
			                        query += nombre + " INT, ";
			                        break;
			                    case "fecha":
			                        query += nombre + " DATE, ";
			                        break;
			                    case "tiempo":
			                        query += nombre + " TIME, ";
			                        break;
			                }
			            }
			        }
			        error = false;
			      
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(109);
				((CreateTableStmtContext)_localctx).foreignRef = foreignRef();

				        if(!error && ((CreateTableStmtContext)_localctx).foreignRef.fk != null)
				            query += ((CreateTableStmtContext)_localctx).foreignRef.fk;
				      
				}
			}

			setState(114);
			match(T__8);
			setState(115);
			match(T__1);
			setState(116);
			match(T__5);
			setState(117);
			match(NEWLINE);

			        if(!error) {
			            if(query.charAt(query.length()-1) == ',' || query.charAt(query.length()-2) == ',')
			                query += "\b\b\b);\n";
			            else
			                query += ");\n";
			            printQuery(query);
			            compiled.add(query);
			            query = "";
			        }
			        tablaActual = null;
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldListContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public FieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterFieldList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitFieldList(this);
		}
	}

	public final FieldListContext fieldList() throws RecognitionException {
		FieldListContext _localctx = new FieldListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(120);
				field();
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(121);
					match(NEWLINE);
					}
				}

				}
				}
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public DataTypeContext dataType;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitField(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_field);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			((FieldContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(129);
			match(T__9);
			setState(130);
			((FieldContext)_localctx).dataType = dataType();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(131);
				match(T__10);
				}
			}


			        tablaActual.addCampo(new BDCampo((((FieldContext)_localctx).IDENTIFIER!=null?((FieldContext)_localctx).IDENTIFIER.getText():null), ((FieldContext)_localctx).dataType.t));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForeignRefContext extends ParserRuleContext {
		public String fk;
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public ForeignRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterForeignRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitForeignRef(this);
		}
	}

	public final ForeignRefContext foreignRef() throws RecognitionException {
		ForeignRefContext _localctx = new ForeignRefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_foreignRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__11);
			setState(137);
			match(T__6);
			setState(138);
			((ForeignRefContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(139);
				match(NEWLINE);
				}
			}


			        ((ForeignRefContext)_localctx).fk =  "";
			        BDTabla t = findTabla((((ForeignRefContext)_localctx).IDENTIFIER!=null?((ForeignRefContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			        String type = "";
			        if(t != null) {
			            type = "INT, FOREIGN KEY (fk_"+t.getIdTabla()+") REFERENCES " + t.getNombreTabla() + "("+t.getIdTabla()+") ON DELETE CASCADE";
			            tablaActual.addCampo(new BDCampo("fk_" + t.getIdTabla(), type));
			            ((ForeignRefContext)_localctx).fk =  "fk_" + t.getIdTabla() + " " + type;
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertStmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public InsertFieldListContext insertFieldList() {
			return getRuleContext(InsertFieldListContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public InsertStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterInsertStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitInsertStmt(this);
		}
	}

	public final InsertStmtContext insertStmt() throws RecognitionException {
		InsertStmtContext _localctx = new InsertStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_insertStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__12);
			setState(145);
			match(T__9);
			setState(146);
			match(T__1);
			setState(147);
			match(T__5);
			setState(148);
			((InsertStmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(149);
				match(NEWLINE);
				}
			}


			        tablaActual = findTabla((((InsertStmtContext)_localctx).IDENTIFIER!=null?((InsertStmtContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			      
			setState(153);
			insertFieldList();
			setState(154);
			match(T__8);
			setState(155);
			match(T__1);
			setState(156);
			match(T__13);
			setState(157);
			match(NEWLINE);

			        if(!error) {
			            query += "INSERT INTO " + (((InsertStmtContext)_localctx).IDENTIFIER!=null?((InsertStmtContext)_localctx).IDENTIFIER.getText():null) + "(" + getCampos(camposActuales) + ") \nVALUES(" + getValores(valores) + ");\n";
			            printQuery(query);
			            compiled.add(query);
			            query = "";
			        }
			        error = false;
			        camposActuales = new ArrayList<>();
			        valores = new ArrayList<>();
			      
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertFieldListContext extends ParserRuleContext {
		public List<BDCampo> campos;
		public List<InsertFieldContext> insertField() {
			return getRuleContexts(InsertFieldContext.class);
		}
		public InsertFieldContext insertField(int i) {
			return getRuleContext(InsertFieldContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public InsertFieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertFieldList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterInsertFieldList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitInsertFieldList(this);
		}
	}

	public final InsertFieldListContext insertFieldList() throws RecognitionException {
		InsertFieldListContext _localctx = new InsertFieldListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_insertFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160);
				insertField();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(161);
					match(NEWLINE);
					}
				}

				}
				}
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertFieldContext extends ParserRuleContext {
		public BDCampo campo;
		public Token IDENTIFIER;
		public LiteralContext literal;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public InsertFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterInsertField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitInsertField(this);
		}
	}

	public final InsertFieldContext insertField() throws RecognitionException {
		InsertFieldContext _localctx = new InsertFieldContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_insertField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			((InsertFieldContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(169);
			match(T__6);
			setState(170);
			match(T__14);
			setState(171);
			((InsertFieldContext)_localctx).literal = literal();
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(172);
				match(T__10);
				}
			}


			        camposActuales.add((((InsertFieldContext)_localctx).IDENTIFIER!=null?((InsertFieldContext)_localctx).IDENTIFIER.getText():null));
			        valores.add(((InsertFieldContext)_localctx).literal.t);
			        ((InsertFieldContext)_localctx).campo =  findCampo((((InsertFieldContext)_localctx).IDENTIFIER!=null?((InsertFieldContext)_localctx).IDENTIFIER.getText():null), tablaActual);
			        if(_localctx.campo != null) {
			            _localctx.campo.addValor(((InsertFieldContext)_localctx).literal.t);
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStmtContext extends ParserRuleContext {
		public SelectFieldsContext selectFields;
		public SelectFromContext selectFrom;
		public WhereClauseContext whereClause;
		public SelectFieldsContext selectFields() {
			return getRuleContext(SelectFieldsContext.class,0);
		}
		public SelectFromContext selectFrom() {
			return getRuleContext(SelectFromContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public SelectStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitSelectStmt(this);
		}
	}

	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_selectStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__15);
			setState(178);
			((SelectStmtContext)_localctx).selectFields = selectFields();

			        if(!error) {
			            if(((SelectStmtContext)_localctx).selectFields.t != null){
			                query += "SELECT * FROM ";
			            }
			            else {
			                query += "SELECT " + getCampos(camposActuales) + " FROM ";
			            }
			       }
			        error = false;
			    
			setState(180);
			((SelectStmtContext)_localctx).selectFrom = selectFrom();

			        if(!error) {
			            if(((SelectStmtContext)_localctx).selectFrom.tabla == null)
			                query += tablaActual.getNombreTabla() + ' ';
			            else {
			                BDTabla t1 = ((SelectStmtContext)_localctx).selectFrom.tabla.get(0);
			                BDTabla t2 = ((SelectStmtContext)_localctx).selectFrom.tabla.get(1);
			                query += t1.getNombreTabla() + " INNER JOIN " + t2.getNombreTabla() + " ON " + t1.getNombreTabla() + '.' + t1.getIdTabla() + " = " + t2.getNombreTabla() + '.' + t2.getForeingKey();
			            }
			        }
			    
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(182);
				((SelectStmtContext)_localctx).whereClause = whereClause();

				        if(!error) {
				            query += "WHERE " + ((SelectStmtContext)_localctx).whereClause.c ;
				        }
				    
				}
			}

			setState(187);
			match(NEWLINE);

			        if(!error) {
			            query += ";\n";
			            compiled.add(query);
			            printQuery(query);
			        }
			        query = "";
			        camposActuales = new ArrayList<>();
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectFieldsContext extends ParserRuleContext {
		public String t;
		public FieldListSpecContext fieldListSpec() {
			return getRuleContext(FieldListSpecContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public SelectFieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectFields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterSelectFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitSelectFields(this);
		}
	}

	public final SelectFieldsContext selectFields() throws RecognitionException {
		SelectFieldsContext _localctx = new SelectFieldsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_selectFields);
		int _la;
		try {
			setState(198);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				match(T__16);
				 ((SelectFieldsContext)_localctx).t =  "todo"; 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				fieldListSpec();
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(193);
					match(NEWLINE);
					}
				}

				 ((SelectFieldsContext)_localctx).t =  null; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldListSpecContext extends ParserRuleContext {
		public List<String> campos;
		public Token IDENTIFIER;
		public List<TerminalNode> IDENTIFIER() { return getTokens(lenguajeParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(lenguajeParser.IDENTIFIER, i);
		}
		public FieldListSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldListSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterFieldListSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitFieldListSpec(this);
		}
	}

	public final FieldListSpecContext fieldListSpec() throws RecognitionException {
		FieldListSpecContext _localctx = new FieldListSpecContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_fieldListSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(200);
				((FieldListSpecContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(201);
					match(T__10);
					}
				}

				}
				}
				setState(206); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );

			        camposActuales.add((((FieldListSpecContext)_localctx).IDENTIFIER!=null?((FieldListSpecContext)_localctx).IDENTIFIER.getText():null));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectFromContext extends ParserRuleContext {
		public List<BDTabla> tabla;
		public Token IDENTIFIER;
		public Token id1;
		public Token id2;
		public List<TerminalNode> IDENTIFIER() { return getTokens(lenguajeParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(lenguajeParser.IDENTIFIER, i);
		}
		public SelectFromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectFrom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterSelectFrom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitSelectFrom(this);
		}
	}

	public final SelectFromContext selectFrom() throws RecognitionException {
		SelectFromContext _localctx = new SelectFromContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_selectFrom);
		try {
			setState(220);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				match(T__3);
				setState(211);
				match(T__1);
				setState(212);
				match(T__5);
				setState(213);
				((SelectFromContext)_localctx).IDENTIFIER = match(IDENTIFIER);

				        tablaActual = findTabla((((SelectFromContext)_localctx).IDENTIFIER!=null?((SelectFromContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
				        for(String c : camposActuales) {
				            findCampo(c, tablaActual);
				        }
				        ((SelectFromContext)_localctx).tabla =  null;
				    
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				match(T__17);
				setState(216);
				((SelectFromContext)_localctx).id1 = match(IDENTIFIER);
				setState(217);
				match(T__18);
				setState(218);
				((SelectFromContext)_localctx).id2 = match(IDENTIFIER);

				        BDTabla t1 = findTabla((((SelectFromContext)_localctx).id1!=null?((SelectFromContext)_localctx).id1.getText():null), baseDatosActual);
				        BDTabla t2 = findTabla((((SelectFromContext)_localctx).id2!=null?((SelectFromContext)_localctx).id2.getText():null), baseDatosActual);
				        for(String c : camposActuales) {
				            findCampo(c, t1);
				            findCampo(c, t2);
				        }
				        ((SelectFromContext)_localctx).tabla =  new ArrayList();
				        _localctx.tabla.add(t1);
				        _localctx.tabla.add(t2);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public String c;
		public String v;
		public String cond;
		public Token IDENTIFIER;
		public CondicionesContext condiciones;
		public LiteralContext literal;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public CondicionesContext condiciones() {
			return getRuleContext(CondicionesContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitWhereClause(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_whereClause);
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(T__19);
				setState(223);
				((WhereClauseContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(224);
				((WhereClauseContext)_localctx).condiciones = condiciones();
				setState(225);
				((WhereClauseContext)_localctx).literal = literal();

				        ((WhereClauseContext)_localctx).c =  (((WhereClauseContext)_localctx).IDENTIFIER!=null?((WhereClauseContext)_localctx).IDENTIFIER.getText():null);
				        ((WhereClauseContext)_localctx).v =  ((WhereClauseContext)_localctx).literal.t;
				        ((WhereClauseContext)_localctx).cond =  ((WhereClauseContext)_localctx).condiciones.cond;
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				match(T__19);
				setState(229);
				match(T__20);
				setState(230);
				match(T__21);
				setState(231);
				((WhereClauseContext)_localctx).IDENTIFIER = match(IDENTIFIER);
				setState(232);
				((WhereClauseContext)_localctx).condiciones = condiciones();
				setState(233);
				((WhereClauseContext)_localctx).literal = literal();

				        ((WhereClauseContext)_localctx).c =  (((WhereClauseContext)_localctx).IDENTIFIER!=null?((WhereClauseContext)_localctx).IDENTIFIER.getText():null);
				        ((WhereClauseContext)_localctx).v =  ((WhereClauseContext)_localctx).literal.t;
				        ((WhereClauseContext)_localctx).cond =  ((WhereClauseContext)_localctx).condiciones.cond;
				    
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondicionesContext extends ParserRuleContext {
		public String cond;
		public CondicionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condiciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterCondiciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitCondiciones(this);
		}
	}

	public final CondicionesContext condiciones() throws RecognitionException {
		CondicionesContext _localctx = new CondicionesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_condiciones);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				match(T__22);
				setState(239);
				match(T__23);
				setState(240);
				match(T__24);
				 ((CondicionesContext)_localctx).cond =  "="; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(T__22);
				setState(243);
				match(T__25);
				setState(244);
				match(T__24);
				 ((CondicionesContext)_localctx).cond =  ">"; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(246);
				match(T__22);
				setState(247);
				match(T__26);
				setState(248);
				match(T__24);
				 ((CondicionesContext)_localctx).cond =  "<"; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(250);
				match(T__22);
				setState(251);
				match(T__26);
				setState(252);
				match(T__27);
				setState(253);
				match(T__23);
				setState(254);
				match(T__24);
				 ((CondicionesContext)_localctx).cond =  "<="; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(256);
				match(T__22);
				setState(257);
				match(T__25);
				setState(258);
				match(T__27);
				setState(259);
				match(T__23);
				setState(260);
				match(T__24);
				 ((CondicionesContext)_localctx).cond =  ">="; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(262);
				match(T__22);
				setState(263);
				match(T__28);
				setState(264);
				match(T__3);
				 ((CondicionesContext)_localctx).cond =  "<>"; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteStmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public WhereClauseContext whereClause;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public DeleteStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterDeleteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitDeleteStmt(this);
		}
	}

	public final DeleteStmtContext deleteStmt() throws RecognitionException {
		DeleteStmtContext _localctx = new DeleteStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_deleteStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__29);
			setState(269);
			match(T__3);
			setState(270);
			match(T__1);
			setState(271);
			match(T__5);
			setState(272);
			((DeleteStmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(273);
			((DeleteStmtContext)_localctx).whereClause = whereClause();
			setState(274);
			match(NEWLINE);

			        tablaActual = findTabla((((DeleteStmtContext)_localctx).IDENTIFIER!=null?((DeleteStmtContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			        if(!error) {
			            query += "DELETE FROM " + (((DeleteStmtContext)_localctx).IDENTIFIER!=null?((DeleteStmtContext)_localctx).IDENTIFIER.getText():null) + " WHERE " + ((DeleteStmtContext)_localctx).whereClause.c + " " + ((DeleteStmtContext)_localctx).whereClause.cond + " " + ((DeleteStmtContext)_localctx).whereClause.v + ";\n";
			            printQuery(query);
			            compiled.add(query);
			            query = "";
			        }
			        error = false;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteTableStmtContext extends ParserRuleContext {
		public Token tabla;
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public DeleteTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterDeleteTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitDeleteTableStmt(this);
		}
	}

	public final DeleteTableStmtContext deleteTableStmt() throws RecognitionException {
		DeleteTableStmtContext _localctx = new DeleteTableStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_deleteTableStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T__29);
			setState(278);
			match(T__1);
			setState(279);
			match(T__5);
			setState(280);
			((DeleteTableStmtContext)_localctx).tabla = match(IDENTIFIER);
			setState(281);
			match(NEWLINE);

			        removeTabla((((DeleteTableStmtContext)_localctx).tabla!=null?((DeleteTableStmtContext)_localctx).tabla.getText():null), baseDatosActual);
			        if(!error) {
			            query += "DROP TABLE IF EXISTS " + (((DeleteTableStmtContext)_localctx).tabla!=null?((DeleteTableStmtContext)_localctx).tabla.getText():null) + ";\n";
			            printQuery(query);
			            query = "";
			        }
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteDBStmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public DeleteDBStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteDBStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterDeleteDBStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitDeleteDBStmt(this);
		}
	}

	public final DeleteDBStmtContext deleteDBStmt() throws RecognitionException {
		DeleteDBStmtContext _localctx = new DeleteDBStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_deleteDBStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(T__29);
			setState(285);
			match(T__1);
			setState(286);
			match(T__2);
			setState(287);
			match(T__3);
			setState(288);
			match(T__4);
			setState(289);
			((DeleteDBStmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(290);
			match(NEWLINE);

			        removeDB((((DeleteDBStmtContext)_localctx).IDENTIFIER!=null?((DeleteDBStmtContext)_localctx).IDENTIFIER.getText():null));
			        if(!error) {
			            query += "DROP DATABASE IF EXISTS " + (((DeleteDBStmtContext)_localctx).IDENTIFIER!=null?((DeleteDBStmtContext)_localctx).IDENTIFIER.getText():null) + ";\n";
			            printQuery(query);
			            compiled.add(query);
			            query = "";
			        }
			        error = false;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateStmtContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public UpdateFieldListContext updateFieldList() {
			return getRuleContext(UpdateFieldListContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public UpdateStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterUpdateStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitUpdateStmt(this);
		}
	}

	public final UpdateStmtContext updateStmt() throws RecognitionException {
		UpdateStmtContext _localctx = new UpdateStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_updateStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(T__30);
			setState(294);
			match(T__3);
			setState(295);
			match(T__1);
			setState(296);
			match(T__5);
			setState(297);
			((UpdateStmtContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(298);
				match(NEWLINE);
				}
			}


			        tablaActual = findTabla((((UpdateStmtContext)_localctx).IDENTIFIER!=null?((UpdateStmtContext)_localctx).IDENTIFIER.getText():null), baseDatosActual);
			    
			setState(302);
			updateFieldList();
			setState(303);
			whereClause();
			setState(304);
			match(NEWLINE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateFieldListContext extends ParserRuleContext {
		public List<BDCampo> campos;
		public List<UpdateFieldContext> updateField() {
			return getRuleContexts(UpdateFieldContext.class);
		}
		public UpdateFieldContext updateField(int i) {
			return getRuleContext(UpdateFieldContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(lenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(lenguajeParser.NEWLINE, i);
		}
		public UpdateFieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateFieldList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterUpdateFieldList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitUpdateFieldList(this);
		}
	}

	public final UpdateFieldListContext updateFieldList() throws RecognitionException {
		UpdateFieldListContext _localctx = new UpdateFieldListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_updateFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(306);
				updateField();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(307);
					match(NEWLINE);
					}
				}

				}
				}
				setState(312); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateFieldContext extends ParserRuleContext {
		public BDCampo campo;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public UpdateFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterUpdateField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitUpdateField(this);
		}
	}

	public final UpdateFieldContext updateField() throws RecognitionException {
		UpdateFieldContext _localctx = new UpdateFieldContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_updateField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(IDENTIFIER);
			setState(315);
			match(T__6);
			setState(316);
			literal();
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(317);
				match(T__10);
				}
			}


			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FinishDBContext extends ParserRuleContext {
		public Token IDENTIFIER;
		public TerminalNode IDENTIFIER() { return getToken(lenguajeParser.IDENTIFIER, 0); }
		public TerminalNode NEWLINE() { return getToken(lenguajeParser.NEWLINE, 0); }
		public FinishDBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finishDB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterFinishDB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitFinishDB(this);
		}
	}

	public final FinishDBContext finishDB() throws RecognitionException {
		FinishDBContext _localctx = new FinishDBContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_finishDB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			match(T__8);
			setState(323);
			match(T__1);
			setState(324);
			match(T__2);
			setState(325);
			match(T__3);
			setState(326);
			match(T__4);
			setState(327);
			((FinishDBContext)_localctx).IDENTIFIER = match(IDENTIFIER);
			setState(328);
			match(NEWLINE);

			        if(findDB((((FinishDBContext)_localctx).IDENTIFIER!=null?((FinishDBContext)_localctx).IDENTIFIER.getText():null)) == baseDatosActual)
			            baseDatosActual = null;
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public String t;
		public Token STRING;
		public Token NUMBER;
		public Token DATE;
		public Token TIME;
		public TerminalNode STRING() { return getToken(lenguajeParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(lenguajeParser.NUMBER, 0); }
		public TerminalNode DATE() { return getToken(lenguajeParser.DATE, 0); }
		public TerminalNode TIME() { return getToken(lenguajeParser.TIME, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_literal);
		try {
			setState(339);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				((LiteralContext)_localctx).STRING = match(STRING);
				 ((LiteralContext)_localctx).t =  (((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getText():null); 
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
				((LiteralContext)_localctx).NUMBER = match(NUMBER);
				 ((LiteralContext)_localctx).t =  (((LiteralContext)_localctx).NUMBER!=null?((LiteralContext)_localctx).NUMBER.getText():null); 
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(335);
				((LiteralContext)_localctx).DATE = match(DATE);
				 ((LiteralContext)_localctx).t =  (((LiteralContext)_localctx).DATE!=null?((LiteralContext)_localctx).DATE.getText():null); 
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 4);
				{
				setState(337);
				((LiteralContext)_localctx).TIME = match(TIME);
				 ((LiteralContext)_localctx).t =  (((LiteralContext)_localctx).TIME!=null?((LiteralContext)_localctx).TIME.getText():null); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataTypeContext extends ParserRuleContext {
		public String t;
		public Token TEXTO;
		public Token NUMERO;
		public Token FECHA;
		public Token TIEMPO;
		public TerminalNode TEXTO() { return getToken(lenguajeParser.TEXTO, 0); }
		public TerminalNode NUMERO() { return getToken(lenguajeParser.NUMERO, 0); }
		public TerminalNode FECHA() { return getToken(lenguajeParser.FECHA, 0); }
		public TerminalNode TIEMPO() { return getToken(lenguajeParser.TIEMPO, 0); }
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof lenguajeListener ) ((lenguajeListener)listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_dataType);
		try {
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TEXTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				((DataTypeContext)_localctx).TEXTO = match(TEXTO);

				        ((DataTypeContext)_localctx).t =  (((DataTypeContext)_localctx).TEXTO!=null?((DataTypeContext)_localctx).TEXTO.getText():null);
				    
				}
				break;
			case NUMERO:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				((DataTypeContext)_localctx).NUMERO = match(NUMERO);

				        ((DataTypeContext)_localctx).t =  (((DataTypeContext)_localctx).NUMERO!=null?((DataTypeContext)_localctx).NUMERO.getText():null);
				    
				}
				break;
			case FECHA:
				enterOuterAlt(_localctx, 3);
				{
				setState(345);
				((DataTypeContext)_localctx).FECHA = match(FECHA);

				        ((DataTypeContext)_localctx).t =  (((DataTypeContext)_localctx).FECHA!=null?((DataTypeContext)_localctx).FECHA.getText():null);
				    
				}
				break;
			case TIEMPO:
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				((DataTypeContext)_localctx).TIEMPO = match(TIEMPO);

				        ((DataTypeContext)_localctx).t =  (((DataTypeContext)_localctx).TIEMPO!=null?((DataTypeContext)_localctx).TIEMPO.getText():null);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001*\u0160\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0005\u0000"+
		"8\b\u0000\n\u0000\f\u0000;\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0004\u0001B\b\u0001\u000b\u0001\f\u0001C\u0001"+
		"\u0001\u0001\u0001\u0003\u0001H\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002Q\b"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005d\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005j\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005q\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0003\u0006{\b"+
		"\u0006\u0004\u0006}\b\u0006\u000b\u0006\f\u0006~\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u0085\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u008d\b\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0097\b\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0003"+
		"\n\u00a3\b\n\u0004\n\u00a5\b\n\u000b\n\f\n\u00a6\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00ae\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00ba\b\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00c3\b\r\u0001\r\u0001\r\u0003\r\u00c7\b\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00cb\b\u000e\u0004\u000e\u00cd\b\u000e\u000b\u000e"+
		"\f\u000e\u00ce\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u00dd\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00ed\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u010b\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0003\u0015\u012c\b\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0135\b\u0016\u0004\u0016\u0137\b\u0016\u000b\u0016\f\u0016\u0138\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u013f\b\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u0154\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u015e"+
		"\b\u001a\u0001\u001a\u0000\u0000\u001b\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.024\u0000\u0000"+
		"\u016e\u00009\u0001\u0000\u0000\u0000\u0002?\u0001\u0000\u0000\u0000\u0004"+
		"P\u0001\u0000\u0000\u0000\u0006R\u0001\u0000\u0000\u0000\bT\u0001\u0000"+
		"\u0000\u0000\n]\u0001\u0000\u0000\u0000\f|\u0001\u0000\u0000\u0000\u000e"+
		"\u0080\u0001\u0000\u0000\u0000\u0010\u0088\u0001\u0000\u0000\u0000\u0012"+
		"\u0090\u0001\u0000\u0000\u0000\u0014\u00a4\u0001\u0000\u0000\u0000\u0016"+
		"\u00a8\u0001\u0000\u0000\u0000\u0018\u00b1\u0001\u0000\u0000\u0000\u001a"+
		"\u00c6\u0001\u0000\u0000\u0000\u001c\u00cc\u0001\u0000\u0000\u0000\u001e"+
		"\u00dc\u0001\u0000\u0000\u0000 \u00ec\u0001\u0000\u0000\u0000\"\u010a"+
		"\u0001\u0000\u0000\u0000$\u010c\u0001\u0000\u0000\u0000&\u0115\u0001\u0000"+
		"\u0000\u0000(\u011c\u0001\u0000\u0000\u0000*\u0125\u0001\u0000\u0000\u0000"+
		",\u0136\u0001\u0000\u0000\u0000.\u013a\u0001\u0000\u0000\u00000\u0142"+
		"\u0001\u0000\u0000\u00002\u0153\u0001\u0000\u0000\u00004\u015d\u0001\u0000"+
		"\u0000\u000068\u0003\u0002\u0001\u000076\u0001\u0000\u0000\u00008;\u0001"+
		"\u0000\u0000\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000"+
		":<\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000<=\u0005\u0000\u0000"+
		"\u0001=>\u0006\u0000\uffff\uffff\u0000>\u0001\u0001\u0000\u0000\u0000"+
		"?A\u0003\b\u0004\u0000@B\u0003\u0004\u0002\u0000A@\u0001\u0000\u0000\u0000"+
		"BC\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000"+
		"\u0000DE\u0001\u0000\u0000\u0000EG\u00030\u0018\u0000FH\u0003\u0006\u0003"+
		"\u0000GF\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000H\u0003\u0001"+
		"\u0000\u0000\u0000IQ\u0003\n\u0005\u0000JQ\u0003\u0012\t\u0000KQ\u0003"+
		"\u0018\f\u0000LQ\u0003$\u0012\u0000MQ\u0003*\u0015\u0000NQ\u0003(\u0014"+
		"\u0000OQ\u0003&\u0013\u0000PI\u0001\u0000\u0000\u0000PJ\u0001\u0000\u0000"+
		"\u0000PK\u0001\u0000\u0000\u0000PL\u0001\u0000\u0000\u0000PM\u0001\u0000"+
		"\u0000\u0000PN\u0001\u0000\u0000\u0000PO\u0001\u0000\u0000\u0000Q\u0005"+
		"\u0001\u0000\u0000\u0000RS\u0003(\u0014\u0000S\u0007\u0001\u0000\u0000"+
		"\u0000TU\u0005\u0001\u0000\u0000UV\u0005\u0002\u0000\u0000VW\u0005\u0003"+
		"\u0000\u0000WX\u0005\u0004\u0000\u0000XY\u0005\u0005\u0000\u0000YZ\u0005"+
		"(\u0000\u0000Z[\u0005)\u0000\u0000[\\\u0006\u0004\uffff\uffff\u0000\\"+
		"\t\u0001\u0000\u0000\u0000]^\u0005\u0001\u0000\u0000^_\u0005\u0002\u0000"+
		"\u0000_`\u0005\u0006\u0000\u0000`a\u0005(\u0000\u0000ac\u0006\u0005\uffff"+
		"\uffff\u0000bd\u0005)\u0000\u0000cb\u0001\u0000\u0000\u0000cd\u0001\u0000"+
		"\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0006\u0005\uffff\uffff\u0000"+
		"fg\u0005\u0007\u0000\u0000gi\u0005\b\u0000\u0000hj\u0005)\u0000\u0000"+
		"ih\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000kl\u0003\f\u0006\u0000lp\u0006\u0005\uffff\uffff\u0000mn\u0003\u0010"+
		"\b\u0000no\u0006\u0005\uffff\uffff\u0000oq\u0001\u0000\u0000\u0000pm\u0001"+
		"\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000"+
		"rs\u0005\t\u0000\u0000st\u0005\u0002\u0000\u0000tu\u0005\u0006\u0000\u0000"+
		"uv\u0005)\u0000\u0000vw\u0006\u0005\uffff\uffff\u0000w\u000b\u0001\u0000"+
		"\u0000\u0000xz\u0003\u000e\u0007\u0000y{\u0005)\u0000\u0000zy\u0001\u0000"+
		"\u0000\u0000z{\u0001\u0000\u0000\u0000{}\u0001\u0000\u0000\u0000|x\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"~\u007f\u0001\u0000\u0000\u0000\u007f\r\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0005(\u0000\u0000\u0081\u0082\u0005\n\u0000\u0000\u0082\u0084"+
		"\u00034\u001a\u0000\u0083\u0085\u0005\u000b\u0000\u0000\u0084\u0083\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0006\u0007\uffff\uffff\u0000\u0087\u000f"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0005\f\u0000\u0000\u0089\u008a\u0005"+
		"\u0007\u0000\u0000\u008a\u008c\u0005(\u0000\u0000\u008b\u008d\u0005)\u0000"+
		"\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000"+
		"\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0006\b\uffff\uffff"+
		"\u0000\u008f\u0011\u0001\u0000\u0000\u0000\u0090\u0091\u0005\r\u0000\u0000"+
		"\u0091\u0092\u0005\n\u0000\u0000\u0092\u0093\u0005\u0002\u0000\u0000\u0093"+
		"\u0094\u0005\u0006\u0000\u0000\u0094\u0096\u0005(\u0000\u0000\u0095\u0097"+
		"\u0005)\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0006"+
		"\t\uffff\uffff\u0000\u0099\u009a\u0003\u0014\n\u0000\u009a\u009b\u0005"+
		"\t\u0000\u0000\u009b\u009c\u0005\u0002\u0000\u0000\u009c\u009d\u0005\u000e"+
		"\u0000\u0000\u009d\u009e\u0005)\u0000\u0000\u009e\u009f\u0006\t\uffff"+
		"\uffff\u0000\u009f\u0013\u0001\u0000\u0000\u0000\u00a0\u00a2\u0003\u0016"+
		"\u000b\u0000\u00a1\u00a3\u0005)\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a7\u0015\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005(\u0000\u0000"+
		"\u00a9\u00aa\u0005\u0007\u0000\u0000\u00aa\u00ab\u0005\u000f\u0000\u0000"+
		"\u00ab\u00ad\u00032\u0019\u0000\u00ac\u00ae\u0005\u000b\u0000\u0000\u00ad"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0006\u000b\uffff\uffff\u0000"+
		"\u00b0\u0017\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u0010\u0000\u0000"+
		"\u00b2\u00b3\u0003\u001a\r\u0000\u00b3\u00b4\u0006\f\uffff\uffff\u0000"+
		"\u00b4\u00b5\u0003\u001e\u000f\u0000\u00b5\u00b9\u0006\f\uffff\uffff\u0000"+
		"\u00b6\u00b7\u0003 \u0010\u0000\u00b7\u00b8\u0006\f\uffff\uffff\u0000"+
		"\u00b8\u00ba\u0001\u0000\u0000\u0000\u00b9\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005)\u0000\u0000\u00bc\u00bd\u0006\f\uffff\uffff\u0000"+
		"\u00bd\u0019\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u0011\u0000\u0000"+
		"\u00bf\u00c7\u0006\r\uffff\uffff\u0000\u00c0\u00c2\u0003\u001c\u000e\u0000"+
		"\u00c1\u00c3\u0005)\u0000\u0000\u00c2\u00c1\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c5\u0006\r\uffff\uffff\u0000\u00c5\u00c7\u0001\u0000\u0000\u0000\u00c6"+
		"\u00be\u0001\u0000\u0000\u0000\u00c6\u00c0\u0001\u0000\u0000\u0000\u00c7"+
		"\u001b\u0001\u0000\u0000\u0000\u00c8\u00ca\u0005(\u0000\u0000\u00c9\u00cb"+
		"\u0005\u000b\u0000\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00c8"+
		"\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cc"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d0\u00d1\u0006\u000e\uffff\uffff\u0000\u00d1"+
		"\u001d\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\u0004\u0000\u0000\u00d3"+
		"\u00d4\u0005\u0002\u0000\u0000\u00d4\u00d5\u0005\u0006\u0000\u0000\u00d5"+
		"\u00d6\u0005(\u0000\u0000\u00d6\u00dd\u0006\u000f\uffff\uffff\u0000\u00d7"+
		"\u00d8\u0005\u0012\u0000\u0000\u00d8\u00d9\u0005(\u0000\u0000\u00d9\u00da"+
		"\u0005\u0013\u0000\u0000\u00da\u00db\u0005(\u0000\u0000\u00db\u00dd\u0006"+
		"\u000f\uffff\uffff\u0000\u00dc\u00d2\u0001\u0000\u0000\u0000\u00dc\u00d7"+
		"\u0001\u0000\u0000\u0000\u00dd\u001f\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0005\u0014\u0000\u0000\u00df\u00e0\u0005(\u0000\u0000\u00e0\u00e1\u0003"+
		"\"\u0011\u0000\u00e1\u00e2\u00032\u0019\u0000\u00e2\u00e3\u0006\u0010"+
		"\uffff\uffff\u0000\u00e3\u00ed\u0001\u0000\u0000\u0000\u00e4\u00e5\u0005"+
		"\u0014\u0000\u0000\u00e5\u00e6\u0005\u0015\u0000\u0000\u00e6\u00e7\u0005"+
		"\u0016\u0000\u0000\u00e7\u00e8\u0005(\u0000\u0000\u00e8\u00e9\u0003\""+
		"\u0011\u0000\u00e9\u00ea\u00032\u0019\u0000\u00ea\u00eb\u0006\u0010\uffff"+
		"\uffff\u0000\u00eb\u00ed\u0001\u0000\u0000\u0000\u00ec\u00de\u0001\u0000"+
		"\u0000\u0000\u00ec\u00e4\u0001\u0000\u0000\u0000\u00ed!\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0005\u0017\u0000\u0000\u00ef\u00f0\u0005\u0018\u0000"+
		"\u0000\u00f0\u00f1\u0005\u0019\u0000\u0000\u00f1\u010b\u0006\u0011\uffff"+
		"\uffff\u0000\u00f2\u00f3\u0005\u0017\u0000\u0000\u00f3\u00f4\u0005\u001a"+
		"\u0000\u0000\u00f4\u00f5\u0005\u0019\u0000\u0000\u00f5\u010b\u0006\u0011"+
		"\uffff\uffff\u0000\u00f6\u00f7\u0005\u0017\u0000\u0000\u00f7\u00f8\u0005"+
		"\u001b\u0000\u0000\u00f8\u00f9\u0005\u0019\u0000\u0000\u00f9\u010b\u0006"+
		"\u0011\uffff\uffff\u0000\u00fa\u00fb\u0005\u0017\u0000\u0000\u00fb\u00fc"+
		"\u0005\u001b\u0000\u0000\u00fc\u00fd\u0005\u001c\u0000\u0000\u00fd\u00fe"+
		"\u0005\u0018\u0000\u0000\u00fe\u00ff\u0005\u0019\u0000\u0000\u00ff\u010b"+
		"\u0006\u0011\uffff\uffff\u0000\u0100\u0101\u0005\u0017\u0000\u0000\u0101"+
		"\u0102\u0005\u001a\u0000\u0000\u0102\u0103\u0005\u001c\u0000\u0000\u0103"+
		"\u0104\u0005\u0018\u0000\u0000\u0104\u0105\u0005\u0019\u0000\u0000\u0105"+
		"\u010b\u0006\u0011\uffff\uffff\u0000\u0106\u0107\u0005\u0017\u0000\u0000"+
		"\u0107\u0108\u0005\u001d\u0000\u0000\u0108\u0109\u0005\u0004\u0000\u0000"+
		"\u0109\u010b\u0006\u0011\uffff\uffff\u0000\u010a\u00ee\u0001\u0000\u0000"+
		"\u0000\u010a\u00f2\u0001\u0000\u0000\u0000\u010a\u00f6\u0001\u0000\u0000"+
		"\u0000\u010a\u00fa\u0001\u0000\u0000\u0000\u010a\u0100\u0001\u0000\u0000"+
		"\u0000\u010a\u0106\u0001\u0000\u0000\u0000\u010b#\u0001\u0000\u0000\u0000"+
		"\u010c\u010d\u0005\u001e\u0000\u0000\u010d\u010e\u0005\u0004\u0000\u0000"+
		"\u010e\u010f\u0005\u0002\u0000\u0000\u010f\u0110\u0005\u0006\u0000\u0000"+
		"\u0110\u0111\u0005(\u0000\u0000\u0111\u0112\u0003 \u0010\u0000\u0112\u0113"+
		"\u0005)\u0000\u0000\u0113\u0114\u0006\u0012\uffff\uffff\u0000\u0114%\u0001"+
		"\u0000\u0000\u0000\u0115\u0116\u0005\u001e\u0000\u0000\u0116\u0117\u0005"+
		"\u0002\u0000\u0000\u0117\u0118\u0005\u0006\u0000\u0000\u0118\u0119\u0005"+
		"(\u0000\u0000\u0119\u011a\u0005)\u0000\u0000\u011a\u011b\u0006\u0013\uffff"+
		"\uffff\u0000\u011b\'\u0001\u0000\u0000\u0000\u011c\u011d\u0005\u001e\u0000"+
		"\u0000\u011d\u011e\u0005\u0002\u0000\u0000\u011e\u011f\u0005\u0003\u0000"+
		"\u0000\u011f\u0120\u0005\u0004\u0000\u0000\u0120\u0121\u0005\u0005\u0000"+
		"\u0000\u0121\u0122\u0005(\u0000\u0000\u0122\u0123\u0005)\u0000\u0000\u0123"+
		"\u0124\u0006\u0014\uffff\uffff\u0000\u0124)\u0001\u0000\u0000\u0000\u0125"+
		"\u0126\u0005\u001f\u0000\u0000\u0126\u0127\u0005\u0004\u0000\u0000\u0127"+
		"\u0128\u0005\u0002\u0000\u0000\u0128\u0129\u0005\u0006\u0000\u0000\u0129"+
		"\u012b\u0005(\u0000\u0000\u012a\u012c\u0005)\u0000\u0000\u012b\u012a\u0001"+
		"\u0000\u0000\u0000\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012d\u0001"+
		"\u0000\u0000\u0000\u012d\u012e\u0006\u0015\uffff\uffff\u0000\u012e\u012f"+
		"\u0003,\u0016\u0000\u012f\u0130\u0003 \u0010\u0000\u0130\u0131\u0005)"+
		"\u0000\u0000\u0131+\u0001\u0000\u0000\u0000\u0132\u0134\u0003.\u0017\u0000"+
		"\u0133\u0135\u0005)\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0134"+
		"\u0135\u0001\u0000\u0000\u0000\u0135\u0137\u0001\u0000\u0000\u0000\u0136"+
		"\u0132\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000\u0000\u0138"+
		"\u0136\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000\u0000\u0000\u0139"+
		"-\u0001\u0000\u0000\u0000\u013a\u013b\u0005(\u0000\u0000\u013b\u013c\u0005"+
		"\u0007\u0000\u0000\u013c\u013e\u00032\u0019\u0000\u013d\u013f\u0005\u000b"+
		"\u0000\u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000"+
		"\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140\u0141\u0006\u0017"+
		"\uffff\uffff\u0000\u0141/\u0001\u0000\u0000\u0000\u0142\u0143\u0005\t"+
		"\u0000\u0000\u0143\u0144\u0005\u0002\u0000\u0000\u0144\u0145\u0005\u0003"+
		"\u0000\u0000\u0145\u0146\u0005\u0004\u0000\u0000\u0146\u0147\u0005\u0005"+
		"\u0000\u0000\u0147\u0148\u0005(\u0000\u0000\u0148\u0149\u0005)\u0000\u0000"+
		"\u0149\u014a\u0006\u0018\uffff\uffff\u0000\u014a1\u0001\u0000\u0000\u0000"+
		"\u014b\u014c\u0005\'\u0000\u0000\u014c\u0154\u0006\u0019\uffff\uffff\u0000"+
		"\u014d\u014e\u0005&\u0000\u0000\u014e\u0154\u0006\u0019\uffff\uffff\u0000"+
		"\u014f\u0150\u0005$\u0000\u0000\u0150\u0154\u0006\u0019\uffff\uffff\u0000"+
		"\u0151\u0152\u0005%\u0000\u0000\u0152\u0154\u0006\u0019\uffff\uffff\u0000"+
		"\u0153\u014b\u0001\u0000\u0000\u0000\u0153\u014d\u0001\u0000\u0000\u0000"+
		"\u0153\u014f\u0001\u0000\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000"+
		"\u01543\u0001\u0000\u0000\u0000\u0155\u0156\u0005 \u0000\u0000\u0156\u015e"+
		"\u0006\u001a\uffff\uffff\u0000\u0157\u0158\u0005!\u0000\u0000\u0158\u015e"+
		"\u0006\u001a\uffff\uffff\u0000\u0159\u015a\u0005\"\u0000\u0000\u015a\u015e"+
		"\u0006\u001a\uffff\uffff\u0000\u015b\u015c\u0005#\u0000\u0000\u015c\u015e"+
		"\u0006\u001a\uffff\uffff\u0000\u015d\u0155\u0001\u0000\u0000\u0000\u015d"+
		"\u0157\u0001\u0000\u0000\u0000\u015d\u0159\u0001\u0000\u0000\u0000\u015d"+
		"\u015b\u0001\u0000\u0000\u0000\u015e5\u0001\u0000\u0000\u0000\u001d9C"+
		"GPcipz~\u0084\u008c\u0096\u00a2\u00a6\u00ad\u00b9\u00c2\u00c6\u00ca\u00ce"+
		"\u00dc\u00ec\u010a\u012b\u0134\u0138\u013e\u0153\u015d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}