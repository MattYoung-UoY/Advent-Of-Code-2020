package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// 7 chars for row
		// 3 chars for column
		
		List<Integer> ids = Arrays.stream(input).map(Main::seatID).collect(Collectors.toList());
		
		int max = ids.stream().max(Integer::compare).orElse(-1);
		
		System.out.println("P1: " + max);
		
		for(int i = 0; i < max; i++) {
			if(!ids.contains(i)) {
				if(ids.contains(i+1) && ids.contains(i-1)) {
					System.out.println("P2: " + i);
				}
			}
		}
	}

	private static int seatID(String seatBSP) {
		int row = binSearch(seatBSP.substring(0, 7), 0, 127, 'F', 'B');
		int col = binSearch(seatBSP.substring(7), 0, 7, 'L', 'R');
		return (row * 8) + col;
	}

	private static int binSearch(String searchStr, int lower, int upper, char lowerChar, char upperChar) {
		for (char searchChar : searchStr.toCharArray()) {
			int midpoint = (int) Math.round((float) (lower + upper) / 2);
			if (searchChar == lowerChar)
				upper = midpoint;
			else if (searchChar == upperChar)
				lower = midpoint;
		}
		return lower;
	}

}
