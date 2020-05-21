package com.example.carturkeyy.Models;

public class ModelPojo{
	private String model;

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	@Override
 	public String toString(){
		return 
			"ModelPojo{" + 
			"model = '" + model + '\'' + 
			"}";
		}
}
