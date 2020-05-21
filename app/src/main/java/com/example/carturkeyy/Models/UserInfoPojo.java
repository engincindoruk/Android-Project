package com.example.carturkeyy.Models;

public class UserInfoPojo{
	private String phone;
	private String surname;
	private String name;
	private String email;
	private String tc;

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setTc(String tc){
		this.tc = tc;
	}

	public String getTc(){
		return tc;
	}

	@Override
 	public String toString(){
		return 
			"UserInfoPojo{" + 
			"phone = '" + phone + '\'' + 
			",surname = '" + surname + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			",tc = '" + tc + '\'' + 
			"}";
		}
}
