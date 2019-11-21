package com.example.retrofitapp.Api;

import com.example.retrofitapp.model.DefaultResponse;
import com.example.retrofitapp.model.LoginResponse;
import com.example.retrofitapp.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
   Call<DefaultResponse> createUser(

            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(

        @Field("email") String email,
        @Field("password") String password

    );

    @GET("allusers")
    Call<UserResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse>  updateUser(
                        @Path("id")int id,
                        @Field("email") String email,
                        @Field("name") String name,
                        @Field("school") String school

                         );

        @FormUrlEncoded
        @PUT("updatepassword")
        Call<DefaultResponse> updatePassword(
                        @Field("currentpassword") String currentpassword,
                        @Field("newpassword") String newpassword,
                        @Field("email") String email
        );

        @DELETE("deleteuser/{id}")
        Call<DefaultResponse> deleteUser(@Path("id") int id);

}
