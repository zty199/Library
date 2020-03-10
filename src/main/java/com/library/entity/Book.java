package com.library.entity;

import java.sql.Timestamp;

public class Book {

	private int id;
	private String ISBN;
	private String reference;
	private String name;
	private String writer;
	private Timestamp date;
	private int id_region;
	private int id_publisher;
	private int id_category;
	private int id_class;

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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
	public int getId_region() {
		return id_region;
	}
	public void setId_region(int id_region) {
		this.id_region = id_region;
	}
	public int getId_publisher() {
		return id_publisher;
	}
	public void setId_publisher(int id_publisher) {
		this.id_publisher = id_publisher;
	}
	public int getId_category() {
		return id_category;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public int getId_class() {
		return id_class;
	}
	public void setId_class(int id_class) {
		this.id_class = id_class;
	}

}
