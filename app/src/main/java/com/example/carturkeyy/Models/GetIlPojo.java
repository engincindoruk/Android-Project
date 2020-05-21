package com.example.carturkeyy.Models;

public class GetIlPojo{
	private String ilid;
	private String isim;

	public void setIlid(String ilid){
		this.ilid = ilid;
	}

	public String getIlid(){
		return ilid;
	}

	public void setIsim(String isim){
		this.isim = isim;
	}

	public String getIsim(){
		return isim;
	}

	@Override
 	public String toString(){
		return 
			"GetIlPojo{" + 
			"ilid = '" + ilid + '\'' + 
			",isim = '" + isim + '\'' + 
			"}";
		}
}
