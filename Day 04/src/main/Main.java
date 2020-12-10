package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			String inp = "";
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.length() == 0) {
					inp += "\n";
					continue;
				}
				inp += " " + line;
			}
			input = inp.split("\n");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		int valid = 0;
		int validPt2 = 0;

		List<String> fields = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

		for (String passport : input) {
			int fieldsCorrect = 0;
			int fieldsCorrectPt2 = 0;
			String[] parts = passport.split(" ");
			for (String part : parts) {
				String[] partsAgain = part.split(":");
				
				if (fields.contains(partsAgain[0]))
					fieldsCorrect++;
				
				switch (partsAgain[0]) {
				case "byr":
					try {
						int year = Integer.parseInt(partsAgain[1]);
						if(partsAgain[1].length() != 4 || year < 1920 || year > 2002) break;
						fieldsCorrectPt2++;
					}catch (Exception e) {
						break;
					}
					break;
				case "iyr":
					try {
						int year = Integer.parseInt(partsAgain[1]);
						if(partsAgain[1].length() != 4 || year < 2010 || year > 2020) break;
						fieldsCorrectPt2++;
					}catch (Exception e) {
						break;
					}
					break;
				case "eyr":
					try {
						int year = Integer.parseInt(partsAgain[1]);
						if(partsAgain[1].length() != 4 || year < 2020 || year > 2030) break;
						fieldsCorrectPt2++;
					}catch (Exception e) {
						break;
					}
					break;
				case "hgt":
					try {
						if(partsAgain[1].endsWith("cm")) {
							int hgt = Integer.parseInt(partsAgain[1].substring(0, partsAgain[1].length() - 2));
							if(hgt >= 150 && hgt <= 193) {
								fieldsCorrectPt2++;
							}
						}else if(partsAgain[1].endsWith("in")) {
							int hgt = Integer.parseInt(partsAgain[1].substring(0, partsAgain[1].length() - 2));
							if(hgt >= 59 && hgt <= 76) {
								fieldsCorrectPt2++;
							}
						}
					}catch(Exception e) {
						break;
					}
					break;
				case "hcl":
					if(partsAgain[1].startsWith("#")) {
						try {
						Integer.parseInt(partsAgain[1].substring(1), 16);
						}catch(Exception e) {
							break;
						}
						fieldsCorrectPt2++;
					}
					break;
				case "ecl":
					if(partsAgain[1].matches("amb|blu|brn|gry|grn|hzl|oth")) {
						fieldsCorrectPt2++;
					}
					break;
				case "pid":
					if(partsAgain[1].length() == 9 && partsAgain[1].matches("[0-9]{9}")) {
						fieldsCorrectPt2++;
					}
					break;
				}
				
			}
			if (fieldsCorrect == fields.size()) {
				valid++;
			}
			
			if (fieldsCorrectPt2 == fields.size()) {
				validPt2++;
			}
		}

		System.out.println(valid);
		System.out.println(validPt2);
	}

}
