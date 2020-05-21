package com.example.carturkeyy.Models;

public class HomeSliderPojo{
	private String resimyolu;
	private boolean tf;
	private String uyeid;
	private String ilanid;

	public void setResimyolu(String resimyolu){
		this.resimyolu = resimyolu;
	}

	public String getResimyolu(){
		return resimyolu;
	}

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

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	@Override
 	public String toString(){
		return 
			"HomeSliderPojo{" + 
			"resimyolu = '" + resimyolu + '\'' + 
			",tf = '" + tf + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			"}";
		}
}
