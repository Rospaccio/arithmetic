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
		String tempPackage = "org.merka.onthefly";
		String program = "2 + 3";
		double result = evaluateClassOnTheFly(program, tempPackage, "CompiledSum");
		assertEquals("result of current program: '" + program + "'", 5, result, 0.00001);
		
		program = "2*3";
		result = evaluateClassOnTheFly(program, tempPackage, "CompiledMul");
		assertEquals("result of current program: '" + program + "'", 6, result, 0.00001);
		
		program = "2 + 2 * 3";
		result = evaluateClassOnTheFly(program, tempPackage, "CompiledSumMul");
		assertEquals(8, result, 0.00001);
		
		program = "7 / 2";
		result = evaluateClassOnTheFly(program, tempPackage, "CompiledDiv");
		assertEquals(3.5, result, 0.00001);
		
		program = "7 - 2";
		result = evaluateClassOnTheFly(program, tempPackage, "CompiledSub");
		assertEquals(5, result, 0.00001);
		
		program = "1 + 1 + 1 * 2 * (4+2) * 2 - (1 + 1 - 4 + 1 +1 ) * 2 / 3 / 3 / 3";
		result = evaluateClassOnTheFly(program, tempPackage, "CompiledExp");
		assertEquals(26D, result, 0.00001);
	}

	public double evaluateClassOnTheFly(String program, String packageName, String className) throws Exception
	{
		TestArithmeticParser.ArithmeticTestErrorListener errorListener = new TestArithmeticParser.ArithmeticTestErrorListener();
		ProgramContext parseTreeRoot = TestArithmeticParser.parseProgram(program, errorListener);

		NaiveCompilerVisitor visitor = new NaiveCompilerVisitor(packageName,
				className);

		visitor.visit(parseTreeRoot);
		byte[] rawClass = visitor.getRawClass();
		String name = packageName + "." + className;
		ByteArrayClassLoader classLoader = new ByteArrayClassLoader(rawClass, name);
		Class<?> compiledClass = classLoader.loadClass(name);

		assertNotNull(compiledClass);

		Object instance = compiledClass.newInstance();
		Class<?>[] parameterTypes = new Class<?>[0];
		Method computeMethod = compiledClass.getMethod("compute", parameterTypes);
		Object[] args = new Object[0];
		double result = (double) computeMethod.invoke(instance, args);
		return result;
	}
	
	@Test
	public void testWriteClass() throws Exception
	{
		String program = "1 + 1 + 1 * 2 * (4+2) * 2 - (1 + 1 - 4 + 1 +1 ) * 2 / 3 / 3 / 3"; // "4 + 1";
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
