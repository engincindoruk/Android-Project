package com.example.carturkeyy.Models;

public class YearPojo{
	private String year;

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	@Override
 	public String toString(){
		return 
			"YearPojo{" + 
			"year = '" + year + '\'' + 
			"}";
		}
}
