package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.DifferenceContext;
import org.merka.arithmetic.language.ArithmeticParser.DivisionContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.SumContext;
import org.merka.arithmetic.language.ArithmeticParser.TermContext;
import org.merka.arithmetic.language.ArithmeticVisitor;
import org.merka.arithmetic.language.ast.ArithmeticASTNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASTBuilderVisitor implements ArithmeticVisitor<ArithmeticASTNode> {

	private static final Logger logger = LoggerFactory.getLogger(ASTBuilderVisitor.class);
	
	@Override
	public ArithmeticASTNode visit(ParseTree arg0) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitChildren(RuleNode arg0) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitErrorNode(ErrorNode arg0) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitTerminal(TerminalNode arg0) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitProgram(ProgramContext ctx) {
		return ctx.expression().accept(this);
	}

	@Override
	public ArithmeticASTNode visitSum(SumContext ctx) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitTerm(TermContext ctx) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitDifference(DifferenceContext ctx) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitMultiplication(MultiplicationContext ctx) {
		
		return null;
	}

	public Number extractNumberValue(NumberContext context){
		Double value = Double.valueOf( context.NUMBER(0).getText() );
		if(context.getChildCount() == 3){
			Double decimalPart = Double.valueOf("0." + context.NUMBER(1).getText());
			value += decimalPart;
		}
		return value;
	}
	
	@Override
	public ArithmeticASTNode visitNumber(NumberContext ctx) {
		return null;
	}

	@Override
	public ArithmeticASTNode visitInnerExpression(InnerExpressionContext ctx) {
		
		return null;
	}

	@Override
	public ArithmeticASTNode visitDivision(DivisionContext ctx) {
		
		return null;
	}

}
