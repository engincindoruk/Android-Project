package com.example.carturkeyy.Models;

public class GetBireyselilanCommentPojo{
	private boolean tf;
	private String surname;
	private String ilanid;
	private String name;
	private String text;
	private String baslik;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setSurname(String surname){
		this.surname = surname;
	}

	public String getSurname(){
		return surname;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
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

	@Override
 	public String toString(){
		return 
			"GetBireyselilanCommentPojo{" + 
			"tf = '" + tf + '\'' + 
			",surname = '" + surname + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",name = '" + name + '\'' + 
			",text = '" + text + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
