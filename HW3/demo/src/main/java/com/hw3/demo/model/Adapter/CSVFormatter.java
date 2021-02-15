package com.hw3.demo.model.Adapter;

public class CSVFormatter implements CSVFormattable{

	@Override
	public String formatCSVText(String text) {
		return text.replace(".", ",");
	}

}
