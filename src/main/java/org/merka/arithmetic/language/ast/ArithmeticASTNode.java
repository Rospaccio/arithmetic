package org.merka.arithmetic.language.ast;

import org.merka.arithmetic.language.ast.visitor.ArithmeticASTVisitor;

public abstract class ArithmeticASTNode {

	public abstract Object accept(ArithmeticASTVisitor visitor);
	
}
