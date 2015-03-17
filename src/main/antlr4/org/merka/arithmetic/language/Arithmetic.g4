grammar Arithmetic;

program : expression ;

expression 
	: expression '+' expression #Sum 
	| expression '-' expression #Difference
	| multiplicativeExp #Term;

multiplicativeExp
	: multiplicativeExp '*' multiplicativeExp #Multiplication
	| multiplicativeExp '/' multiplicativeExp #Division
	| NUMBER ('.'NUMBER)? #Number
	| '(' expression ')' #InnerExpression;

// realNumber : NUMBER ('.'NUMBER)?;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+ ;
