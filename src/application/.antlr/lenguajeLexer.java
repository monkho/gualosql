// Generated from c:/Users/Obgoh/OneDrive - Instituto Tecnológico de Morelia/Documentos/08_Semestre/03 - Lenguajes y automatas II/practicas/proyecto_2/src/application/lenguaje.g4 by ANTLR 4.13.1

package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class lenguajeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "TEXTO", "NUMERO", 
			"FECHA", "TIEMPO", "DATE", "TIME", "NUMBER", "STRING", "IDENTIFIER", 
			"DIGIT", "NEWLINE", "WS"
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


	public lenguajeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000*\u0162\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001%\u0004%\u013e\b%\u000b%\f%"+
		"\u013f\u0001&\u0001&\u0005&\u0144\b&\n&\f&\u0147\t&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0005\'\u014d\b\'\n\'\f\'\u0150\t\'\u0001(\u0001(\u0001)\u0003"+
		")\u0155\b)\u0001)\u0004)\u0158\b)\u000b)\f)\u0159\u0001*\u0004*\u015d"+
		"\b*\u000b*\f*\u015e\u0001*\u0001*\u0000\u0000+\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u00193\u001a5\u001b"+
		"7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%K&M\'O(Q\u0000S)U*\u0001\u0000"+
		"\u0005\u0001\u000009\u0003\u0000\n\n\r\r\'\'\u0003\u0000AZ__az\u0004\u0000"+
		"09AZ__az\u0002\u0000\t\t  \u0166\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000"+
		"\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;"+
		"\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000"+
		"\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000"+
		"\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I"+
		"\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000"+
		"\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000"+
		"\u0000U\u0001\u0000\u0000\u0000\u0001W\u0001\u0000\u0000\u0000\u0003\\"+
		"\u0001\u0000\u0000\u0000\u0005_\u0001\u0000\u0000\u0000\u0007d\u0001\u0000"+
		"\u0000\u0000\tg\u0001\u0000\u0000\u0000\u000bm\u0001\u0000\u0000\u0000"+
		"\rs\u0001\u0000\u0000\u0000\u000fw\u0001\u0000\u0000\u0000\u0011~\u0001"+
		"\u0000\u0000\u0000\u0013\u0086\u0001\u0000\u0000\u0000\u0015\u0089\u0001"+
		"\u0000\u0000\u0000\u0017\u008b\u0001\u0000\u0000\u0000\u0019\u0098\u0001"+
		"\u0000\u0000\u0000\u001b\u00a0\u0001\u0000\u0000\u0000\u001d\u00aa\u0001"+
		"\u0000\u0000\u0000\u001f\u00b0\u0001\u0000\u0000\u0000!\u00bb\u0001\u0000"+
		"\u0000\u0000#\u00c0\u0001\u0000\u0000\u0000%\u00c6\u0001\u0000\u0000\u0000"+
		"\'\u00c8\u0001\u0000\u0000\u0000)\u00cf\u0001\u0000\u0000\u0000+\u00d2"+
		"\u0001\u0000\u0000\u0000-\u00d8\u0001\u0000\u0000\u0000/\u00dc\u0001\u0000"+
		"\u0000\u00001\u00e2\u0001\u0000\u0000\u00003\u00e4\u0001\u0000\u0000\u0000"+
		"5\u00ea\u0001\u0000\u0000\u00007\u00f0\u0001\u0000\u0000\u00009\u00f2"+
		"\u0001\u0000\u0000\u0000;\u00fb\u0001\u0000\u0000\u0000=\u0101\u0001\u0000"+
		"\u0000\u0000?\u010a\u0001\u0000\u0000\u0000A\u0110\u0001\u0000\u0000\u0000"+
		"C\u0117\u0001\u0000\u0000\u0000E\u011d\u0001\u0000\u0000\u0000G\u0124"+
		"\u0001\u0000\u0000\u0000I\u0131\u0001\u0000\u0000\u0000K\u013d\u0001\u0000"+
		"\u0000\u0000M\u0141\u0001\u0000\u0000\u0000O\u014a\u0001\u0000\u0000\u0000"+
		"Q\u0151\u0001\u0000\u0000\u0000S\u0157\u0001\u0000\u0000\u0000U\u015c"+
		"\u0001\u0000\u0000\u0000WX\u0005c\u0000\u0000XY\u0005r\u0000\u0000YZ\u0005"+
		"e\u0000\u0000Z[\u0005a\u0000\u0000[\u0002\u0001\u0000\u0000\u0000\\]\u0005"+
		"l\u0000\u0000]^\u0005a\u0000\u0000^\u0004\u0001\u0000\u0000\u0000_`\u0005"+
		"b\u0000\u0000`a\u0005a\u0000\u0000ab\u0005s\u0000\u0000bc\u0005e\u0000"+
		"\u0000c\u0006\u0001\u0000\u0000\u0000de\u0005d\u0000\u0000ef\u0005e\u0000"+
		"\u0000f\b\u0001\u0000\u0000\u0000gh\u0005d\u0000\u0000hi\u0005a\u0000"+
		"\u0000ij\u0005t\u0000\u0000jk\u0005o\u0000\u0000kl\u0005s\u0000\u0000"+
		"l\n\u0001\u0000\u0000\u0000mn\u0005t\u0000\u0000no\u0005a\u0000\u0000"+
		"op\u0005b\u0000\u0000pq\u0005l\u0000\u0000qr\u0005a\u0000\u0000r\f\u0001"+
		"\u0000\u0000\u0000st\u0005c\u0000\u0000tu\u0005o\u0000\u0000uv\u0005n"+
		"\u0000\u0000v\u000e\u0001\u0000\u0000\u0000wx\u0005c\u0000\u0000xy\u0005"+
		"a\u0000\u0000yz\u0005m\u0000\u0000z{\u0005p\u0000\u0000{|\u0005o\u0000"+
		"\u0000|}\u0005s\u0000\u0000}\u0010\u0001\u0000\u0000\u0000~\u007f\u0005"+
		"t\u0000\u0000\u007f\u0080\u0005e\u0000\u0000\u0080\u0081\u0005r\u0000"+
		"\u0000\u0081\u0082\u0005m\u0000\u0000\u0082\u0083\u0005i\u0000\u0000\u0083"+
		"\u0084\u0005n\u0000\u0000\u0084\u0085\u0005a\u0000\u0000\u0085\u0012\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0005e\u0000\u0000\u0087\u0088\u0005n\u0000"+
		"\u0000\u0088\u0014\u0001\u0000\u0000\u0000\u0089\u008a\u0005,\u0000\u0000"+
		"\u008a\u0016\u0001\u0000\u0000\u0000\u008b\u008c\u0005r\u0000\u0000\u008c"+
		"\u008d\u0005e\u0000\u0000\u008d\u008e\u0005f\u0000\u0000\u008e\u008f\u0005"+
		"e\u0000\u0000\u008f\u0090\u0005r\u0000\u0000\u0090\u0091\u0005e\u0000"+
		"\u0000\u0091\u0092\u0005n\u0000\u0000\u0092\u0093\u0005c\u0000\u0000\u0093"+
		"\u0094\u0005i\u0000\u0000\u0094\u0095\u0005a\u0000\u0000\u0095\u0096\u0005"+
		"d\u0000\u0000\u0096\u0097\u0005o\u0000\u0000\u0097\u0018\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0005i\u0000\u0000\u0099\u009a\u0005n\u0000\u0000\u009a"+
		"\u009b\u0005s\u0000\u0000\u009b\u009c\u0005e\u0000\u0000\u009c\u009d\u0005"+
		"r\u0000\u0000\u009d\u009e\u0005t\u0000\u0000\u009e\u009f\u0005a\u0000"+
		"\u0000\u009f\u001a\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005i\u0000\u0000"+
		"\u00a1\u00a2\u0005n\u0000\u0000\u00a2\u00a3\u0005s\u0000\u0000\u00a3\u00a4"+
		"\u0005e\u0000\u0000\u00a4\u00a5\u0005r\u0000\u0000\u00a5\u00a6\u0005c"+
		"\u0000\u0000\u00a6\u00a7\u0005i\u0000\u0000\u00a7\u00a8\u0005o\u0000\u0000"+
		"\u00a8\u00a9\u0005n\u0000\u0000\u00a9\u001c\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0005v\u0000\u0000\u00ab\u00ac\u0005a\u0000\u0000\u00ac\u00ad\u0005"+
		"l\u0000\u0000\u00ad\u00ae\u0005o\u0000\u0000\u00ae\u00af\u0005r\u0000"+
		"\u0000\u00af\u001e\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005s\u0000\u0000"+
		"\u00b1\u00b2\u0005e\u0000\u0000\u00b2\u00b3\u0005l\u0000\u0000\u00b3\u00b4"+
		"\u0005e\u0000\u0000\u00b4\u00b5\u0005c\u0000\u0000\u00b5\u00b6\u0005c"+
		"\u0000\u0000\u00b6\u00b7\u0005i\u0000\u0000\u00b7\u00b8\u0005o\u0000\u0000"+
		"\u00b8\u00b9\u0005n\u0000\u0000\u00b9\u00ba\u0005a\u0000\u0000\u00ba "+
		"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005t\u0000\u0000\u00bc\u00bd\u0005"+
		"o\u0000\u0000\u00bd\u00be\u0005d\u0000\u0000\u00be\u00bf\u0005o\u0000"+
		"\u0000\u00bf\"\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005e\u0000\u0000"+
		"\u00c1\u00c2\u0005n\u0000\u0000\u00c2\u00c3\u0005t\u0000\u0000\u00c3\u00c4"+
		"\u0005r\u0000\u0000\u00c4\u00c5\u0005e\u0000\u0000\u00c5$\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0005y\u0000\u0000\u00c7&\u0001\u0000\u0000\u0000"+
		"\u00c8\u00c9\u0005c\u0000\u0000\u00c9\u00ca\u0005u\u0000\u0000\u00ca\u00cb"+
		"\u0005a\u0000\u0000\u00cb\u00cc\u0005n\u0000\u0000\u00cc\u00cd\u0005d"+
		"\u0000\u0000\u00cd\u00ce\u0005o\u0000\u0000\u00ce(\u0001\u0000\u0000\u0000"+
		"\u00cf\u00d0\u0005e\u0000\u0000\u00d0\u00d1\u0005l\u0000\u0000\u00d1*"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005c\u0000\u0000\u00d3\u00d4\u0005"+
		"a\u0000\u0000\u00d4\u00d5\u0005m\u0000\u0000\u00d5\u00d6\u0005p\u0000"+
		"\u0000\u00d6\u00d7\u0005o\u0000\u0000\u00d7,\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d9\u0005s\u0000\u0000\u00d9\u00da\u0005e\u0000\u0000\u00da\u00db\u0005"+
		"a\u0000\u0000\u00db.\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005i\u0000"+
		"\u0000\u00dd\u00de\u0005g\u0000\u0000\u00de\u00df\u0005u\u0000\u0000\u00df"+
		"\u00e0\u0005a\u0000\u0000\u00e0\u00e1\u0005l\u0000\u0000\u00e10\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0005a\u0000\u0000\u00e32\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e5\u0005m\u0000\u0000\u00e5\u00e6\u0005a\u0000\u0000\u00e6"+
		"\u00e7\u0005y\u0000\u0000\u00e7\u00e8\u0005o\u0000\u0000\u00e8\u00e9\u0005"+
		"r\u0000\u0000\u00e94\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005m\u0000"+
		"\u0000\u00eb\u00ec\u0005e\u0000\u0000\u00ec\u00ed\u0005n\u0000\u0000\u00ed"+
		"\u00ee\u0005o\u0000\u0000\u00ee\u00ef\u0005r\u0000\u0000\u00ef6\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0005o\u0000\u0000\u00f18\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0005d\u0000\u0000\u00f3\u00f4\u0005i\u0000\u0000\u00f4"+
		"\u00f5\u0005s\u0000\u0000\u00f5\u00f6\u0005t\u0000\u0000\u00f6\u00f7\u0005"+
		"i\u0000\u0000\u00f7\u00f8\u0005n\u0000\u0000\u00f8\u00f9\u0005t\u0000"+
		"\u0000\u00f9\u00fa\u0005o\u0000\u0000\u00fa:\u0001\u0000\u0000\u0000\u00fb"+
		"\u00fc\u0005b\u0000\u0000\u00fc\u00fd\u0005o\u0000\u0000\u00fd\u00fe\u0005"+
		"r\u0000\u0000\u00fe\u00ff\u0005r\u0000\u0000\u00ff\u0100\u0005a\u0000"+
		"\u0000\u0100<\u0001\u0000\u0000\u0000\u0101\u0102\u0005m\u0000\u0000\u0102"+
		"\u0103\u0005o\u0000\u0000\u0103\u0104\u0005d\u0000\u0000\u0104\u0105\u0005"+
		"i\u0000\u0000\u0105\u0106\u0005f\u0000\u0000\u0106\u0107\u0005i\u0000"+
		"\u0000\u0107\u0108\u0005c\u0000\u0000\u0108\u0109\u0005a\u0000\u0000\u0109"+
		">\u0001\u0000\u0000\u0000\u010a\u010b\u0005t\u0000\u0000\u010b\u010c\u0005"+
		"e\u0000\u0000\u010c\u010d\u0005x\u0000\u0000\u010d\u010e\u0005t\u0000"+
		"\u0000\u010e\u010f\u0005o\u0000\u0000\u010f@\u0001\u0000\u0000\u0000\u0110"+
		"\u0111\u0005n\u0000\u0000\u0111\u0112\u0005u\u0000\u0000\u0112\u0113\u0005"+
		"m\u0000\u0000\u0113\u0114\u0005e\u0000\u0000\u0114\u0115\u0005r\u0000"+
		"\u0000\u0115\u0116\u0005o\u0000\u0000\u0116B\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0005f\u0000\u0000\u0118\u0119\u0005e\u0000\u0000\u0119\u011a\u0005"+
		"c\u0000\u0000\u011a\u011b\u0005h\u0000\u0000\u011b\u011c\u0005a\u0000"+
		"\u0000\u011cD\u0001\u0000\u0000\u0000\u011d\u011e\u0005t\u0000\u0000\u011e"+
		"\u011f\u0005i\u0000\u0000\u011f\u0120\u0005e\u0000\u0000\u0120\u0121\u0005"+
		"m\u0000\u0000\u0121\u0122\u0005p\u0000\u0000\u0122\u0123\u0005o\u0000"+
		"\u0000\u0123F\u0001\u0000\u0000\u0000\u0124\u0125\u0005\'\u0000\u0000"+
		"\u0125\u0126\u0003Q(\u0000\u0126\u0127\u0003Q(\u0000\u0127\u0128\u0003"+
		"Q(\u0000\u0128\u0129\u0003Q(\u0000\u0129\u012a\u0005-\u0000\u0000\u012a"+
		"\u012b\u0003Q(\u0000\u012b\u012c\u0003Q(\u0000\u012c\u012d\u0005-\u0000"+
		"\u0000\u012d\u012e\u0003Q(\u0000\u012e\u012f\u0003Q(\u0000\u012f\u0130"+
		"\u0005\'\u0000\u0000\u0130H\u0001\u0000\u0000\u0000\u0131\u0132\u0005"+
		"\'\u0000\u0000\u0132\u0133\u0003Q(\u0000\u0133\u0134\u0003Q(\u0000\u0134"+
		"\u0135\u0005:\u0000\u0000\u0135\u0136\u0003Q(\u0000\u0136\u0137\u0003"+
		"Q(\u0000\u0137\u0138\u0005:\u0000\u0000\u0138\u0139\u0003Q(\u0000\u0139"+
		"\u013a\u0003Q(\u0000\u013a\u013b\u0005\'\u0000\u0000\u013bJ\u0001\u0000"+
		"\u0000\u0000\u013c\u013e\u0007\u0000\u0000\u0000\u013d\u013c\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000"+
		"\u0000\u0000\u013f\u0140\u0001\u0000\u0000\u0000\u0140L\u0001\u0000\u0000"+
		"\u0000\u0141\u0145\u0005\'\u0000\u0000\u0142\u0144\b\u0001\u0000\u0000"+
		"\u0143\u0142\u0001\u0000\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000"+
		"\u0145\u0143\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000"+
		"\u0146\u0148\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000"+
		"\u0148\u0149\u0005\'\u0000\u0000\u0149N\u0001\u0000\u0000\u0000\u014a"+
		"\u014e\u0007\u0002\u0000\u0000\u014b\u014d\u0007\u0003\u0000\u0000\u014c"+
		"\u014b\u0001\u0000\u0000\u0000\u014d\u0150\u0001\u0000\u0000\u0000\u014e"+
		"\u014c\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f"+
		"P\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151\u0152"+
		"\u0007\u0000\u0000\u0000\u0152R\u0001\u0000\u0000\u0000\u0153\u0155\u0005"+
		"\r\u0000\u0000\u0154\u0153\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000"+
		"\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0158\u0005\n\u0000"+
		"\u0000\u0157\u0154\u0001\u0000\u0000\u0000\u0158\u0159\u0001\u0000\u0000"+
		"\u0000\u0159\u0157\u0001\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000"+
		"\u0000\u015aT\u0001\u0000\u0000\u0000\u015b\u015d\u0007\u0004\u0000\u0000"+
		"\u015c\u015b\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000"+
		"\u015e\u015c\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000"+
		"\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u0161\u0006*\u0000\u0000\u0161"+
		"V\u0001\u0000\u0000\u0000\u0007\u0000\u013f\u0145\u014e\u0154\u0159\u015e"+
		"\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}