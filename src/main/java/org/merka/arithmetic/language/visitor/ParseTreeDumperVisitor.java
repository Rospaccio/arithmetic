package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.AlgebraicSumContext;
import org.merka.arithmetic.language.ArithmeticParser.AtomicTermContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.merka.arithmetic.language.ArithmeticVisitor;

public class ParseTreeDumperVisitor implements ArithmeticVisitor<String> {

	private StringBuilder appender = new StringBuilder();

	private int indentationLevel = 0;

	private void increaseIndentation() {
		this.indentationLevel += 1;
	}

	private void decreaseIndentation() {
		this.indentationLevel -= 1;
	}

	@Override
	public String visit(ParseTree tree) {
		return tree.accept(this);
	}

	@Override
	public String visitChildren(RuleNode node) {
		StringBuilder builder = new StringBuilder();
		builder.append(System.lineSeparator());
		int childrenCount = node.getChildCount();
		increaseIndentation();
		for (int i = 0; i < childrenCount; i++) {
			builder.append(node.getChild(i).accept(this));
		}
		decreaseIndentation();
		return builder.toString();
	}

	@Override
	public String visitTerminal(TerminalNode node) {
		return getIndentation() + "TERMINAL: " + node.getText() + System.lineSeparator();
	}

	@Override
	public String visitErrorNode(ErrorNode node) {

		return null;
	}

	@Override
	public String visitProgram(ProgramContext context) {
		return getIndentation() + visitChildren(context) + System.lineSeparator();
	}

	@Override
	public String visitAlgebraicSum(AlgebraicSumContext context) {
		return getIndentation() + "AlgebraicSum" + visitChildren(context);
	}

	@Override
	public String visitMultiplication(MultiplicationContext context) {
		return getIndentation() + "Multiplication" + visitChildren(context);
	}

	@Override
	public String visitAtomicTerm(AtomicTermContext context) {
		return getIndentation() + "AtomicTerm" + visitChildren(context);
	}

	@Override
	public String visitNumber(NumberContext context) {
		return getIndentation() + "Number" + visitChildren(context);
	}

	@Override
	public String visitInnerExpression(InnerExpressionContext context) {
		return getIndentation() + "InnerExpression" + visitChildren(context);
	}

	@Override
	public String visitRealNumber(RealNumberContext context) {
		String intPart = context.NUMBER(0).getText();
		if(context.getChildCount() == 3){
			String decimal = context.NUMBER(1).getText();
			return getIndentation() + intPart + "." + decimal + System.lineSeparator();
		}
		else return getIndentation() + intPart + System.lineSeparator();
	}

	private String getIndentation() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < indentationLevel; i++) {
			builder.append("--");
		}
		return builder.toString();
	}
}
