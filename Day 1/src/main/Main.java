package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		double start = System.currentTimeMillis();

		BufferedReader reader;

		List<String> lines = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(new File("src/main/input.txt")));

			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] input = new int[lines.size()];
		for (String line : lines) {
			input[lines.indexOf(line)] = Integer.parseInt(line);
		}

		int res1 = 0, res2 = 0;

		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] + input[j] == 2020) {
					res1 = input[i] * input[j];
					break;
				}
			}
		}

		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				for (int k = j + 1; k < input.length; k++) {
					if (input[i] + input[j] + input[k] == 2020) {
						res2 = input[i] * input[j] * input[k];
						break;
					}
				}
			}
		}

		System.out.println(System.currentTimeMillis() - start);

		System.out.println(res1);
		System.out.println(res2);
	}

}
