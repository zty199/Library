package com.library.interpreter;

public class Information {

	public Expression getRegion(String ISBN) {
		return new DivideExpression(ISBN, 1);
	}
	
	public Expression getPublisher(String ISBN) {
		return new DivideExpression(ISBN, 2);
	}

}
