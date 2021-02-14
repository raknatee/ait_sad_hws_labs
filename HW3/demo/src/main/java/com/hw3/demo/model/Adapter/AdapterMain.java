package com.hw3.demo.model.Adapter;

public class AdapterMain {
	public static void runMe() {
		String testString = " Formatting line 1. Formatting line 2. Formatting line 3.";
		
		TextFormattable textFormatter = new NewLineFormatter();
		System.out.println(textFormatter.formatText(testString));
		
		TextFormattable textFormatterFromCSV = new CSVAdapter(new CSVFormatter());
		System.out.println(textFormatterFromCSV.formatText(testString));
	}

}
