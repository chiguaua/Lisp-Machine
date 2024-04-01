grammar lisp_to_java;

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
program : expression* EOF ;
expression: OP (IDENTIFIER | STRING | NUMBER | expression)* CP;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

IDENTIFIER : ((LETTER (LETTER | DIGIT)*) | PLUS | MINUS | MULT | DIV | LOGIC) ;

PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
OP : '(';
CP : ')';
LOGIC : '>' | '<' | '=' | '/=';

STRING : '"' (LETTER | DIGIT)+ '"';

NUMBER : (DIGIT)+ ;

WHITESPACE : [ \r\n\t] + -> channel (HIDDEN);

DIGIT : '0'..'9';

LETTER : LOWER | UPPER ;

LOWER : ('a'..'z') ;
UPPER : ('A'..'Z') ;
