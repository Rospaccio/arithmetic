package org.merka.arithmetic.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class HardcodedASMDumper implements Opcodes
{
	public static byte[] dump() throws Exception
	{

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER,
				"org/merka/arithmetic/asm/OnTheFlyCompiledExpression", null, "java/lang/Object",
				null);

		cw.visitSource("OnTheFlyCompiledExpression.java", null);

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(3, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lorg/merka/arithmetic/asm/OnTheFlyCompiledExpression;",
					null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "compute", "()D", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(6, l0);
			mv.visitLdcInsn(new Double("2.0"));
			mv.visitVarInsn(DSTORE, 1);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(7, l1);
			mv.visitLdcInsn(new Double("3.0"));
			mv.visitVarInsn(DSTORE, 3);
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLineNumber(8, l2);
			mv.visitVarInsn(DLOAD, 1);
			mv.visitVarInsn(DLOAD, 3);
			mv.visitInsn(DADD);
			mv.visitInsn(DRETURN);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLocalVariable("this", "Lorg/merka/arithmetic/asm/OnTheFlyCompiledExpression;",
					null, l0, l3, 0);
			mv.visitLocalVariable("leftArgumnet", "D", null, l1, l3, 1);
			mv.visitLocalVariable("rightArgument", "D", null, l2, l3, 3);
			mv.visitMaxs(4, 5);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
