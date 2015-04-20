package org.merka.arithmetic.language.ast;

public class MultiplicationASTNode extends BinaryExpression {
	public MultiplicationASTNode(Number leftOperand, Number rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
}
