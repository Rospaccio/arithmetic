package org.merka.arithmetic.language.visitor;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.merka.arithmetic.language.ArithmeticParser.DifferenceContext;
import org.merka.arithmetic.language.ArithmeticParser.DivisionContext;
import org.merka.arithmetic.language.ArithmeticParser.ExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.SumContext;
import org.merka.arithmetic.language.ArithmeticParser.TermContext;
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
		return getNodeStringRepresentation("<program>", ctx);
	}

	@Override
	public String visitSum(SumContext ctx) {
		return getNodeStringRepresentation("<sum>", ctx);
	}
	
	@Override
	public String visitDifference(DifferenceContext ctx) {
		return getNodeStringRepresentation("<difference>", ctx);
	}

	@Override
	public String visitMultiplication(MultiplicationContext ctx) {
		return getNodeStringRepresentation("<multiplication>", ctx);
	}
	
	@Override
	public String visitDivision(DivisionContext ctx) {
		return getNodeStringRepresentation("<division>", ctx);
	}
	
//	@Override
//	public String visitRealNumber(RealNumberContext ctx) {
//		return getNodeStringRepresentation("<realNumber>", ctx);
//	}
	
	@Override
	public String visitNumber(NumberContext ctx) {
		return getNodeStringRepresentation("<number>", ctx);
	}
	
	@Override
	public String visitTerm(TermContext ctx) {
		return getNodeStringRepresentation("<term>", ctx);
	}
	
	@Override
	public String visitInnerExpression(InnerExpressionContext ctx) {
		return getNodeStringRepresentation("<innerExpression>", ctx);
	}

	private String getIndentation(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < indentationLevel; i++){
			builder.append("--");
		}
		return builder.toString();
	}
	
	private String getNodeStringRepresentation(String nodeName, ParserRuleContext context){
		return getIndentation() + nodeName + visitChildren(context);
	}
}
