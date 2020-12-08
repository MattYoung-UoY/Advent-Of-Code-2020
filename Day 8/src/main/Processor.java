package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

	private Map<String, Integer> registers = new HashMap<String, Integer>();
	private List<Instruction> instrSet = new ArrayList<Instruction>();
	private String[] program;
	private boolean terminated;
	private int termCode;
	
	public Processor() {
		reset();
	}
	
	public void initRegister(String regName, int initVal) {
		registers.put(regName, initVal);
	}
	
	public void addInstruction(Instruction instruction) {
		instrSet.add(instruction);
	}
	
	public int regVal(String reg) {
		return registers.get(reg);
	}
	
	public void setReg(String reg, int val) {
		registers.put(reg, val);
	}
	
	public void program(String[] program) {
		this.program = program;
	}
	
	public void step() {
		String inst = program[regVal("pc")];
		inst = inst.replaceAll("\\+", "");
		String[] parts = inst.split(" ");
		Instruction instr = null;
		for(Instruction i: instrSet) {
			if(i.opcode().equals(parts[0])) {
				instr=i;
				break;
			}
		}
		instr.execute(this, Integer.parseInt(parts[1]));
		setReg("pc", regVal("pc") + 1);
	}
	
	public void execute() {
		while(!terminated) step();
	}
	
	public void executeLoopBreak() {

		List<Integer> pcVisited = new ArrayList<Integer>();
		
		while(!terminated) {
			pcVisited.add(regVal("pc"));
			step();
			if(pcVisited.contains(regVal("pc"))) {
				terminate(1);
			}
		}
	}
	
	public void terminate(int termCode) {
		terminated = true;
		this.termCode = termCode;
	}
	
	public int getTermCode() {
		if(!terminated) {
			System.err.println("Code has not finished executing!");
			System.exit(1);
		}
		return termCode;
	}
	
	public void reset() {
		instrSet.clear();
		registers.clear();
		program = new String[0];
		terminated = false;
		termCode = -1;
		initRegister("pc", 0);
	}
	
	public String toString() {
		String toRet = "";
		for(String key: registers.keySet()) toRet += "{" + key + ", " + registers.get(key) + "}, ";
		return toRet;
	}
	
}
