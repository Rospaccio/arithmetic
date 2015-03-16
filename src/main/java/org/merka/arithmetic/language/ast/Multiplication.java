package org.merka.arithmetic.language.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicativeExpContext;

public class Multiplication extends MultiplicativeExpContext {

	public Multiplication(ParserRuleContext parent, int invokingState) {
		super(parent, invokingState);
	}

}
