package com.library.interpreter;

//定义非终结符表达式“-”（拆分）
public class DivideExpression implements Expression {

	private String ISBN;
	private int no;
	
	//获取参数
	public DivideExpression(String ISBN, int no) {
		this.ISBN = ISBN;
		this.no = no;
	}

	//覆写方法解释器进行拆分操作
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
