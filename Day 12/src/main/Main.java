package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		String[] input = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/input.txt")))) {
			input = reader.lines().toArray(String[]::new);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println(pt1(input, 0, 0, 1));
		System.out.println(pt2(input, 10, 1, 0, 0));
	}

	private static int pt2(String[] input, int startWaypointX, int startWaypointY, int startShipX, int startShipY) {
		int wayX = startWaypointX;
		int wayY = startWaypointY;

		int shipX = startShipX;
		int shipY = startShipY;
		
		for(String instruction : input) {
			String inst = instruction.substring(0, 1);
			int val = Integer.parseInt(instruction.substring(1));
			switch(inst) {
			case "N":
				wayY += val;
				break;
			case "S":
				wayY -= val;
				break;
			case "E":
				wayX += val;
				break;
			case "W":
				wayX -= val;
				break;
			case "L":
				int[] inp = {wayX, wayY};
				int[] waypoint = rotateWaypoint(-val, inp);
				wayX = waypoint[0];
				wayY = waypoint[1];
				break;
			case "R":
				int[] inp2 = {wayX, wayY};
				int[] waypoint2 = rotateWaypoint(val, inp2);
				wayX = waypoint2[0];
				wayY = waypoint2[1];
				break;
			case "F":
				shipX += val*wayX;
				shipY += val*wayY;
				break;
			}
			System.out.println(instruction + ": " + shipX + ", " + shipY + ", " + wayX + ", " + wayY);
		}
		
		return Math.abs(shipX) + Math.abs(shipY); 
	}
	
	private static int[] rotateWaypoint(int degrees, int[] waypoint) {
		int[] ret = new int[2];
		if(Math.abs(degrees) % 180 == 0) {
			ret[0] = -waypoint[0];
			ret[1] = -waypoint[1];
		}
		int angle;
		int deg = 360 - degrees;
		if(deg < 0) angle = 4 + ((deg / 90) % 4);
		else angle = (deg / 90) % 4;
		switch(angle) {
		case 1:
			ret[0] = -waypoint[1];
			ret[1] = waypoint[0];
			break;
		case 3:
			ret[0] = waypoint[1];
			ret[1] = -waypoint[0];
			break;
		}
		return ret;
	}
	
	private static int pt1(String[] input, int startx, int starty, int startFacing) {
		int x = startx;
		int y = starty;
		int facing = startFacing;
		// north - 0
		// east - 1
		// south - 2
		// west - 3

		for (String instruction : input) {
			String inst = instruction.substring(0, 1);
			int val = Integer.parseInt(instruction.substring(1));
			switch (inst) {
			case "N":
				y += val;
				break;
			case "S":
				y -= val;
				break;
			case "E":
				x += val;
				break;
			case "W":
				x -= val;
				break;
			case "L":
				facing = rotateShip(facing, -val);
				break;
			case "R":
				facing = rotateShip(facing, val);
				break;
			case "F":
				switch (facing) {
				case 0:
					y += val;
					break;
				case 1:
					x += val;
					break;
				case 2:
					y -= val;
					break;
				case 3:
					x -= val;
					break;
				}
				break;
			}
		}
		return Math.abs(x) + Math.abs(y);
	}

	private static int rotateShip(int facing, int degrees) {
		int ret;
		if (degrees < 0) {
			int dir = 4 + (degrees / 90) % 4;
			ret = (facing + dir) % 4;
		} else {
			ret = (facing + (degrees / 90)) % 4;
		}
		return ret;
	}

}
