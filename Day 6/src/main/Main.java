package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		
		//Input 3 = 19
		
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			String inp = "";
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0) {
					inp += "\n";
					continue;
				}
				inp += "," + line;
			}
			input = inp.split("\n");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println(Arrays.stream(input).map(Main::uniqueChars).collect(Collectors.summingInt(Integer::intValue)));
		System.out.println(Arrays.stream(input).map(Main::overlappingChars).collect(Collectors.summingInt(Integer::intValue)));
		
	}
	
	private static int uniqueChars(String inp) {
		inp = inp.replaceAll(",", "");
		List<Character> chs = new ArrayList<Character>();
		for(char ch: inp.toCharArray()) if(!chs.contains(ch)) chs.add(ch);
		return chs.size();
	}
	
	private static int overlappingChars(String inp) {
		String[] people = inp.replaceFirst(",", "").split(",");
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		for(String person: people) {
			for(char ch: person.toCharArray()) {
				if(count.containsKey(ch)) {
					count.put(ch, count.get(ch) + 1);
				}else {
					count.put(ch, 1);
				}
			}
		}
		return (int) count.values().stream().filter(val -> val.equals(people.length)).count();
	}
	
}
