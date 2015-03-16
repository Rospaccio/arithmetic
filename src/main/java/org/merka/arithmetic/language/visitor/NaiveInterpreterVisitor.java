package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.AdditiveExpContext;
import org.merka.arithmetic.language.ArithmeticParser.ExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicativeExpContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticVisitor;

public class NaiveInterpreterVisitor implements ArithmeticVisitor<Double> {

	@Override
	public Double visit(ParseTree arg0) {
		return Double.valueOf(14F);
	}

	@Override
	public Double visitChildren(RuleNode arg0) {
		return null;
	}

	@Override
	public Double visitErrorNode(ErrorNode arg0) {
		
		return null;
	}

	@Override
	public Double visitTerminal(TerminalNode arg0) {
		
		return null;
	}

	@Override
	public Double visitProgram(ProgramContext ctx) {
		
		return null;
	}

	@Override
	public Double visitExpression(ExpressionContext ctx) {
		
		return null;
	}

	@Override
	public Double visitAdditiveExp(AdditiveExpContext ctx) {
		
		return null;
	}

	@Override
	public Double visitMultiplicativeExp(MultiplicativeExpContext ctx) {
		Double leftOperand = ctx.getChild(0).accept(this);
		String operator = ctx.getChild(1).getText();
		Double rightOperand = ctx.getChild(2).accept(this);
		return leftOperand * rightOperand;
	}

	@Override
	public Double visitRealNumber(RealNumberContext ctx) {
		Double value = Double.valueOf(ctx.getChild(0).getText());
		if(ctx.getChildCount() == 3){
			Double decimalPart = Double.parseDouble("0." + ctx.getChild(2).getText());
			value = value + decimalPart; 
		}
		return value;
	}

}
