grammar lisp_to_java;
/*------------------------------------------------------------------
 * A very basic implementation of a Lisp grammar.
 *------------------------------------------------------------------*/

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
program : expression* EOF ;
expression: OP (IDENTIFIER | STRING | NUMBER | expression)* CP;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
IDENTIFIER : ( (LETTER | DIGIT | ADDITIONAL)* | PLUS | MINUS | MULT | DIV | LOGIC ) ;

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
OP : '(';
CP : ')';
LOGIC : '>' | '<' | '=' | '/=';
ADDITIONAL : '\'' | '"' | '-' | '@' | '.';
SPACE  : [ \t\r\n]+ -> skip;

STRING : '"' (LETTER | DIGIT | SPACE)+ '"';

NUMBER : (DIGIT)+ ;

WHITESPACE : [ \r\n\t] + -> channel (HIDDEN);

DIGIT : '0'..'9';

LETTER : LOWER | UPPER ;

LOWER : ('a'..'z') ;
UPPER : ('A'..'Z') ;

