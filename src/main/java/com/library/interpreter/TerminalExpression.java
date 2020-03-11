package com.library.interpreter;

public class TerminalExpression implements Expression {
	
	private String context;
	
	public TerminalExpression(String context) {
		this.context = context;
	}

	@Override
	public String interpret() {
		// TODO Auto-generated method stub
		return context;
	}

}
