package org.merka.arithmetic.language.ast;

public class SumASTNode extends BinaryExpression {

	public SumASTNode(Number leftOpernad, Number rightOperand) {
		super();
		setLeftOperand(leftOperand);
		setRightOperand(rightOperand);
	}

	
}
