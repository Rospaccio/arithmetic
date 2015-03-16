package org.merka.arithmetic.language.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;

public class RealNumber extends RealNumberContext {

	public RealNumber(ParserRuleContext parent, int invokingState) {
		super(parent, invokingState);
	}

}
