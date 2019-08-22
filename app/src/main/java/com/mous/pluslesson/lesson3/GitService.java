package com.mous.pluslesson.lesson3;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GitService {

    @GET("user/xxxx/res")
    Call<ResponseBody> getUser();
}
