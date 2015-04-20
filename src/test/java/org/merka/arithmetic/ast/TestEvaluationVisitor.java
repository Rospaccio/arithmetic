package org.merka.arithmetic.ast;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Test;
import org.merka.arithmetic.language.ArithmeticLexer;
import org.merka.arithmetic.language.ArithmeticParser;
import org.merka.arithmetic.language.TestArithmeticParser;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.TestArithmeticParser.ArithmeticTestErrorListener;
import org.merka.arithmetic.language.ast.ArithmeticASTNode;
import org.merka.arithmetic.language.ast.visitor.ArithmeticASTVisitor;
import org.merka.arithmetic.language.ast.visitor.EvaluationVisitor;
import org.merka.arithmetic.language.visitor.ASTBuilderVisitor;

public class TestEvaluationVisitor {

	public static final double DELTA = 0.00001;
	
	public static final String[] validStrings = 
		{
			"1.2",
			"1+1",
			"1+1+2*3+4/6",
			"1 + 1",
			"1 +1+1",
			"3 * 4",
			"1 + 3 * 4",
			"3 /4+1",
			"3 * (1 + 4 + 5 * 3)",
			"1 + (30 - (5 - (2 + (4 - 5))))",
			"((2))",
			"((((4))))",
			"(1) - (2)",
			"(1 + 1)",
			"(2) + (3 - 5) * (3-2)"
		};
	
	public static Double[] results = {
		1.2,
		2D,
		8.666666666667,
		2D,
		3D,
		12D,
		13D,
		1.75,
		60D,
		27D,
		2D,
		4D,
		-1D,
		2D,
		0D
	};
	
	@Test
	public void testEvaluation() {
		int index = 0;
		for(String program : validStrings){
			try
			{
				testString(program, results[index]);
			}
			catch(Exception ex)
			{
				fail("failed to evaluate String \"" + program + "\" at index + " + index);
			}
			
			index += 1;
		}
	}
	
	public void testString(String program, Double expected) throws IOException
	{
		ArithmeticTestErrorListener errorListener = new TestArithmeticParser.ArithmeticTestErrorListener();
		ProgramContext concreteParseTree = TestArithmeticParser.parseProgram(program, errorListener);
		assertFalse(errorListener.isFail());
		
		ASTBuilderVisitor visitor = new ASTBuilderVisitor();
		ArithmeticASTNode ast = concreteParseTree.accept(visitor);
		
		ArithmeticASTVisitor astVisitor = new EvaluationVisitor();
		Number evaluationResult = (Number)ast.accept(astVisitor);
		
		assertTrue(Math.abs(evaluationResult.doubleValue() - expected) <= DELTA);
	}
}
