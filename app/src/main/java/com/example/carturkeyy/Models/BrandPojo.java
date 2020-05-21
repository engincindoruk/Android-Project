package com.example.carturkeyy.Models;

public class BrandPojo{
	private String brand;

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	@Override
 	public String toString(){
		return 
			"BrandPojo{" + 
			"brand = '" + brand + '\'' + 
			"}";
		}
}
