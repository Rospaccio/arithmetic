grammar Arithmetic;

program : expression ;

expression 
	: expression ('*' | '/') expression #Multiplication
	| expression ('+' | '-') expression #AlgebraicSum
	| term #AtomicTerm;

term: realNumber #Number
	| '(' expression ')' #InnerExpression;

 realNumber : NUMBER ('.'NUMBER)?;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+ ;
