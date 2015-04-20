package org.merka.arithmetic.language.ast.visitor;

import org.merka.arithmetic.language.ast.ArithmeticASTNode;
import org.merka.arithmetic.language.ast.DifferenceASTNode;
import org.merka.arithmetic.language.ast.DivisionASTNode;
import org.merka.arithmetic.language.ast.MultiplicationASTNode;
import org.merka.arithmetic.language.ast.NumberASTNode;
import org.merka.arithmetic.language.ast.SumASTNode;

public class EvaluationVisitor implements ArithmeticASTVisitor {

	@Override
	public Number visitDifference(DifferenceASTNode differenceNode) {
		
		return null;
	}

	@Override
	public Number visitDivision(DivisionASTNode divisionNode) {
		
		return null;
	}

	@Override
	public Number visitMultiplication(MultiplicationASTNode multiplicationNode) {
		
		return null;
	}

	@Override
	public Number visitSum(SumASTNode sumNode) {
		
		return null;
	}

	@Override
	public Number visitNumber(NumberASTNode numberNode) {
		
		return null;
	}

}
