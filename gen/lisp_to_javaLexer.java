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
		IDENTIFIER=1, PLUS=2, MINUS=3, MULT=4, DIV=5, OP=6, CP=7, LOGIC=8, ADDITIONAL=9, 
		SPACE=10, STRING=11, NUMBER=12, WHITESPACE=13, DIGIT=14, LETTER=15, LOWER=16, 
		UPPER=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IDENTIFIER", "PLUS", "MINUS", "MULT", "DIV", "OP", "CP", "LOGIC", "ADDITIONAL", 
			"SPACE", "STRING", "NUMBER", "WHITESPACE", "DIGIT", "LETTER", "LOWER", 
			"UPPER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'+'", "'-'", "'*'", "'/'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IDENTIFIER", "PLUS", "MINUS", "MULT", "DIV", "OP", "CP", "LOGIC", 
			"ADDITIONAL", "SPACE", "STRING", "NUMBER", "WHITESPACE", "DIGIT", "LETTER", 
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
		"\u0004\u0000\u0011p\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u00001\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007F\b\u0007\u0001\b\u0001\b\u0001\t\u0004\tK\b\t\u000b"+
		"\t\f\tL\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0004\nU\b\n\u000b"+
		"\n\f\nV\u0001\n\u0001\n\u0001\u000b\u0004\u000b\\\b\u000b\u000b\u000b"+
		"\f\u000b]\u0001\f\u0004\fa\b\f\u000b\f\f\fb\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\u000e\u0001\u000e\u0003\u000ek\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0000\u0000\u0011\u0001\u0001\u0003\u0002\u0005"+
		"\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n"+
		"\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"\u0001\u0000\u0002\u0004\u0000\"\"\'\'-.@@\u0003\u0000\t\n\r\r  \u0081"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u00010\u0001\u0000\u0000\u0000\u00032\u0001\u0000\u0000"+
		"\u0000\u00054\u0001\u0000\u0000\u0000\u00076\u0001\u0000\u0000\u0000\t"+
		"8\u0001\u0000\u0000\u0000\u000b:\u0001\u0000\u0000\u0000\r<\u0001\u0000"+
		"\u0000\u0000\u000fE\u0001\u0000\u0000\u0000\u0011G\u0001\u0000\u0000\u0000"+
		"\u0013J\u0001\u0000\u0000\u0000\u0015P\u0001\u0000\u0000\u0000\u0017["+
		"\u0001\u0000\u0000\u0000\u0019`\u0001\u0000\u0000\u0000\u001bf\u0001\u0000"+
		"\u0000\u0000\u001dj\u0001\u0000\u0000\u0000\u001fl\u0001\u0000\u0000\u0000"+
		"!n\u0001\u0000\u0000\u0000#\'\u0003\u001d\u000e\u0000$\'\u0003\u001b\r"+
		"\u0000%\'\u0003\u0011\b\u0000&#\u0001\u0000\u0000\u0000&$\u0001\u0000"+
		"\u0000\u0000&%\u0001\u0000\u0000\u0000\'*\u0001\u0000\u0000\u0000(&\u0001"+
		"\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)1\u0001\u0000\u0000\u0000"+
		"*(\u0001\u0000\u0000\u0000+1\u0003\u0003\u0001\u0000,1\u0003\u0005\u0002"+
		"\u0000-1\u0003\u0007\u0003\u0000.1\u0003\t\u0004\u0000/1\u0003\u000f\u0007"+
		"\u00000(\u0001\u0000\u0000\u00000+\u0001\u0000\u0000\u00000,\u0001\u0000"+
		"\u0000\u00000-\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u00000/\u0001"+
		"\u0000\u0000\u00001\u0002\u0001\u0000\u0000\u000023\u0005+\u0000\u0000"+
		"3\u0004\u0001\u0000\u0000\u000045\u0005-\u0000\u00005\u0006\u0001\u0000"+
		"\u0000\u000067\u0005*\u0000\u00007\b\u0001\u0000\u0000\u000089\u0005/"+
		"\u0000\u00009\n\u0001\u0000\u0000\u0000:;\u0005(\u0000\u0000;\f\u0001"+
		"\u0000\u0000\u0000<=\u0005)\u0000\u0000=\u000e\u0001\u0000\u0000\u0000"+
		">F\u0002<>\u0000?@\u0005/\u0000\u0000@F\u0005=\u0000\u0000AB\u0005>\u0000"+
		"\u0000BF\u0005=\u0000\u0000CD\u0005<\u0000\u0000DF\u0005=\u0000\u0000"+
		"E>\u0001\u0000\u0000\u0000E?\u0001\u0000\u0000\u0000EA\u0001\u0000\u0000"+
		"\u0000EC\u0001\u0000\u0000\u0000F\u0010\u0001\u0000\u0000\u0000GH\u0007"+
		"\u0000\u0000\u0000H\u0012\u0001\u0000\u0000\u0000IK\u0007\u0001\u0000"+
		"\u0000JI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LJ\u0001\u0000"+
		"\u0000\u0000LM\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0006"+
		"\t\u0000\u0000O\u0014\u0001\u0000\u0000\u0000PT\u0005\"\u0000\u0000QU"+
		"\u0003\u001d\u000e\u0000RU\u0003\u001b\r\u0000SU\u0003\u0013\t\u0000T"+
		"Q\u0001\u0000\u0000\u0000TR\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000"+
		"\u0000UV\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000"+
		"\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0005\"\u0000\u0000Y\u0016\u0001"+
		"\u0000\u0000\u0000Z\\\u0003\u001b\r\u0000[Z\u0001\u0000\u0000\u0000\\"+
		"]\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000"+
		"\u0000^\u0018\u0001\u0000\u0000\u0000_a\u0007\u0001\u0000\u0000`_\u0001"+
		"\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0006\f\u0001\u0000"+
		"e\u001a\u0001\u0000\u0000\u0000fg\u000209\u0000g\u001c\u0001\u0000\u0000"+
		"\u0000hk\u0003\u001f\u000f\u0000ik\u0003!\u0010\u0000jh\u0001\u0000\u0000"+
		"\u0000ji\u0001\u0000\u0000\u0000k\u001e\u0001\u0000\u0000\u0000lm\u0002"+
		"az\u0000m \u0001\u0000\u0000\u0000no\u0002AZ\u0000o\"\u0001\u0000\u0000"+
		"\u0000\u000b\u0000&(0ELTV]bj\u0002\u0006\u0000\u0000\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}