package org.merka.arithmetic.asm;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.merka.arithmetic.classloader.ByteArrayClassLoader;
import org.merka.arithmetic.language.TestArithmeticParser;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.visitor.NaiveCompilerVisitor;

public class TestNaiveCompilerVisitor
{

	@Test
	public void testOnTheFly() throws Exception
	{
		String program = "2 + 3";
		TestArithmeticParser.ArithmeticTestErrorListener errorListener = new TestArithmeticParser.ArithmeticTestErrorListener();
		ProgramContext parseTreeRoot = TestArithmeticParser.parseProgram(program, errorListener);

		NaiveCompilerVisitor visitor = new NaiveCompilerVisitor("org.merka.onthefly",
				"CompiledExpression");

		visitor.visit(parseTreeRoot);
		byte[] rawClass = visitor.getRawClass();
		String name = "org.merka.onthefly.CompiledExpression";
		ByteArrayClassLoader classLoader = new ByteArrayClassLoader(rawClass, name);
		Class<?> compiledClass = classLoader.loadClass(name);

		assertNotNull(compiledClass);

		Object instance = compiledClass.newInstance();
		Class<?>[] parameterTypes = new Class<?>[0];
		Method computeMethod = compiledClass.getMethod("compute", parameterTypes);
		Object[] args = new Object[0];
		double result = (double) computeMethod.invoke(instance, args);
		assertEquals("result", 5, result, 0.00001);
	}

	@Test
	public void testWriteClass() throws Exception
	{
		String program = "4 + 1";
		TestArithmeticParser.ArithmeticTestErrorListener errorListener = new TestArithmeticParser.ArithmeticTestErrorListener();
		ProgramContext parseTreeRoot = TestArithmeticParser.parseProgram(program, errorListener);

		NaiveCompilerVisitor visitor = new NaiveCompilerVisitor("org.merka.onthefly",
				"CompiledExpression");

		visitor.visit(parseTreeRoot);
		byte[] rawClass = visitor.getRawClass();
		
		File file = new File("target/org/merka/onthefly/CompiledExpression.class");
		FileUtils.writeByteArrayToFile(file, rawClass);
	}
}
