grammar Arithmetic;

program : expression ;

expression : additiveExp;
	
additiveExp 
	: multiplicativeExp
	| additiveExp '+' multiplicativeExp 
	| additiveExp '-' multiplicativeExp ;
	
multiplicativeExp
	: unaryExp
	| multiplicativeExp '*' unaryExp
	| multiplicativeExp '/' unaryExp ;

unaryExp 
	: realNumber
	| '(' expression ')' ;

realNumber : NUMBER ('.' NUMBER)? ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0 - 9]+ ;
