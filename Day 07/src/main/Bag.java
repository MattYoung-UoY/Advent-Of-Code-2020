package main;

import java.util.Map;

public class Bag {

	private String name;
	private Map<String, Integer> subBags;
	
	public Bag(String name, Map<String, Integer> subBags) {
		this.name = name;
		this.subBags = subBags;
	}

	public String toString() {
		return "{" + name + ", " + subBags + "}";
	}

	public String getName() {
		return name;
	}

	public Map<String, Integer> getSubBags() {
		return subBags;
	}
	
}
