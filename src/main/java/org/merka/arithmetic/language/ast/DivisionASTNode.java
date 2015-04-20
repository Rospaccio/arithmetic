package org.merka.arithmetic.language.ast;

public class DivisionASTNode extends BinaryExpression {

	public DivisionASTNode(Number leftOperand, Number rightOperand) {
		super();
		setLeftOperand(leftOperand);
		setRightOperand(rightOperand);
	}

	
}
