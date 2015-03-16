package org.merka.arithmetic.language.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicativeExpContext;

public class Division extends MultiplicativeExpContext {

	public Division(ParserRuleContext parent, int invokingState) {
		super(parent, invokingState);
	}

}
