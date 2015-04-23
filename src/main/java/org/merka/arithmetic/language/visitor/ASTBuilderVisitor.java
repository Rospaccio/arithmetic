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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ASTBuilderVisitor implements ArithmeticVisitor<ArithmeticASTNode> {

	private static final Logger logger = LoggerFactory.getLogger(ASTBuilderVisitor.class);

	@Override
	public ArithmeticASTNode visit(ParseTree tree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitChildren(RuleNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitProgram(ProgramContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitAlgebraicSum(AlgebraicSumContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitMultiplication(MultiplicationContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitAtomicTerm(AtomicTermContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitNumber(NumberContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitInnerExpression(InnerExpressionContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArithmeticASTNode visitRealNumber(RealNumberContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
