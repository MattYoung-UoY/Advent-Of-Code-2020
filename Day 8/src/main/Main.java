package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	private static Processor cpu = new Processor();
	
	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Part 1
		
		initCPU(input);
		
		cpu.executeLoopBreak();
		if(cpu.getTermCode() == 0) {
			System.out.println("No loop!");
		}else if(cpu.getTermCode() == 1){
			System.out.println("Loop!");
		}
		System.out.println(cpu + "\n------------------");
		
		//Part 2
		
		List<String[]> testPrograms = new ArrayList<>();
		
		for(int i = 0; !input[i].split(" ")[0].equals("stop"); i++) {
			if(input[i].split(" ")[0].equals("jmp")) {
				String[] testProgram = input.clone();
				testProgram[i] = testProgram[i].replaceAll("jmp", "nop");
				testPrograms.add(testProgram);
			}else if(input[i].split(" ")[0].equals("nop") ) {
				String[] testProgram = input.clone();
				testProgram[i] = testProgram[i].replaceAll("nop", "jmp");
				testPrograms.add(testProgram);
			}
		}
		
		System.out.println(testPrograms.size() + " test programs");
		
		for(String[] program: testPrograms) {
			initCPU(program);
			cpu.executeLoopBreak();
			if(cpu.getTermCode() == 0) {
				System.out.println(cpu);
				break;
			}
		}
		
	}
	
	private static void initCPU(String[] program) {
		cpu.reset();
		cpu.initRegister("acc", 0);
		cpu.addInstruction(new Acc());
		cpu.addInstruction(new Jmp());
		cpu.addInstruction(new Nop());
		cpu.addInstruction(new Stop());
		cpu.program(program);
	}
	
}
