package org.merka.arithmetic.language.ast.visitor;

import org.merka.arithmetic.language.ast.DifferenceASTNode;
import org.merka.arithmetic.language.ast.DivisionASTNode;
import org.merka.arithmetic.language.ast.MultiplicationASTNode;
import org.merka.arithmetic.language.ast.NumberASTNode;
import org.merka.arithmetic.language.ast.SumASTNode;

public interface ArithmeticASTVisitor {

	public Number visitDifference(DifferenceASTNode differenceNode);
	public Number visitDivision(DivisionASTNode divisionNode);
	public Number visitMultiplication(MultiplicationASTNode multiplicationNode);
	public Number visitSum(SumASTNode sumNode);
	public Number visitNumber(NumberASTNode numberNode);
}
