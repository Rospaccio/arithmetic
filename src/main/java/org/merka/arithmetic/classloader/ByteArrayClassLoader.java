package org.merka.arithmetic.classloader;

import org.apache.commons.lang.StringUtils;

public class ByteArrayClassLoader extends ClassLoader
{
	private byte[] rawClass;
	private String name;
	private Class<?> clazz; 
			
	public ByteArrayClassLoader(byte[] rawClass, String name)
	{
		if(StringUtils.isBlank(name)){
			throw new IllegalArgumentException("name");
		}
		if(rawClass == null){
			throw new IllegalArgumentException("rawClass");
		}
		this.rawClass = rawClass;
		this.name = name;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		if(this.name.equals(name)){
			if(clazz != null){
				return clazz;
			}
			else{
				return defineClass(this.name, this.rawClass, 0, this.rawClass.length);
			}
		}
		return super.findClass(name);
	}
}
