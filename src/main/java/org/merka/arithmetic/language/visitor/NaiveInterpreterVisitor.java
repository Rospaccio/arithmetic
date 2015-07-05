package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.AlgebraicSumContext;
import org.merka.arithmetic.language.ArithmeticParser.AtomicTermContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;

public class NaiveInterpreterVisitor implements ArithmeticVisitor<Double> {

	private static final Logger logger = LoggerFactory.getLogger(NaiveInterpreterVisitor.class);
	
	private ParseTreeProperty<Double> numberNodesAnnotations = new ParseTreeProperty<>();
	
	protected ParseTreeProperty<Double> getNumberNodesAnnotations() {
		return numberNodesAnnotations;
	}

	protected void setNumberNodesAnnotations(
			ParseTreeProperty<Double> numberNodesAnnotations) {
		this.numberNodesAnnotations = numberNodesAnnotations;
	}

	@Override
	public Double visit(ParseTree arg0) {
		return arg0.accept(this);
	}
	
	@Override
	public Double visitChildren(RuleNode node) 
	{
		return null;
	}

	@Override
	public Double visitTerminal(TerminalNode node) {
		
		return null;
	}

	@Override
	public Double visitErrorNode(ErrorNode node) {
		
		return null;
	}

	@Override
	public Double visitProgram(ProgramContext context) {
		return context.expression().accept(this);
	}

	@Override
	public Double visitAlgebraicSum(AlgebraicSumContext context) {
		String operand = context.getChild(1).getText();
		Double left = context.expression(0).accept(this);
		Double right = context.expression(1).accept(this);
		if(operand.equals("+")){
			return left + right;
		}
		else if(operand.equals("-")){
			return left - right;
		}
		else
		{
			throw new ArithmeticException("Something has really gone wrong");
		}
	}

	@Override
	public Double visitMultiplication(MultiplicationContext context) {
		String operand = context.getChild(1).getText();
		Double left = context.expression(0).accept(this);
		Double right = context.expression(1).accept(this);
		if(operand.equals("*")){
			return left * right;
		}
		else if(operand.equals("/")){
			return left / right;
		}
		else
		{
			throw new ArithmeticException("Something has really gone wrong");
		}
	}

	@Override
	public Double visitAtomicTerm(AtomicTermContext context) {
		return context.term().accept(this);
	}

	@Override
	public Double visitInnerExpression(InnerExpressionContext context) {
		return context.expression().accept(this);
	}

	@Override
	public Double visitRealNumber(RealNumberContext context) {
		String intPart = context.NUMBER(0).getText();
		if(context.getChildCount() == 3){
			String decimalPart = context.NUMBER(1).getText();
			return Double.parseDouble(intPart + "." + decimalPart);
		}
		else
		{
			return Double.parseDouble(intPart);
		}
	}

	@Override
	public Double visitNumber(NumberContext context) {
		return context.realNumber().accept(this);
	}
}
