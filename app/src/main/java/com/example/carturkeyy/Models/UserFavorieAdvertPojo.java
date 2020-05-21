package com.example.carturkeyy.Models;

public class UserFavorieAdvertPojo{
	private String result;
	private String resim;
	private String il;
	private String ilce;
	private String uyeid;
	private String ilanid;
	private String fiyat;
	private String currency;
	private String baslik;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setIl(String il){
		this.il = il;
	}

	public String getIl(){
		return il;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
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

	public void setFiyat(String fiyat){
		this.fiyat = fiyat;
	}

	public String getFiyat(){
		return fiyat;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "UserFavorieAdvertPojo{" +
				"result='" + result + '\'' +
				", resim='" + resim + '\'' +
				", il='" + il + '\'' +
				", ilce='" + ilce + '\'' +
				", uyeid='" + uyeid + '\'' +
				", ilanid='" + ilanid + '\'' +
				", fiyat='" + fiyat + '\'' +
				", currency='" + currency + '\'' +
				", baslik='" + baslik + '\'' +
				'}';
	}
}
