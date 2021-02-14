package com.hw3.demo.model.Adapter;

public class NewLineFormatter implements TextFormattable{

	@Override
	public String formatText(String text) {
		return text.replace(".", "\n");
	}

}
