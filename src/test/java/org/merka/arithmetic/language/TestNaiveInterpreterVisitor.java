package org.merka.arithmetic.language;

import static org.junit.Assert.*;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;

public class TestNaiveInterpreterVisitor {
	
	@Test
	@Ignore
	public void testVisitAdditiveExp() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testVisitMultiplicativeExp() {
		fail("Not yet implemented");
	}

}
