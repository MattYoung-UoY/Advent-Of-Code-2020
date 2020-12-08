package main;

public class Nop extends Instruction{

	public Nop() {
		super("nop");
	}

	@Override
	public void execute(Processor proc, int operand) {
	}

}
