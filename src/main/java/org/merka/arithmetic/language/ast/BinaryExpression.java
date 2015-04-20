package org.merka.arithmetic.language.ast;

public abstract class BinaryExpression extends ArithmeticASTNode {

	public ArithmeticASTNode leftOperand;
	public ArithmeticASTNode rightOperand;
	public ArithmeticASTNode getLeftOperand() {
		return leftOperand;
	}
	public void setLeftOperand(ArithmeticASTNode leftOperand) {
		this.leftOperand = leftOperand;
	}
	public ArithmeticASTNode getRightOperand() {
		return rightOperand;
	}
	public void setRightOperand(ArithmeticASTNode rightOperand) {
		this.rightOperand = rightOperand;
	}
	
}
