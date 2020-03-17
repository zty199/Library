package com.library.interpreter;

//定义具体实现方法调用解释器
public class Information {

	//获取ISBN中国家地区信息
	public Expression getRegion(String ISBN) {
		return new DivideExpression(ISBN, 1);
	}
	
	//获取ISBN中出版社信息
	public Expression getPublisher(String ISBN) {
		return new DivideExpression(ISBN, 2);
	}

}
