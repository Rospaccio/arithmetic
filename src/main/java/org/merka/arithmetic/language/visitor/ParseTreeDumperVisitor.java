package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.AdditiveExpContext;
import org.merka.arithmetic.language.ArithmeticParser.ExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicativeExpContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticVisitor;

public class ParseTreeDumperVisitor implements ArithmeticVisitor<String> {

	private int indentationLevel = 0;
	
	private void increaseIndentation(){
		this.indentationLevel += 1;
	}
	
	private void decreaseIndentation(){
		this.indentationLevel -= 1;
	}
	
	@Override
	public String visit(ParseTree arg0) {
		return arg0.accept(this);
	}

	@Override
	public String visitChildren(RuleNode arg0) {
		StringBuilder builder = new StringBuilder();
		builder.append(System.lineSeparator());
		int childrenCount = arg0.getChildCount();
		increaseIndentation();
		for(int i = 0; i < childrenCount; i++){
			builder.append(arg0.getChild(i).accept(this));
		}
		decreaseIndentation();
		return builder.toString();
	}

	@Override
	public String visitErrorNode(ErrorNode arg0) {
		
		return null;
	}

	@Override
	public String visitTerminal(TerminalNode arg0) {
		return getIndentation() + "<terminal> " + arg0.getText() + System.lineSeparator();
	}

	@Override
	public String visitProgram(ProgramContext ctx) {
		return getIndentation() + "<program>" + visitChildren(ctx);
	}

	@Override
	public String visitExpression(ExpressionContext ctx) {
		return getIndentation() + "<expression>" + visitChildren(ctx);
	}

	@Override
	public String visitAdditiveExp(AdditiveExpContext ctx) {
		return getIndentation() + "<additiveExp>" + visitChildren(ctx);
	}

	@Override
	public String visitMultiplicativeExp(MultiplicativeExpContext ctx) {
		return getIndentation() + "<multiplicativeExp>" + visitChildren(ctx);
	}

	@Override
	public String visitRealNumber(RealNumberContext ctx) {
		return getIndentation() + "<realNumber>" + visitChildren(ctx);
	}

	private String getIndentation(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < indentationLevel; i++){
			builder.append("--");
		}
		return builder.toString();
	}
}
