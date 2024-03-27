// Generated from F:/CS NSU Class of 2025/CS NSU 06 (Sixth Semester)/06 Software Designing/Projects/Lisp-Machine/src/main/java/lisp_to_java.g4 by ANTLR 4.13.1
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
		"\u0004\u0000\u0011f\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u00001\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007B\b\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\n\u0004\nK\b\n\u000b\n\f\nL\u0001\n\u0001"+
		"\n\u0001\u000b\u0004\u000bR\b\u000b\u000b\u000b\f\u000bS\u0001\f\u0004"+
		"\fW\b\f\u000b\f\f\fX\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000ea\b\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0000\u0000\u0011\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004"+
		"\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017"+
		"\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011\u0001\u0000\u0001"+
		"\u0003\u0000\t\n\r\r  r\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003"+
		"\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007"+
		"\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001"+
		"\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000"+
		"\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000"+
		"\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000"+
		"\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000"+
		"\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000"+
		"\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u00010\u0001\u0000\u0000\u0000"+
		"\u00032\u0001\u0000\u0000\u0000\u00054\u0001\u0000\u0000\u0000\u00076"+
		"\u0001\u0000\u0000\u0000\t8\u0001\u0000\u0000\u0000\u000b:\u0001\u0000"+
		"\u0000\u0000\r<\u0001\u0000\u0000\u0000\u000fA\u0001\u0000\u0000\u0000"+
		"\u0011C\u0001\u0000\u0000\u0000\u0013E\u0001\u0000\u0000\u0000\u0015G"+
		"\u0001\u0000\u0000\u0000\u0017Q\u0001\u0000\u0000\u0000\u0019V\u0001\u0000"+
		"\u0000\u0000\u001b\\\u0001\u0000\u0000\u0000\u001d`\u0001\u0000\u0000"+
		"\u0000\u001fb\u0001\u0000\u0000\u0000!d\u0001\u0000\u0000\u0000#(\u0003"+
		"\u001d\u000e\u0000$\'\u0003\u001d\u000e\u0000%\'\u0003\u001b\r\u0000&"+
		"$\u0001\u0000\u0000\u0000&%\u0001\u0000\u0000\u0000\'*\u0001\u0000\u0000"+
		"\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)1\u0001\u0000"+
		"\u0000\u0000*(\u0001\u0000\u0000\u0000+1\u0003\u0003\u0001\u0000,1\u0003"+
		"\u0005\u0002\u0000-1\u0003\u0007\u0003\u0000.1\u0003\t\u0004\u0000/1\u0003"+
		"\u000f\u0007\u00000#\u0001\u0000\u0000\u00000+\u0001\u0000\u0000\u0000"+
		"0,\u0001\u0000\u0000\u00000-\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u00000/\u0001\u0000\u0000\u00001\u0002\u0001\u0000\u0000\u000023\u0005"+
		"+\u0000\u00003\u0004\u0001\u0000\u0000\u000045\u0005-\u0000\u00005\u0006"+
		"\u0001\u0000\u0000\u000067\u0005*\u0000\u00007\b\u0001\u0000\u0000\u0000"+
		"89\u0005/\u0000\u00009\n\u0001\u0000\u0000\u0000:;\u0005(\u0000\u0000"+
		";\f\u0001\u0000\u0000\u0000<=\u0005)\u0000\u0000=\u000e\u0001\u0000\u0000"+
		"\u0000>B\u0002<>\u0000?@\u0005/\u0000\u0000@B\u0005=\u0000\u0000A>\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000B\u0010\u0001\u0000\u0000"+
		"\u0000CD\u0005`\u0000\u0000D\u0012\u0001\u0000\u0000\u0000EF\u0005,\u0000"+
		"\u0000F\u0014\u0001\u0000\u0000\u0000GJ\u0005\"\u0000\u0000HK\u0003\u001d"+
		"\u000e\u0000IK\u0003\u001b\r\u0000JH\u0001\u0000\u0000\u0000JI\u0001\u0000"+
		"\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001"+
		"\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0005\"\u0000\u0000O\u0016"+
		"\u0001\u0000\u0000\u0000PR\u0003\u001b\r\u0000QP\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000T\u0018\u0001\u0000\u0000\u0000UW\u0007\u0000\u0000\u0000VU\u0001"+
		"\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000"+
		"XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0006\f\u0000\u0000"+
		"[\u001a\u0001\u0000\u0000\u0000\\]\u000209\u0000]\u001c\u0001\u0000\u0000"+
		"\u0000^a\u0003\u001f\u000f\u0000_a\u0003!\u0010\u0000`^\u0001\u0000\u0000"+
		"\u0000`_\u0001\u0000\u0000\u0000a\u001e\u0001\u0000\u0000\u0000bc\u0002"+
		"az\u0000c \u0001\u0000\u0000\u0000de\u0002AZ\u0000e\"\u0001\u0000\u0000"+
		"\u0000\n\u0000&(0AJLSX`\u0001\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}