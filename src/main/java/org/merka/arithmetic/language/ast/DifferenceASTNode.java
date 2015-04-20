package org.merka.arithmetic.language.ast;

public class DifferenceASTNode extends BinaryExpression {

	public DifferenceASTNode(Number leftOpernad, Number rightOperand){
		super();
		setLeftOperand(leftOpernad);
		setRightOperand(rightOperand);
	}
}
