package com.example.admin.metromonial.NetworkConnection;

//import com.netwrko.Model.BannerResponse;
//import com.netwrko.Model.LoginChatResponse;
//import com.netwrko.Model.LoginResponse;
//import com.netwrko.Model.PostResponse;

import com.example.admin.metromonial.Model.Loginmodel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by abul on 4/10/17.
 */

public interface FileUploadInterface {



    @FormUrlEncoded
    @POST("login_authentication.php")
    Call<ResponseBody> getLogin(@Field("username") String name, @Field("password") String password);



    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> getRegister(@Field("name") String name, @Field("email") String email,@Field("mobile") String mobile,@Field("password")String password );



    @FormUrlEncoded
    @POST("search-list.php")
    Call<ResponseBody> getSearchList(@Field("looking_for") String looking_for, @Field("sage") String sage,@Field("eage") String eage,@Field("country")String country ,
                                     @Field("state") String state, @Field("religion") String religion);


    @FormUrlEncoded
    @POST("get-state.php")
    Call<ResponseBody> getStateList(@Field("country_id") String country_id);


    @FormUrlEncoded
    @POST("get-religion.php")
    Call<ResponseBody> getReligionList(@Field("country_id") String country_id);






}
