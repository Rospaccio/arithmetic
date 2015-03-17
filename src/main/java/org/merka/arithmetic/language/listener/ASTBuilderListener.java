package org.merka.arithmetic.language.listener;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticListener;
import org.merka.arithmetic.language.ArithmeticParser.DifferenceContext;
import org.merka.arithmetic.language.ArithmeticParser.DivisionContext;
import org.merka.arithmetic.language.ArithmeticParser.ExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
//import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticParser.SumContext;
import org.merka.arithmetic.language.ArithmeticParser.TermContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASTBuilderListener implements ArithmeticListener {

	private static final Logger logger = LoggerFactory.getLogger(ASTBuilderListener.class);
	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
//		 logger.info("Entering rule: " + arg0.getClass().getName());
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
//		logger.info("Exiting rule: " + arg0.getClass().getName());
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		
		
	}

	@Override
	public void enterSum(SumContext ctx) {
		
	}

	@Override
	public void exitSum(SumContext ctx) {
		
		
	}

//	@Override
//	public void enterExpression(ExpressionContext ctx) {
//		
//		
//	}
//
//	@Override
//	public void exitExpression(ExpressionContext ctx) {
//		
//		
//	}

	@Override
	public void enterMultiplication(MultiplicationContext ctx) {
		
		
	}

	@Override
	public void exitMultiplication(MultiplicationContext ctx) {
		
		
	}

//	@Override
//	public void enterRealNumber(RealNumberContext ctx) {
//		
//		
//	}
//
//	@Override
//	public void exitRealNumber(RealNumberContext ctx) {
//		
//		
//	}

	@Override
	public void enterInnerExpression(InnerExpressionContext ctx) {
		
		
	}

	@Override
	public void exitInnerExpression(InnerExpressionContext ctx) {
		
		
	}

	@Override
	public void enterDifference(DifferenceContext ctx) {
		
		
	}

	@Override
	public void exitDifference(DifferenceContext ctx) {
		
		
	}

	@Override
	public void enterNumber(NumberContext ctx) {
		
		
	}

	@Override
	public void exitNumber(NumberContext ctx) {
		
		
	}

	@Override
	public void enterProgram(ProgramContext ctx) {
		
		
	}

	@Override
	public void exitProgram(ProgramContext ctx) {
		
		
	}

	@Override
	public void enterDivision(DivisionContext ctx) {
		
		
	}

	@Override
	public void exitDivision(DivisionContext ctx) {
		
		
	}

	@Override
	public void enterTerm(TermContext ctx) {
		
		
	}

	@Override
	public void exitTerm(TermContext ctx) {
		
		
	}

}
