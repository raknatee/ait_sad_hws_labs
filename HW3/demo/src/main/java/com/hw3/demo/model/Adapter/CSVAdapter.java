package com.hw3.demo.model.Adapter;

public class CSVAdapter implements TextFormattable{

	CSVFormatter csvFormatter;
	public CSVAdapter(CSVFormatter csvFormatter) {
		this.csvFormatter = csvFormatter;
	}
	@Override
	public String formatText(String text) {
		// TODO Auto-generated method stub
		return this.csvFormatter.formatCSVText(text);
	}
	

}
