package com.library.interpreter;

//定义终结符表达式
public class TerminalExpression implements Expression {
	
	private String context;
	
	//获取参数信息
	public TerminalExpression(String context) {
		this.context = context;
	}

	//覆写方法解释器返回拆分部分结果
	@Override
	public String interpret() {
		// TODO Auto-generated method stub
		return context;
	}

}
