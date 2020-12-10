package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Matt
 *
 * Better to read. Worse in execution time and behaviour.
 * I'm almost ashamed to have written this code.
 */
public class Better {

	public static void main(String[] args) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")));
		List<Integer> input = reader.lines().mapToInt(s -> Integer.parseInt(s)).boxed().collect(Collectors.toList());
		reader.close();
		
		input.forEach(i -> input.forEach(j -> {if(i+j == 2020) System.out.println(i*j);}));
		input.forEach(i -> input.forEach(j -> input.forEach(k -> {if(i+j+k == 2020) System.out.println(i*j*k);})));
	}
	
}
