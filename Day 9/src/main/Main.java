package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		long[] input = null;
		try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))){
		input = reader.lines().mapToLong(s -> Long.parseLong(s)).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		long invalid = 0;
		List<Long> prevInts = new ArrayList<>();
		for(int i = 25; i < input.length; i++) {
			for(int j = 0; j < 25; j++) prevInts.add(input[i-j-1]);
			if(!validData(input[i], prevInts)) {
				invalid = input[i];
				break;
			}
			prevInts.clear();
		}
	
		System.out.println(invalid);
		
		List<Long> set = findSet(input, invalid);
		
		Collections.sort(set);
		System.out.println(set);
		long encWeak = set.get(0) + set.get(set.size()-1) ;
		System.out.println(encWeak);
		
	}
	
	private static List<Long> findSet(long[] input, long target){
		List<Long> set = new ArrayList<Long>();
		for(int i = 2; i <= input.length; i++) {
			for(int j = 0; j < input.length - i + 1; j++) {
				set.clear();
				for(int k = 0; k < i; k++) set.add(input[j+k]);
				if(set.stream().collect(Collectors.summingLong(Long::longValue)) == target) {
					return set;
				}
			}
		}
		return new ArrayList<Long>();
	}
	
	private static boolean validData(long n, List<Long> prev) {
		for(long num: prev) {
			if(prev.contains(n-num)) return true;
		}
		return false;
	}
	
}
