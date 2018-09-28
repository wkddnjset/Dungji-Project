package com.project.dungji.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import com.project.dungji.model.ResponseHeaderModel;

/**
 * Retrofit 을 활용하여 요청할 api 목록 인터페이스
 *
 * @GET 인 경우는 () 안에 get url 이 들어가고
 * 만약 경로상에 유동적인 값이 들어가야 한다면 쓰는 게 @Path(경로{} 값)
 * 만약 url에 데이터를 &키=값 형태로 넣어야 한다면 @Query(키값) 를 사용
 *
 *
 */
public interface RetrofitApiService {


    @GET("api/page/search")
    Call<ResponseHeaderModel> callGetSearchFilter();

    @Headers("Content-Type: application/json")
    @POST("api/page/search/result")
    Call<ResponseHeaderModel> callPostSearchResult(@Body JsonObject body);


    @Headers("Content-Type: application/json")
    @POST("api/page/main")
    Call<ResponseHeaderModel> callPostMainResult(@Body JsonObject body);


    @Headers("Content-Type: application/json")
    @POST("api/page/shelter/detail")
    Call<ResponseHeaderModel> callPostShelterDetail(@Body JsonObject body);
}
