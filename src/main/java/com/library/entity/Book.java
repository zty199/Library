package com.library.entity;

import java.sql.Timestamp;

public class Book {

	private int id;
	private String ISBN;
	private String index;
	private String name;
	private String writer;
	private Timestamp date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}

}
