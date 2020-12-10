package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	private static Map<Integer, Long> dynProg = new HashMap<Integer, Long>();
	private static List<Integer> adapters;

	public static void main(String[] args) {
		int[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			input = reader.lines().mapToInt(s -> Integer.parseInt(s)).toArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		adapters = new ArrayList<Integer>();
		for (int i : input)
			adapters.add(i);
		adapters.add(0);
		Collections.sort(adapters);
		adapters.add(adapters.get(adapters.size() - 1) + 3);

		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
		dist.put(1, 0);
		dist.put(2, 0);
		dist.put(3, 0);

		for (int i = 0; i < adapters.size() - 1; i++) {
			int diff = adapters.get(i + 1) - adapters.get(i);
			dist.put(diff, dist.get(diff) + 1);
		}

		System.out.println(dist);
		System.out.println(dist.get(1) * dist.get(3));

		dynProg = arrangements(0, new HashMap<Integer, Long>());
		System.out.println(dynProg.get(0));
	}

	private static Map<Integer, Long> arrangements(int num, Map<Integer, Long> dynMap) {
		if (num == adapters.get(adapters.size() - 1)) {
			dynMap.put(num, (long) 1);
			return dynMap;
		}
		int i = adapters.indexOf(num);
		List<Integer> next = new ArrayList<Integer>();
		try {
			for (int j = 1; j < 4; j++)
				if (adapters.get(i + j) - num < 4) {
					if (!dynMap.containsKey(adapters.get(i + j))) {
						arrangements(adapters.get(i + j), dynMap);
					}
					next.add(adapters.get(i + j));
				}

		} catch (Exception e) {
		}
		Long comb = (long) 0;
		for (int n : next)
			comb += dynMap.get(n);
		dynMap.put(num, comb);
		return dynMap;
	}
}
