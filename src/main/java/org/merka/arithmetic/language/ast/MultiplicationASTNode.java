package org.merka.arithmetic.language.ast;

import org.merka.arithmetic.language.ast.visitor.ArithmeticASTVisitor;

public class MultiplicationASTNode extends BinaryExpression {
	public MultiplicationASTNode(ArithmeticASTNode leftOperand, ArithmeticASTNode rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	@Override
	public Object accept(ArithmeticASTVisitor visitor) {
		return visitor.visitMultiplication(this);
	}
}
