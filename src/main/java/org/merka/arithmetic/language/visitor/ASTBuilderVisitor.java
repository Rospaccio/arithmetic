package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
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
import org.merka.arithmetic.language.ast.ArithmeticASTNode;
import org.merka.arithmetic.language.ast.DifferenceASTNode;
import org.merka.arithmetic.language.ast.DivisionASTNode;
import org.merka.arithmetic.language.ast.MultiplicationASTNode;
import org.merka.arithmetic.language.ast.NumberASTNode;
import org.merka.arithmetic.language.ast.SumASTNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASTBuilderVisitor implements ArithmeticVisitor<ArithmeticASTNode> {

	private static final Logger logger = LoggerFactory.getLogger(ASTBuilderVisitor.class);

	public static final String PLUS = "+";
	public static final String MINUS = "-";
	public static final String TIMES = "*";
	public static final String DIVIDED = "/";
	
	@Override
	public ArithmeticASTNode visit(ParseTree tree) {
		return tree.accept(this);
	}

	@Override
	public ArithmeticASTNode visitChildren(RuleNode node) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitTerminal(TerminalNode node) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitErrorNode(ErrorNode node) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitProgram(ProgramContext context) {
		return context.expression().accept(this);
	}

	@Override
	public ArithmeticASTNode visitAlgebraicSum(AlgebraicSumContext context) {
		String operand = context.getChild(1).getText();
		ArithmeticASTNode leftOperand = context.expression(0).accept(this);
		ArithmeticASTNode rightOperand = context.expression(1).accept(this);
		if(operand.equals(PLUS)){
			return new SumASTNode(leftOperand, rightOperand);
		}
		else if (operand.equals(MINUS)){
			return new DifferenceASTNode(leftOperand, rightOperand);
		}
		else{
			throw new ArithmeticException("Something has really gone wrong: operand '" 
					+ operand + "' comes as a complete surprise");
		}
	}

	@Override
	public ArithmeticASTNode visitMultiplication(MultiplicationContext context) {
		String operand = context.getChild(1).getText();
		ArithmeticASTNode leftOperand = context.expression(0).accept(this);
		ArithmeticASTNode rightOperand = context.expression(1).accept(this);
		if(operand.equals(TIMES)){
			return new MultiplicationASTNode(leftOperand, rightOperand);
		}
		else if (operand.equals(DIVIDED)){
			return new DivisionASTNode(leftOperand, rightOperand);
		}
		else{
			throw new ArithmeticException("Something has really gone wrong: operand '" 
					+ operand + "' comes as a complete surprise");
		}
	}

	@Override
	public ArithmeticASTNode visitAtomicTerm(AtomicTermContext context) {
		return context.term().accept(this);
	}

	@Override
	public ArithmeticASTNode visitNumber(NumberContext context) {
		return context.realNumber().accept(this);
	}

	@Override
	public ArithmeticASTNode visitInnerExpression(InnerExpressionContext context) {
		return context.expression().accept(this);
	}

	@Override
	public ArithmeticASTNode visitRealNumber(RealNumberContext context) {
		String intPart = context.NUMBER(0).getText();
		String decimalPart = "";
		if(context.getChildCount() > 2){
			decimalPart = "." + context.NUMBER(1).getText();
		}
		Double value = Double.parseDouble(intPart + decimalPart);
		return new NumberASTNode(value);
		
	}

}
