package com.library.interpreter;

public class DivideExpression implements Expression {

	private String ISBN;
	private int no;
	
	public DivideExpression(String ISBN, int no) {
		this.ISBN = ISBN;
		this.no = no;
	}

	@Override
	public String interpret() {
		// TODO Auto-generated method stub
		String[] array = ISBN.split("-");
		String context = "";
		for(int i = 0; i <= no; i++) {
			context += array[i]; 
		}
		return new TerminalExpression(context).interpret();
	}

}
