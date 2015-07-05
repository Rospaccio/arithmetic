package org.merka.arithmetic.language.visitor;

import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.merka.arithmetic.language.ArithmeticBaseVisitor;
import org.merka.arithmetic.language.ArithmeticParser.AlgebraicSumContext;
import org.merka.arithmetic.language.ArithmeticParser.AtomicTermContext;
import org.merka.arithmetic.language.ArithmeticParser.InnerExpressionContext;
import org.merka.arithmetic.language.ArithmeticParser.MultiplicationContext;
import org.merka.arithmetic.language.ArithmeticParser.NumberContext;
import org.merka.arithmetic.language.ArithmeticParser.ProgramContext;
import org.merka.arithmetic.language.ArithmeticParser.RealNumberContext;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaiveCompilerVisitor extends ArithmeticBaseVisitor<String> implements Opcodes
{
	private static final Logger logger = LoggerFactory.getLogger(NaiveCompilerVisitor.class);
	
	private ClassWriter classWriter;
	private String packageName;
	private String className;
	private TraceClassVisitor traceClassVisitor;
	
	public byte[] getRawClass(){
		return classWriter.toByteArray();
	}
	
	private int methodCounter = 0;

	public NaiveCompilerVisitor(String packageName, String className)
	{
		if(StringUtils.isBlank(packageName)){
			throw new IllegalArgumentException("packageName");
		}
		if(StringUtils.isBlank(className)){
			throw new IllegalArgumentException("className");
		}
		this.packageName = packageName;
		this.className = className;
		this.classWriter = new ClassWriter(0);
		traceClassVisitor = new TraceClassVisitor(classWriter, new PrintWriter(System.out));
	}
	
	public String getQualifiedName(){
		String qualifiedName = packageName.replace(".", "/") + "/" + className;
		return qualifiedName;
	}
	
	public String getStackQualifiedName(){
		return "L" + getQualifiedName() + ";";
	}
	
	@Override
	public String visitProgram(ProgramContext ctx)
	{ 
		// builds the prolog of the class
//		FieldVisitor fv;
		MethodVisitor mv;
//		AnnotationVisitor av0;
		
		traceClassVisitor.visit(V1_7, ACC_PUBLIC + ACC_SUPER,
				getQualifiedName(), null, "java/lang/Object",
				null);

		traceClassVisitor.visitSource(className + ".java", null);
		
		// builds the default constructor
		{
			mv = traceClassVisitor.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			//mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", getStackQualifiedName(),
					null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		
		// passes itself into the child node
		String innerMethodName = ctx.expression().accept(this);
		
		// creates a top level method named "compute"
		// that internally calls the previous generated innerMethodName
		{
				mv = classWriter.visitMethod(ACC_PUBLIC, "compute", "()D", null, null);
				mv.visitCode();
				Label l0 = new Label();
				mv.visitLabel(l0);
				//mv.visitLineNumber(14, l0);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitMethodInsn(INVOKEVIRTUAL, getQualifiedName(), innerMethodName, "()D", false);
				mv.visitInsn(DRETURN);
				Label l1 = new Label();
				mv.visitLabel(l1);
				mv.visitLocalVariable("this", getStackQualifiedName(), null, l0, l1, 0);
				mv.visitMaxs(2, 1);
				mv.visitEnd();
		}
		
		// build the epilog of the class
		traceClassVisitor.visitEnd();
		return "compute";
	}

	@Override
	public String visitAlgebraicSum(AlgebraicSumContext ctx)
	{
		int byteCodeOp = -1;
		String operand = ctx.getChild(1).getText();
		if(operand.equals("+")){
			byteCodeOp = DADD;
		}
		else if(operand.equals("-")){
			byteCodeOp = DSUB;
		}
		else
		{
			throw new ArithmeticException("Something has really gone wrong");
		}
		
		String leftArgumentMethod = ctx.expression(0).accept(this);
		String rightArgumentMethod = ctx.expression(1).accept(this);
		
		// builds a method whose body is
		// 'return <leftArgumentMethod>() + rightArgumentMethod()'
		
		String currentMethodName = getNextMethodName();
		MethodVisitor methodVisitor;
		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC, currentMethodName, "()D", null, null);
			methodVisitor.visitCode();
			Label l0 = new Label();
			methodVisitor.visitLabel(l0);

			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, getQualifiedName(), leftArgumentMethod, "()D", false);
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, getQualifiedName(), rightArgumentMethod, "()D", false);
			methodVisitor.visitInsn(byteCodeOp);
			methodVisitor.visitInsn(DRETURN);
			Label l1 = new Label();
			methodVisitor.visitLabel(l1);
			methodVisitor.visitLocalVariable("this", getStackQualifiedName(), null, l0, l1, 0);
			methodVisitor.visitMaxs(4, 1);
			methodVisitor.visitEnd();
		}
		
		return currentMethodName;
	}

	@Override
	public String visitMultiplication(MultiplicationContext ctx)
	{
		int bytecodeOp = -1;
		String operand = ctx.getChild(1).getText();
		if(operand.equals("*")){
			bytecodeOp = DMUL;
		}
		else if(operand.equals("/")){
			bytecodeOp = DDIV;
		}
		else
		{
			throw new ArithmeticException("Something has really gone wrong");
		}
		
		String leftArgumentMethod = ctx.expression(0).accept(this);
		String rightArgumentMethod = ctx.expression(1).accept(this);
		
		// builds a method whose body is
		// 'return <leftArgumentMethod>() + rightArgumentMethod()'
		
		String currentMethodName = getNextMethodName();
		MethodVisitor methodVisitor;
		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC, currentMethodName, "()D", null, null);
			methodVisitor.visitCode();
			Label l0 = new Label();
			methodVisitor.visitLabel(l0);

			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, getQualifiedName(), leftArgumentMethod, "()D", false);
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, getQualifiedName(), rightArgumentMethod, "()D", false);
			methodVisitor.visitInsn(bytecodeOp);
			methodVisitor.visitInsn(DRETURN);
			Label l1 = new Label();
			methodVisitor.visitLabel(l1);
			methodVisitor.visitLocalVariable("this", getStackQualifiedName(), null, l0, l1, 0);
			methodVisitor.visitMaxs(4, 1);
			methodVisitor.visitEnd();
		}
		
		return currentMethodName;
	}

	@Override
	public String visitAtomicTerm(AtomicTermContext ctx)
	{
		return ctx.term().accept(this);
	}

	@Override
	public String visitNumber(NumberContext ctx)
	{
		
		return super.visitNumber(ctx);
	}

	@Override
	public String visitInnerExpression(InnerExpressionContext ctx)
	{
		return ctx.expression().accept(this);
	}

	@Override
	public String visitRealNumber(RealNumberContext ctx)
	{
		double value;
		String intPart = ctx.NUMBER(0).getText();
		if(ctx.getChildCount() == 3){
			String decimalPart = ctx.NUMBER(1).getText();
			value = Double.parseDouble(intPart + "." + decimalPart);
		}
		else
		{
			value = Double.parseDouble(intPart);
		}
		
		String currentMethodName = getNextMethodName();
		MethodVisitor mv;
		// builds a method whose body is
		// 'return <value>D';
		{
			mv = classWriter.visitMethod(ACC_PUBLIC, currentMethodName, "()D", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			
			mv.visitLdcInsn(new Double(value));
			mv.visitInsn(DRETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", getStackQualifiedName(), null, l0, l1, 0);
			mv.visitMaxs(2, 1);
			mv.visitEnd();
		}
		return currentMethodName;
	}
	
	private String getNextMethodName(){
		String nextMethodName = "compute_" + methodCounter;
		methodCounter += 1;
		return nextMethodName;
	}
}
