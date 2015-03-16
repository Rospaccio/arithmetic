package org.merka.arithmetic.language.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.merka.arithmetic.language.ArithmeticParser.AdditiveExpContext;

public class Sum extends AdditiveExpContext {

	public Sum(ParserRuleContext parent, int invokingState) {
		super(parent, invokingState);
	}

}
