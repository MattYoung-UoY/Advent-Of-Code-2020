package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
//
public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input2.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		for(int i = 0; i < input.length; i++) {
			input[i] = input[i].replace(" ", "");
			input[i] = input[i].replace("ask", "");
			input[i] = input[i].replace("mem[", "");
			input[i] = input[i].replace("]", "");
		}
		
		Map<String, String> memory = new HashMap<>();
		String mask = "";
		
		String[][] Inst = new String[input.length][2];
		for(int i = 0; i < input.length; i++) Inst[i] = input[i].split("=");
		
		//Pad Inst[1] with leading 0s until 36 bits
		for(int i = 0; i < Inst.length; i++) {
			
		}
		
		for(int i = 0; i < Inst.length; i++) {
			String op = Inst[i][0];
			String val = Inst[i][1];
			if(op.equals("m")) mask = val;
			else memory.put(op, mask(mask, val));
		}
		
		int res = 0;
		for(String val: memory.values()) res += Integer.parseInt(val, 2);
		System.out.println(res);
	}
	
	private static String mask(String mask, String val) {
		String res = "";
		for(int i = 0; i < mask.length(); i++) {
			switch(mask.charAt(i)) {
			case 'X':
				res += val.charAt(i);
				break;
			default:
				res += mask.charAt(i);
				break;
			}
		}
		return res;
	}
	
}
