package org.merka.arithmetic.language.ast.visitor;

import org.merka.arithmetic.language.ast.DifferenceASTNode;
import org.merka.arithmetic.language.ast.DivisionASTNode;
import org.merka.arithmetic.language.ast.MultiplicationASTNode;
import org.merka.arithmetic.language.ast.NumberASTNode;
import org.merka.arithmetic.language.ast.SumASTNode;

public class EvaluationVisitor implements ArithmeticASTVisitor {

	@Override
	public Number visitDifference(DifferenceASTNode differenceNode) {
		Number leftOperand = (Number)differenceNode.getLeftOperand().accept(this);
		Number rightOperand = (Number)differenceNode.getRightOperand().accept(this);
		return leftOperand.doubleValue() - rightOperand.doubleValue();
	}

	@Override
	public Number visitDivision(DivisionASTNode divisionNode) {
		Number leftOperand = (Number)divisionNode.getLeftOperand().accept(this);
		Number rightOperand = (Number)divisionNode.getRightOperand().accept(this);
		return leftOperand.doubleValue() / rightOperand.doubleValue();
	}

	@Override
	public Number visitMultiplication(MultiplicationASTNode multiplicationNode) {
		Number leftOperand = (Number)multiplicationNode.getLeftOperand().accept(this);
		Number rightOperand = (Number)multiplicationNode.getRightOperand().accept(this);
		return leftOperand.doubleValue() * rightOperand.doubleValue();
	}

	@Override
	public Number visitSum(SumASTNode sumNode) {
		Number leftOperand = (Number)sumNode.getLeftOperand().accept(this);
		Number rightOperand = (Number)sumNode.getRightOperand().accept(this);
		return leftOperand.doubleValue() + rightOperand.doubleValue();
	}

	@Override
	public Number visitNumber(NumberASTNode numberNode) {
		return numberNode.getValue();
	}

}
