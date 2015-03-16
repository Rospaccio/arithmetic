grammar arithmetic;

program : expression ;

expression : term '+' term
			| term '-' term ;
			
term : real_number | expression;

real_number : NUMBER ('.' 	NUMBER)? ;

NUMBER : [0 - 9]+ ;