package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		long start = System.nanoTime();
		String[] input = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))){
			input = reader.lines().toArray(String[]::new);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Loading from file: " + (System.nanoTime() - start) + "ns");
		start = System.nanoTime();
		
		System.out.println(slope(input, 3, 1));
		
		System.out.println("P1 execution: " + (System.nanoTime() - start) + "ns");
		start = System.nanoTime();
		
		//System.out.println(slope(input, 1, 2));
		System.out.println(slope(input, 1, 1) * slope(input, 3, 1) * slope(input, 5, 1)* slope(input, 7, 1) * slope(input, 1, 2));
		
		System.out.println("P2 execution: " + (System.nanoTime() - start) + "ns");
		start = System.nanoTime();
	}
	
	private static int slope(String[] input, int slopeX, int slopeY) {
		int x = slopeX % input[0].length();
		int trees = 0;
		
		for(int y = slopeY; y < input.length; y+=slopeY) {
			if(input[y].charAt(x) == '#') trees++;
			x = (x + slopeX) % input[0].length();
		}
		
		return trees;
	}
	
}
