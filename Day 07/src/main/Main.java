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
