package org.merka.arithmetic.language;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Test;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.listener.ASTBuilderListener;
import org.merka.arithmetic.language.visitor.NaiveInterpreterVisitor;
import org.merka.arithmetic.language.visitor.ParseTreeDumperVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestArithmeticParser {

	public static final Logger logger = LoggerFactory.getLogger(TestArithmeticParser.class);
	
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
		0.666666666667,
		2D,
		3D,
		12D,
		13D,
		1.75,
		22D
	};
	
	public static final String[] invalidStrings = 
		{
			"1.",
			"1+1-",
			"1+1+2*3+4//6",
			"1 ++ 1",
			"1 +* 1 + 1",
			"3 * 4.4.4",
			"1 + 3 * 4.4.",
			"3 /)(4+1",
			"3 * ((1 + 4 + 5 * 3)",
			"1 + (30 -+ (5 - (2 + (4 - 5))))",
			"((2)))",
			"((((4)))))",
			"(1) - (2))",
			"(1 + 1()",
			"(2) + ()3 - 5) * (3-2)"
		};
	
	@Test
	public void testProgram() throws IOException {
		for(String valid : validStrings){
			assertTrue("string = '" + valid + "'", isValidLanguageString(valid));
		}
	}
	
	@Test
	public void testInvalidStrings() throws IOException{
		for(String invalid : invalidStrings){
			assertFalse("string '" + invalid + "' is recognized as valid, but it should not", isValidLanguageString(invalid));
		}
	}
	
	@Test
	public void testParseTreeToString() throws IOException{
		String program = "1 + (30 / (3 * 4.4) + (5.34 * 0.3 - (0.2 + (4 - 5))))";
//		String program = "1 + 3 - 2";
		ArithmeticTestErrorListener errorListener = new ArithmeticTestErrorListener();
		ProgramContext context = parseProgram(program, errorListener);		
		assertFalse(errorListener.isFail());
		ArithmeticVisitor<String> dumper = new ParseTreeDumperVisitor();
		String parseTreeString = dumper.visit(context);
		logger.info(parseTreeString);
	}

	@Test
	public void testNaiveInterpreterVisitor() throws IOException{
		String program = "2 + 3 * 4"; //14
		Double result = interpret(program);
		Double epsilon = Double.valueOf("0.00001");
		assertTrue(Math.abs(result - (double)14) <= epsilon);
	}
	
	@Test
	public void testNaiveInterpreterVisitorBatch() throws IOException{
		int index = 0;
		for(Double expected : results){
			String program = validStrings[index];
			Double result = interpret(program);
			Double epsilon = Double.valueOf("0.00001");
			assertTrue("Result does not match for the program '" 
					+ program + "'. Expected " + expected 
					+ " but got " + result, Math.abs(result - (double)expected) <= epsilon);
			index++;
		}
	}
	
	private Double interpret(String program) throws IOException{
		NaiveInterpreterVisitor visitor = new NaiveInterpreterVisitor();
		ArithmeticTestErrorListener errorListener = new ArithmeticTestErrorListener();
		ProgramContext context = parseProgram(program, errorListener);
		assertFalse(errorListener.isFail());
		Double result = visitor.visit(context);
		return result;
	}
	
	private boolean isValidLanguageString(String languageString) throws IOException{
		ArithmeticTestErrorListener errorListener = new ArithmeticTestErrorListener();
		ProgramContext context = parseProgram(languageString, errorListener);
		return !errorListener.isFail();
	}
	
	private ProgramContext parseProgram(String program, ANTLRErrorListener errorListener) throws IOException
	{
		CharStream inputCharStream = new ANTLRInputStream(new StringReader(program));
		TokenSource tokenSource = new ArithmeticLexer(inputCharStream);
		TokenStream inputTokenStream = new CommonTokenStream(tokenSource);
		ArithmeticParser parser = new ArithmeticParser(inputTokenStream);
		parser.addErrorListener(errorListener);
	
		parser.addParseListener(new ASTBuilderListener());
		
		ProgramContext context = parser.program();
		return context;
	}
	
	class ArithmeticTestErrorListener implements ANTLRErrorListener {
		
		private boolean fail = false;
		
		public boolean isFail() {
			return fail;
		}

		public void setFail(boolean fail) {
			this.fail = fail;
		}

		@Override
		public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2,
				int arg3, String arg4, RecognitionException arg5) {
			setFail(true);
		}
		
		@Override
		public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2,
				int arg3, int arg4, ATNConfigSet arg5) {
			setFail(true);			
		}
		
		@Override
		public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2,
				int arg3, BitSet arg4, ATNConfigSet arg5) {
			setFail(true);
		}
		
		@Override
		public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3,
				boolean arg4, BitSet arg5, ATNConfigSet arg6) {
			setFail(true);
		}
	}
}
