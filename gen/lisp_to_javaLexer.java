// Generated from C:/Global/prog/Lisp/Lisp-Machine/src/main/java/lisp_to_java.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class lisp_to_javaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IDENTIFIER=1, PLUS=2, MINUS=3, MULT=4, DIV=5, OP=6, CP=7, LOGIC=8, BACKTICK=9, 
		COMMA=10, STRING=11, NUMBER=12, WHITESPACE=13, DIGIT=14, LETTER=15, LOWER=16, 
		UPPER=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IDENTIFIER", "PLUS", "MINUS", "MULT", "DIV", "OP", "CP", "LOGIC", "BACKTICK", 
			"COMMA", "STRING", "NUMBER", "WHITESPACE", "DIGIT", "LETTER", "LOWER", 
			"UPPER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'+'", "'-'", "'*'", "'/'", "'('", "')'", null, "'`'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IDENTIFIER", "PLUS", "MINUS", "MULT", "DIV", "OP", "CP", "LOGIC", 
			"BACKTICK", "COMMA", "STRING", "NUMBER", "WHITESPACE", "DIGIT", "LETTER", 
			"LOWER", "UPPER"
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


	public lisp_to_javaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "lisp_to_java.g4"; }

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
		"\u0004\u0000\u0011h\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003"+
		"\u00003\b\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007D\b"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0004\n"+
		"M\b\n\u000b\n\f\nN\u0001\n\u0001\n\u0001\u000b\u0004\u000bT\b\u000b\u000b"+
		"\u000b\f\u000bU\u0001\f\u0004\fY\b\f\u000b\f\f\fZ\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0003\u000ec\b\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0000\u0000\u0011\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"\u0001\u0000\u0001\u0003\u0000\t\n\r\r  v\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u00012\u0001"+
		"\u0000\u0000\u0000\u00034\u0001\u0000\u0000\u0000\u00056\u0001\u0000\u0000"+
		"\u0000\u00078\u0001\u0000\u0000\u0000\t:\u0001\u0000\u0000\u0000\u000b"+
		"<\u0001\u0000\u0000\u0000\r>\u0001\u0000\u0000\u0000\u000fC\u0001\u0000"+
		"\u0000\u0000\u0011E\u0001\u0000\u0000\u0000\u0013G\u0001\u0000\u0000\u0000"+
		"\u0015I\u0001\u0000\u0000\u0000\u0017S\u0001\u0000\u0000\u0000\u0019X"+
		"\u0001\u0000\u0000\u0000\u001b^\u0001\u0000\u0000\u0000\u001db\u0001\u0000"+
		"\u0000\u0000\u001fd\u0001\u0000\u0000\u0000!f\u0001\u0000\u0000\u0000"+
		"#(\u0003\u001d\u000e\u0000$\'\u0003\u001d\u000e\u0000%\'\u0003\u001b\r"+
		"\u0000&$\u0001\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000\'*\u0001\u0000"+
		"\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)3\u0001"+
		"\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000+3\u0003\u0003\u0001\u0000"+
		",3\u0003\u0005\u0002\u0000-3\u0003\u0007\u0003\u0000.3\u0003\t\u0004\u0000"+
		"/3\u0003\u000f\u0007\u000003\u0003\u0011\b\u000013\u0003\u0013\t\u0000"+
		"2#\u0001\u0000\u0000\u00002+\u0001\u0000\u0000\u00002,\u0001\u0000\u0000"+
		"\u00002-\u0001\u0000\u0000\u00002.\u0001\u0000\u0000\u00002/\u0001\u0000"+
		"\u0000\u000020\u0001\u0000\u0000\u000021\u0001\u0000\u0000\u00003\u0002"+
		"\u0001\u0000\u0000\u000045\u0005+\u0000\u00005\u0004\u0001\u0000\u0000"+
		"\u000067\u0005-\u0000\u00007\u0006\u0001\u0000\u0000\u000089\u0005*\u0000"+
		"\u00009\b\u0001\u0000\u0000\u0000:;\u0005/\u0000\u0000;\n\u0001\u0000"+
		"\u0000\u0000<=\u0005(\u0000\u0000=\f\u0001\u0000\u0000\u0000>?\u0005)"+
		"\u0000\u0000?\u000e\u0001\u0000\u0000\u0000@D\u0002<>\u0000AB\u0005/\u0000"+
		"\u0000BD\u0005=\u0000\u0000C@\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000"+
		"\u0000D\u0010\u0001\u0000\u0000\u0000EF\u0005`\u0000\u0000F\u0012\u0001"+
		"\u0000\u0000\u0000GH\u0005,\u0000\u0000H\u0014\u0001\u0000\u0000\u0000"+
		"IL\u0005\"\u0000\u0000JM\u0003\u001d\u000e\u0000KM\u0003\u001b\r\u0000"+
		"LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000"+
		"\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OP\u0001\u0000"+
		"\u0000\u0000PQ\u0005\"\u0000\u0000Q\u0016\u0001\u0000\u0000\u0000RT\u0003"+
		"\u001b\r\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000US\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\u0018\u0001\u0000\u0000"+
		"\u0000WY\u0007\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0001"+
		"\u0000\u0000\u0000\\]\u0006\f\u0000\u0000]\u001a\u0001\u0000\u0000\u0000"+
		"^_\u000209\u0000_\u001c\u0001\u0000\u0000\u0000`c\u0003\u001f\u000f\u0000"+
		"ac\u0003!\u0010\u0000b`\u0001\u0000\u0000\u0000ba\u0001\u0000\u0000\u0000"+
		"c\u001e\u0001\u0000\u0000\u0000de\u0002az\u0000e \u0001\u0000\u0000\u0000"+
		"fg\u0002AZ\u0000g\"\u0001\u0000\u0000\u0000\n\u0000&(2CLNUZb\u0001\u0000"+
		"\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}