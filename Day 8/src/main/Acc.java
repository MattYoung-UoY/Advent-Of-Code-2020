package main;

public class Acc extends Instruction{

	public Acc() {
		super("acc");
	}

	@Override
	public void execute(Processor proc, int operand) {
		proc.setReg("acc", proc.regVal("acc") + operand);
	}

}
