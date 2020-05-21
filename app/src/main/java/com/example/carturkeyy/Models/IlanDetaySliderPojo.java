package com.example.carturkeyy.Models;

public class IlanDetaySliderPojo{
	private String resim;

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	@Override
 	public String toString(){
		return 
			"IlanDetaySliderPojo{" + 
			"resim = '" + resim + '\'' + 
			"}";
		}
}
