package com.example.carturkeyy.Models;

public class GetYorumPojo{
	private boolean tf;
	private String uyeid;
	private String id;
	private String surname;
	private String name;
	private String text;
	private String baslik;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}

	public String getSurname(){
		return surname;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetYorumPojo{" +
				"tf=" + tf +
				", uyeid='" + uyeid + '\'' +
				", id='" + id + '\'' +
				", surname='" + surname + '\'' +
				", name='" + name + '\'' +
				", text='" + text + '\'' +
				", baslik='" + baslik + '\'' +
				'}';
	}
}
