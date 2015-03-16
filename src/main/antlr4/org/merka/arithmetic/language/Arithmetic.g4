grammar Arithmetic;

program : expression ;

expression 
	: additiveExp;
	
additiveExp 
	: additiveExp '+' additiveExp
	| additiveExp '-' additiveExp
	| multiplicativeExp;

multiplicativeExp
	: multiplicativeExp '*' multiplicativeExp
	| multiplicativeExp '/' multiplicativeExp
	| realNumber
	| '(' expression ')';

realNumber : NUMBER ('.'NUMBER)? ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+ ;
