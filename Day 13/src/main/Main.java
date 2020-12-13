package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int startTime = 0;
		List<String> busses = new ArrayList<String>();
		try(BufferedReader reader = new BufferedReader(new FileReader("src/main/input.txt"))){
			startTime = Integer.parseInt(reader.readLine());
			Arrays.stream(reader.readLine().split(",")).forEach(s -> busses.add(s));
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int earliest = 0;
		int currentTime = startTime;
		boolean running = true;
		while(running) {
			for(String bus: busses) if((!bus.equals("x")) && currentTime % Integer.parseInt(bus) == 0) {
				earliest = Integer.parseInt(bus);
				running = false;
				currentTime--;
				break;
			}
			currentTime++;
		}
		System.out.println(earliest * (currentTime-startTime));
		
		pt2(busses);
	}
	
	private static void pt2(List<String> busses) {
		long t = 0;
		long i = 1;
		while(true) {
			t = i * Integer.parseInt(busses.get(0));
			boolean result = true;
			for(int j = 1; j < busses.size(); j++) {
				if(busses.get(j).equals("x")) {
					continue;
				}else if((t+j) % Integer.parseInt(busses.get(j)) != 0) {
					result = false;
					break;
				}
			}
			if(result) {
				System.out.println("success: " + t);
				break;
			}
			i++;
		}
	}
	
}
