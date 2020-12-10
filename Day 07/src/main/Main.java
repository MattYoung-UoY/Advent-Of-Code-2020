package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import main.lex.Lexer;
import main.parse.Parser;
import main.parse.sym;

public class Main {

	private static Parser parser = null;
	
	public static void main(String[] args) {
		parse("src/main/input.txt");
		List<Bag> bags = parser.bags;
		System.out.println(bags);
		int numCanContainGold = 0;
		for(Bag bag: bags) if((!bag.getName().equals("shiny gold")) && canContainGold(bag, bags)) numCanContainGold++;
		System.out.println(numCanContainGold);
		System.out.println(numBagsInside(getBagFromStr("shiny gold", bags), bags));
	}

	private static int numBagsInside(Bag bag, List<Bag> bags) {
		int numBags = 0;
		for(int n: bag.getSubBags().values()) numBags += n;
		for(String bagName: bag.getSubBags().keySet()) numBags += bag.getSubBags().get(bagName) * numBagsInside(getBagFromStr(bagName, bags), bags);
		return numBags;
	}
	
	private static boolean canContainGold(Bag bag, List<Bag> bags) {
		if(bag.getName().contentEquals("shiny gold")) return true;
		boolean canContain = false;
		for(String subBagName: bag.getSubBags().keySet()) canContain = canContain || canContainGold(getBagFromStr(subBagName, bags), bags);
		return canContain;
	}
	
	private static Bag getBagFromStr(String bagName, List<Bag> bags) {
		for(Bag bag: bags) if(bag.getName().equals(bagName)) return bag;
		return null;
	}
	
	private static void parse(String filename) {
		try (BufferedReader inp = new BufferedReader(new FileReader(filename))) {

			parser = new Parser(new Lexer(inp));
			try {
				parser.parse();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void lex(String filename) {
		try (BufferedReader inp = new BufferedReader(new FileReader(filename))) {
			Scanner lexer = new Lexer(inp);
			Symbol tok;

			try {
				do {
					tok = lexer.next_token();
					System.out.print(sym.terminalNames[tok.sym] + ":");
					if (tok.value != null)
						System.out.print(" " + tok.value);
					System.out.println();
				} while (tok.sym != sym.EOF);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
