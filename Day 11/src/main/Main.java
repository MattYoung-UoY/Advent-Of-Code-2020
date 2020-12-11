package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	private static String[] current, next;
	
	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		current = input.clone();
		next = input.clone();
		
		iterate();
		while(!isStable(current, next)) iterate();
		
		int occSeats = 0;
		
		for(int i = 0; i < next.length; i++) {
			for(int j = 0; j < next[0].length(); j++) {
				if(next[i].charAt(j) == '#') occSeats++;
			}
		}
		
		System.out.println(occSeats);
		
		/////////////////////////////////////////////////

		current = input.clone();
		next = input.clone();
		
		iterate2();
		while(!isStable(current, next)) iterate2();
		
		occSeats = 0;
		
		for(int i = 0; i < next.length; i++) {
			for(int j = 0; j < next[0].length(); j++) {
				if(next[i].charAt(j) == '#') occSeats++;
			}
		}
		
		System.out.println(occSeats);
		
	}
	
	private static boolean isStable(String[] grid1, String[] grid2) {
		for(int i = 0; i < grid1.length; i++) {
			for(int j = 0; j < grid1[0].length(); j++) {
				if(grid1[i].charAt(j) != grid2[i].charAt(j)) return false;
			}
		}
		return true;
	}
	
	private static void iterate2() {
		current = next.clone();
		for(int i = 0; i < current.length; i++) {
			for(int j = 0; j < current[0].length(); j++) {
				if(current[i].charAt(j) == '.') continue;
				int occSeen = 0;
				
				
				//Left three
				if(firstSeatSeen(i, j, -1, -1)) occSeen++;
				if(firstSeatSeen(i, j, -1, 0)) occSeen++;
				if(firstSeatSeen(i, j, -1, 1)) occSeen++;
				
				//Top
				if(firstSeatSeen(i, j, 0, -1)) occSeen++;
				
				
				//Bottom
				if(firstSeatSeen(i, j, 0, 1)) occSeen++;
				
				
				//Right three
				if(firstSeatSeen(i, j, 1, -1)) occSeen++;
				if(firstSeatSeen(i, j, 1, 0)) occSeen++;
				if(firstSeatSeen(i, j, 1, 1)) occSeen++;
				
				if(next[i].charAt(j) == 'L' && occSeen == 0) {
					String tempNext = "";
					tempNext += next[i].substring(0, j);
					tempNext += "#";
					if(next[i].length() != j+1) tempNext += next[i].substring(j+1);
					next[i] = tempNext;
				}
				if(next[i].charAt(j) == '#' && occSeen > 4){
					String tempNext = "";
					tempNext += next[i].substring(0, j);
					tempNext += "L";
					if(next[i].length() != j+1) tempNext += next[i].substring(j+1);
					next[i] = tempNext;
				}
			}
		}
	}
	
	private static boolean firstSeatSeen(int x, int y, int dx, int dy) {
		int vx = x + dx;
		int vy = y + dy;
		while(vx > -1 && vx < current.length && vy > -1 && vy < current[0].length()) {
			
			if(current[vx].charAt(vy) == 'L') return false;
			else if(current[vx].charAt(vy) == '#') return true;
			
			vx += dx;
			vy += dy;
		}
		return false;
	}
	
	private static void iterate() {
		current = next.clone();
		for(int i = 0; i < current.length; i++) {
			for(int j = 0; j < current[0].length(); j++) {
				if(current[i].charAt(j) == '.') continue;
				int occAdj = 0;
				
				
				//Left three
				if(i > 0) {
					if(current[i-1].charAt(j) == '#') occAdj++;
					if(j > 0) if(current[i-1].charAt(j-1) == '#') occAdj++;
					if(j < current[0].length()-1) if(current[i-1].charAt(j+1) == '#') occAdj++;
					
				}
				
				
				//Top
				if(j > 0) if(current[i].charAt(j-1) == '#') occAdj++;
				
				
				//Bottom
				if(j < current[0].length()-1) {
					if(current[i].charAt(j+1) == '#') occAdj++;
				}
				
				
				//Right three
				if(i < current.length-1) {
					if(current[i+1].charAt(j) == '#') occAdj++;
					if(j > 0) if(current[i+1].charAt(j-1) == '#') occAdj++;
					if(j < current[0].length()-1) if(current[i+1].charAt(j+1) == '#') occAdj++;
				}
				
				
				
				if(next[i].charAt(j) == 'L' && occAdj == 0) {
					String tempNext = "";
					tempNext += next[i].substring(0, j);
					tempNext += "#";
					if(next[i].length() != j+1) tempNext += next[i].substring(j+1);
					next[i] = tempNext;
				}
				if(next[i].charAt(j) == '#' && occAdj > 3){
					String tempNext = "";
					tempNext += next[i].substring(0, j);
					tempNext += "L";
					if(next[i].length() != j+1) tempNext += next[i].substring(j+1);
					next[i] = tempNext;
				}
			}
		}
	}
	
	private static void printSeats() {
		for(String row: next) System.out.println(row);
	}
	
}
