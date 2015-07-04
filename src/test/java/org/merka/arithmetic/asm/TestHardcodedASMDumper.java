package org.merka.arithmetic.asm;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.merka.arithmetic.classloader.ByteArrayClassLoader;

public class TestHardcodedASMDumper
{

	@Test
	public void test() throws Exception
	{
		byte[] rawClass = HardcodedASMDumper.dump();
		String name = "org.merka.arithmetic.asm.OnTheFlyCompiledExpression";
		ByteArrayClassLoader classLoader = new ByteArrayClassLoader(rawClass, name);
		Class<?> compiledClass = classLoader.loadClass(name);
		
		assertNotNull(compiledClass);
		
		Object instance = compiledClass.newInstance();
		Class<?>[] parameterTypes = new Class<?>[0];
		Method computeMethod = compiledClass.getMethod("compute", parameterTypes);
		Object[] args = new Object[0];
		double result = (double)computeMethod.invoke(instance, args);
		assertEquals("result", 5, result, 0.00001);
	}

}
