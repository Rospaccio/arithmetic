grammar Arithmetic;

program : expression ;

expression 
	: additiveExp;
	
additiveExp 
	: additiveExp '+' additiveExp #Sum
	| additiveExp '-' additiveExp #Diff
	| multiplicativeExp #Term;

multiplicativeExp
	: multiplicativeExp '*' multiplicativeExp #Mult
	| multiplicativeExp '/' multiplicativeExp #Div
	| realNumber #Real
	| '(' expression ')' #Exp;

realNumber : NUMBER ('.'NUMBER)? ;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

NUMBER : [0-9]+ ;
