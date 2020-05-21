package com.example.carturkeyy.Models;

public class LoginPojo{
	private boolean tf;
	private String mail;
	private String id;
	private String name;
	private String surname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setMail(String mail){
		this.mail = mail;
	}

	public String getMail(){
		return mail;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LoginPojo(boolean tf, String mail, String id, String name, String surname) {
		this.tf = tf;
		this.mail = mail;
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
}
