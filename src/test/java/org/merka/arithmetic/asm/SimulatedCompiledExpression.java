package org.merka.arithmetic.asm;


/**
 * A trivial class whose purpose is to being parsed 
 * by ASMifier in order to look and learn how bytecode for
 * an arbitrary class can be generated.
 * @author merka
 *
 */
public class SimulatedCompiledExpression
{
	public double compute(){
		return compute_0();
	}
	
	public double compute_0(){
		return compute_01() + compute_02();
	}
	
	public double compute_01(){
		return 2.0D;
	}
	
	public double compute_02(){
		return 3.0;
	}
}
