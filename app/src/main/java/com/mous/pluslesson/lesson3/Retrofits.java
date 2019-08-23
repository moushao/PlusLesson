package com.mous.pluslesson.lesson3;

import java.io.IOException;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Retrofits {


    public static void main(String[] args) {

        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("hostname", "sha256/rZ/aGVuY29kZXI=")
                .build();


        OkHttpClient client = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build();
        Request request = new Request.Builder()
                .url("https://hencoder.com")
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                System.out.println("failed");
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                System.out.println("success");
            }
        });
        //        client.newCall(new Request.Builder()
        ////                .url("https://api.github.com/users/moushao")
        //                .url("https://hencoder.com")
        //                .build())
        //                .enqueue(new okhttp3.Callback() {
        //                    @Override
        //                    public void onFailure(okhttp3.Call call, IOException e) {
        //                        System.out.println("failed");
        //                    }
        //
        //                    @Override
        //                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
        //                        System.out.println("success");
        //                    }
        //                });
    }
}
