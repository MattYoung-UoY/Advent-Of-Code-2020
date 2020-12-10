package main;

public class Stop extends Instruction{

	public Stop() {
		super("stop");
	}

	@Override
	public void execute(Processor proc, int operand) {
		proc.terminate(operand);
	}

}
