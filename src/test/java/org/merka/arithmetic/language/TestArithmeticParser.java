package org.merka.arithmetic.language;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.BitSet;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Test;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestArithmeticParser {

	public static final Logger logger = LoggerFactory.getLogger(TestArithmeticParser.class);
	
	public static final String[] validStrings = 
		{
			"1",
			"1+1",
			"1+1+2*3+4/6",
			"1 + 1",
			"1 +1+1",
			"3 * 4",
			"1 + 3 * 4",
			"3 /4+1",
			"3 * (1 + 4 + 5 * 3)",
			"(2) + (3 - 5) * (3-2)"
		};
	
	@Test
	public void testProgram() throws IOException {
		for(String valid : validStrings){
			assertTrue("string = '" + valid + "'", isValidLanguageString(valid));
		}
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
