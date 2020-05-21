package com.example.carturkeyy.Models;

public class IlanVerGenel{
	private boolean tf;
	private Object uyeid;
	private String ilanid;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setUyeid(Object uyeid){
		this.uyeid = uyeid;
	}

	public Object getUyeid(){
		return uyeid;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	@Override
 	public String toString(){
		return 
			"IlanVerGenel{" + 
			"tf = '" + tf + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			"}";
		}
}
