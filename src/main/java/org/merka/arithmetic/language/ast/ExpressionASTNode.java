package org.merka.arithmetic.language.ast;

import org.merka.arithmetic.language.ast.visitor.ArithmeticASTVisitor;

public class ExpressionASTNode extends ArithmeticASTNode
{

	@Override
	public Object accept(ArithmeticASTVisitor visitor)
	{
		throw new RuntimeException("Not implemented!!");
	}

}
