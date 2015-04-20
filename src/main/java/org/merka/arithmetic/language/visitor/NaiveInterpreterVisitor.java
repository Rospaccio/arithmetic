package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.DifferenceContext;
import org.merka.arithmetic.language.ArithmeticParser.DivisionContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
//import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticParser.SumContext;
import org.merka.arithmetic.language.ArithmeticParser.TermContext;
import org.merka.arithmetic.language.ArithmeticVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		return ctx.expression().accept(this);
	}

	@Override
	public Double visitSum(SumContext ctx) {
		Double leftOpernad = ctx.getChild(0).accept(this);
		Double rightOperand = ctx.getChild(2).accept(this);
		return leftOpernad + rightOperand;
	}

	@Override
	public Double visitTerm(TermContext ctx) {
		return ctx.getChild(0).accept(this);
	}

	@Override
	public Double visitDifference(DifferenceContext ctx) {
		Double leftOpernad = ctx.getChild(0).accept(this);
		Double rightOperand = ctx.getChild(2).accept(this);
		return leftOpernad - rightOperand;
	}

	@Override
	public Double visitMultiplication(MultiplicationContext ctx) {
		Double leftOpernad = ctx.getChild(0).accept(this);
		Double rightOperand = ctx.getChild(2).accept(this);
		return leftOpernad * rightOperand;
	}

	@Override
	public Double visitNumber(NumberContext ctx) {
		if(numberNodesAnnotations.get(ctx) != null){
//			logger.info("value found in cache");
			return numberNodesAnnotations.get(ctx);
		}
		Double value = Double.valueOf( ctx.NUMBER(0).getText() );
		if(ctx.getChildCount() == 3){
			Double decimalPart = Double.valueOf("0." + ctx.NUMBER(1).getText());
			value += decimalPart;
		}
//		logger.info("storing value in cache");
		numberNodesAnnotations.put(ctx, value);
		return value;
	}

	@Override
	public Double visitInnerExpression(InnerExpressionContext ctx) {
		return ctx.expression().accept(this);
	}

	@Override
	public Double visitDivision(DivisionContext ctx) {
		Double leftOpernad = ctx.getChild(0).accept(this);
		Double rightOperand = ctx.getChild(2).accept(this);
		return leftOpernad / rightOperand;
	}

//	@Override
//	public Double visitRealNumber(RealNumberContext ctx) {
//		
//		return null;
//	}

}
