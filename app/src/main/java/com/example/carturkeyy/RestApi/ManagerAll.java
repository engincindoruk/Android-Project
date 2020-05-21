package com.example.carturkeyy.RestApi;

import com.example.carturkeyy.Models.AddAdvertCommentPojo;
import com.example.carturkeyy.Models.BireyselilanlarPojo;
import com.example.carturkeyy.Models.BrandPojo;
import com.example.carturkeyy.Models.DogrulamaPojo;
import com.example.carturkeyy.Models.FavoriePojo;
import com.example.carturkeyy.Models.FavorieislemPojo;
import com.example.carturkeyy.Models.ForgotPasswordPojo;
import com.example.carturkeyy.Models.GetBireyselilanCommentPojo;
import com.example.carturkeyy.Models.GetIlPojo;
import com.example.carturkeyy.Models.GetYorumPojo;
import com.example.carturkeyy.Models.HomeSliderPojo;
import com.example.carturkeyy.Models.IlanDetayPojo;
import com.example.carturkeyy.Models.IlanDetaySliderPojo;
import com.example.carturkeyy.Models.IlanVerGenel;
import com.example.carturkeyy.Models.IlanVerTeknik;
import com.example.carturkeyy.Models.IlanlarPojo;
import com.example.carturkeyy.Models.IlanlarimSilPojo;
import com.example.carturkeyy.Models.ImagePojo;
import com.example.carturkeyy.Models.LoginPojo;
import com.example.carturkeyy.Models.ModelPojo;
import com.example.carturkeyy.Models.RegisterPojo;
import com.example.carturkeyy.Models.SetForgotPasswordPojo;
import com.example.carturkeyy.Models.SilBireyselCommentPojo;
import com.example.carturkeyy.Models.UpdateUserInfoPojo;
import com.example.carturkeyy.Models.UserFavorieAdvertPojo;
import com.example.carturkeyy.Models.UserInfoPojo;
import com.example.carturkeyy.Models.YearPojo;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance=new ManagerAll();
    public static synchronized ManagerAll getInstance()
    {
        return ourInstance;
    }

    public Call<LoginPojo> Login(String email,String password)
    {
        Call<LoginPojo> x=getRestApi().login(email,password);
        return x;
    }
    public Call<RegisterPojo> Register(String name ,String surname,String phone,String tc,String mail,String password)
    {
        Call<RegisterPojo> y=getRestApi().register(name,surname,phone,tc,mail,password);
        return y;
    }

    public Call<DogrulamaPojo> Verify(String email, String verifycode)
    {
        Call<DogrulamaPojo> x=getRestApi().verify(email,verifycode);
        return x;
    }

    public Call<List<YearPojo>> GetYear()
    {
        Call<List<YearPojo>> x=getRestApi().getYear();
        return x;
    }

    public Call<List<BrandPojo>> GetBrand()
    {
        Call<List<BrandPojo>> x=getRestApi().getBrand();
        return x;
    }

    public Call<List<ModelPojo>> GetModel(String brand,String year)
    {
        Call<List<ModelPojo>> x=getRestApi().getModel(brand,year);
        return x;
    }

    public Call<IlanVerGenel> Ilanver(String uyeid ,String brand,String model,String plate,String productyear,String price,String currency,String ilandate ,String kimden,String ilanbaslik,String explanation,String color,String guarantee,String city ,String district,String fulladres)
    {
        Call<IlanVerGenel> x=getRestApi().ilanvergenel(uyeid, brand, model, plate, productyear, price,currency, ilandate, kimden, ilanbaslik, explanation, color, guarantee, city, district, fulladres);
        return x;
    }

    public Call<IlanVerTeknik> IlanverTeknik(String uyeid , String ilanid, String yakittipi, String motorhacmi, String motorgucu,String vites, String km, String kasatipi , String bagajkapasitesi, String colorstatus, String changing, String tramer, String ortalamayakit, String sehirdisi , String sehirici, String depo)
    {
        Call<IlanVerTeknik> x=getRestApi().ilanverteknik(uyeid, ilanid, yakittipi, motorhacmi, motorgucu,vites, km, kasatipi, bagajkapasitesi, colorstatus, changing, tramer, ortalamayakit, sehirdisi, sehirici, depo);
        return x;
    }

    public Call<ImagePojo> AddImage(String uyeid, String ilanid,String base64image)
    {
        Call<ImagePojo> x=getRestApi().addImage(uyeid,ilanid,base64image);
        return x;
    }

    public Call<List<BireyselilanlarPojo>> GetAdvert(String uyeid)
    {
        Call<List<BireyselilanlarPojo>> x=getRestApi().getAdvert(uyeid);
        return x;
    }

    public Call<IlanlarimSilPojo> DeleteAdvert(String ilanid)
    {
        Call<IlanlarimSilPojo> x=getRestApi().deleteAdvert(ilanid);
        return x;
    }

    public Call<List<IlanlarPojo>> GetAllAdvert()
    {
        Call<List<IlanlarPojo>> x=getRestApi().getAllAdvert();
        return x;
    }

    public Call<IlanDetayPojo> GetAdvertDetails(String ilanid)
    {
        Call<IlanDetayPojo> x=getRestApi().getAdvertDetails(ilanid);
        return x;
    }

    public Call<List<IlanDetaySliderPojo>> GetAdvertImage(String ilanid)
    {
        Call<List<IlanDetaySliderPojo>> x=getRestApi().getAdvertImage(ilanid);
        return x;
    }


    public Call<FavoriePojo> CheckFavorie(String uyeid,String ilanid)
    {
        Call<FavoriePojo> x=getRestApi().checkFavorie(uyeid, ilanid);
        return x;
    }

    public Call<FavorieislemPojo> FavorieIslem(String uyeid, String ilanid)
    {
        Call<FavorieislemPojo> x=getRestApi().Favorieislem(uyeid, ilanid);
        return x;
    }

    public Call<List<HomeSliderPojo>> GetFavorieImage(String uyeid)
    {
        Call<List<HomeSliderPojo>> x=getRestApi().getFavorieImage(uyeid);
        return x;
    }

    public Call<UserInfoPojo> GetUserInfo(String uyeid)
    {
        Call<UserInfoPojo> x=getRestApi().getUserInfo(uyeid);
        return x;
    }

    public Call<UpdateUserInfoPojo> UpdateUserInfo(String uyeid, String name,String surname, String phone,String tc, String email,String password)
    {
        Call<UpdateUserInfoPojo> x=getRestApi().updateUser(uyeid, name, surname, phone, tc, email, password);
        return x;
    }

    public Call<List<UserFavorieAdvertPojo>> GetUserFavorieAdvert(String uyeid)
    {
        Call<List<UserFavorieAdvertPojo>> x=getRestApi().getUserFavorieAdvert(uyeid);
        return x;
    }

    public Call<ForgotPasswordPojo> ForgotPassword(String mail)
    {
        Call<ForgotPasswordPojo> x=getRestApi().forgotpassword(mail);
        return x;
    }

    public Call<SetForgotPasswordPojo> SetForgotPassword(String mail,String sifre,String verificationcode)
    {
        Call<SetForgotPasswordPojo> x=getRestApi().setforgotpassword(mail, sifre, verificationcode);
        return x;
    }

    public Call<AddAdvertCommentPojo> AddComment(String uyeid, String ilanid, String baslik,String text)
    {
        Call<AddAdvertCommentPojo> x=getRestApi().addcomment(uyeid, ilanid, baslik, text);
        return x;
    }

    public Call<List<GetYorumPojo>> GetComment(String ilanid)
    {
        Call<List<GetYorumPojo>> x=getRestApi().getcommet(ilanid);
        return x;
    }

    public Call<SilBireyselCommentPojo> DeleteComment(String uyeid, String Commentid)
    {
        Call<SilBireyselCommentPojo> x=getRestApi().deletecomment(uyeid, Commentid);
        return x;
    }

    public Call<List<GetBireyselilanCommentPojo>> GetBireyselIlanComment(String uyeid)
    {
        Call<List<GetBireyselilanCommentPojo>> x=getRestApi().getbireyselilancomment(uyeid);
        return x;
    }

    public Call<List<GetIlPojo>> GetIl()
    {
        Call<List<GetIlPojo>> x=getRestApi().getil();
        return x;
    }

}
