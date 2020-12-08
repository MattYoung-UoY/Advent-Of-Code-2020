package main;

public abstract class Instruction {

	private String opcode;
	
	public Instruction(String opcode) {
		this.opcode = opcode;
	}
	
	public abstract void execute(Processor proc, int operand);
	
	public String opcode() {
		return opcode;
	}
	
	public String toString() {
		return opcode;
	}
	
}
