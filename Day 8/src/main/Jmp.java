package main;

public class Jmp extends Instruction{

	public Jmp() {
		super("jmp");
	}

	@Override
	public void execute(Processor proc, int operand) {
		proc.setReg("pc", proc.regVal("pc") + operand - 1);
	}

}
