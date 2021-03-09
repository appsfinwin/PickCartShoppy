package com.finwin.pickcart.retrofit;

import com.finwin.pickcart.login.pojo.LoginResponse;
import com.finwin.pickcart.sign_up.pojo.GetBranchResponse;
import com.finwin.pickcart.sign_up.pojo.sign_up_response.SignUpResponse;

import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("LogInManagement_Cart_Api")
    Single<LoginResponse> login(@Body RequestBody body);


    @POST("CustomerManagement_Cart_Api")
    Single<SignUpResponse> signUp(@Body RequestBody body);

  @POST("Branch_registration")
    Single<GetBranchResponse> getBranches(@Body RequestBody body);



}