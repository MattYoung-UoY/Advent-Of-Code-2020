package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))){
			input = reader.lines().toArray(String[]::new);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println(Arrays.stream(input).filter(password -> validPassword(password)).count());

		System.out.println(Arrays.stream(input).filter(password -> newValidation(password)).count());
	}
	
	private static boolean newValidation(String input) {
		String res = input.replaceAll(":", "");
		String[] parts = res.split(" ");
		String[] bounds = parts[0].split("-");
		int i = Integer.parseInt(bounds[0]) - 1;
		int j = Integer.parseInt(bounds[1]) - 1;
		char charToFind = parts[1].charAt(0);
		String password = parts[2];
		return (password.charAt(i) == charToFind) != (password.charAt(j) == charToFind);
	}
	
	private static boolean validPassword(String input) {
		String res = input.replaceAll(":", "");
		String[] parts = res.split(" ");
		String[] bounds = parts[0].split("-");
		int start = Integer.parseInt(bounds[0]);
		int end = Integer.parseInt(bounds[1]);
		int occurances = (int) parts[2].chars().filter(ch -> ch == parts[1].charAt(0)).count();
		return (occurances <= end) && (occurances >= start);
	}
	
	
}
