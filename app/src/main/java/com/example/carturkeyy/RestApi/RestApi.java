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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApi {

    @FormUrlEncoded
    @POST("/login.php")
    Call<LoginPojo> login(@Field("mail") String mail, @Field("sifre")String password);

    @FormUrlEncoded
    @POST("/register.php")
    Call<RegisterPojo> register(@Field("name") String name, @Field("surname") String surname, @Field("phone") String phone, @Field("tc")String tc, @Field("mail")String email, @Field("password")String password);

    @FormUrlEncoded
    @POST("/dogrulama.php")
    Call<DogrulamaPojo> verify(@Field("mail") String mail, @Field("verificationcode") String verificationcode);

    @GET("/getyear.php")
    Call<List<YearPojo>> getYear();

    @GET("/getbrand.php")
    Call<List<BrandPojo>> getBrand();

    @FormUrlEncoded
    @POST("/getmodel.php")
    Call<List<ModelPojo>> getModel(@Field("brand") String brand, @Field("year") String year);

    @FormUrlEncoded
    @POST("/ilanver.php")
    Call<IlanVerGenel> ilanvergenel(@Field("uyeid") String uyeid, @Field("brand") String brand,@Field("model") String model,@Field("plate") String plate,@Field("productyear") String productyear,@Field("price") String price,@Field("currency") String currency,@Field("ilandate") String ilandate,@Field("kimden") String kimden,@Field("ilanbaslik") String ilanbaslik,@Field("explanation") String explanation,@Field("color") String color,@Field("guarantee") String guarantee,@Field("city") String city,@Field("district") String district,@Field("fulladres") String fulladres);


    @FormUrlEncoded
    @POST("/ilanverteknik.php")
    Call<IlanVerTeknik> ilanverteknik(@Field("uyeid") String uyeid, @Field("ilanid") String ilanid, @Field("yakittipi") String yakittipi, @Field("motorhacmi") String motorhacmi, @Field("motorgucu") String motorgucu,@Field("vites") String vites, @Field("km") String km, @Field("kasatipi") String kasatipi, @Field("bagajkapasitesi") String bagajkapasitesi, @Field("colorstatus") String colorstatus, @Field("changing") String changing, @Field("tramer") String tramer, @Field("ortalamayakit") String ortalamayakit, @Field("sehirdisi") String sehirdisi, @Field("sehirici") String sehirici, @Field("depo") String depo);

    @FormUrlEncoded
    @POST("/ilanresimekle.php")
    Call<ImagePojo> addImage(@Field("uyeid") String uyeid, @Field("ilanid") String ilanid, @Field("resim") String base64resim);

    @GET("/ilanlarim.php")
    Call<List<BireyselilanlarPojo>> getAdvert(@Query("uyeid")String uyeid);

    @GET("/ilanlarimsil.php")
    Call<IlanlarimSilPojo> deleteAdvert(@Query("ilanid")String ilanid);

    @GET("/ilanlar.php")
    Call<List<IlanlarPojo>> getAllAdvert();

    @GET("/ilandetay.php")
    Call<IlanDetayPojo> getAdvertDetails(@Query("ilanid")String ilanid);

    @GET("/ilanlarresim.php")
    Call<List<IlanDetaySliderPojo>> getAdvertImage(@Query("ilanid")String ilanid);

    @GET("/favoriilanlar.php")
    Call<FavoriePojo> checkFavorie(@Query("uyeid")String uyeid,@Query("ilanid")String ilanid);

    @GET("/favoriislem.php")
    Call<FavorieislemPojo> Favorieislem(@Query("uyeid")String uyeid, @Query("ilanid")String ilanid);

    @GET("/favoriilanslider.php")
    Call<List<HomeSliderPojo>> getFavorieImage(@Query("uyeid")String uyeid);

    @GET("/uyelergetallinfo.php")
    Call<UserInfoPojo> getUserInfo(@Query("uyeid")String uyeid);

    @FormUrlEncoded
    @POST("/uyeınfoedit.php")
    Call<UpdateUserInfoPojo> updateUser(@Field("uyeid") String uyeid, @Field("name") String name, @Field("surname") String surname,@Field("phone") String phone,@Field("tc") String tc,@Field("email") String email,@Field("password") String password );

    @GET("/getuyefavori.php")
    Call<List<UserFavorieAdvertPojo>> getUserFavorieAdvert(@Query("uyeid")String uyeid);

    @GET("/forgotpassword.php")
    Call<ForgotPasswordPojo> forgotpassword(@Query("mail")String mail);

    @FormUrlEncoded
    @POST("/setforgotpassword.php")
    Call<SetForgotPasswordPojo> setforgotpassword(@Field("mail") String mail, @Field("sifre") String sifre, @Field("verificationcode") String verificationcode);

    @FormUrlEncoded
    @POST("/yorumekle.php")
    Call<AddAdvertCommentPojo> addcomment(@Field("uyeid") String uyeid, @Field("ilanid") String ilanid, @Field("baslik") String baslik,@Field("text") String text);

    @FormUrlEncoded
    @POST("/getyorum.php")
    Call<List<GetYorumPojo>> getcommet(@Field("ilanid") String ilanid);

    @FormUrlEncoded
    @POST("/deleteyorum.php")
    Call<SilBireyselCommentPojo> deletecomment(@Field("uyeid") String uyeid,@Field("id") String id);

    @FormUrlEncoded
    @POST("/getbireyselilancomment.php")
    Call<List<GetBireyselilanCommentPojo>> getbireyselilancomment(@Field("uyeid") String uyeid);

    @GET("/getil.php")
    Call<List<GetIlPojo>> getil();

}
