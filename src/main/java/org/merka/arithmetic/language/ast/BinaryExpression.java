package org.merka.arithmetic.language.ast;

public abstract class BinaryExpression extends ArithmeticASTNode {

	public Number leftOperand;
	public Number rightOperand;
	protected Number getLeftOperand() {
		return leftOperand;
	}
	protected void setLeftOperand(Number leftOperand) {
		this.leftOperand = leftOperand;
	}
	protected Number getRightOperand() {
		return rightOperand;
	}
	protected void setRightOperand(Number rightOperand) {
		this.rightOperand = rightOperand;
	}
	
}
