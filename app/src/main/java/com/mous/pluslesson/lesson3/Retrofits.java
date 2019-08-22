package com.mous.pluslesson.lesson3;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofits {


    public Retrofits() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("").build();
        GitService apis = retrofit.create(GitService.class);
        apis.getUser().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
