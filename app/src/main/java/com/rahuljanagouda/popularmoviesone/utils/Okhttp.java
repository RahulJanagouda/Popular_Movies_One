package com.rahuljanagouda.popularmoviesone.utils;

import com.google.gson.Gson;
import com.rahuljanagouda.popularmoviesone.pojo.Page;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rahuljanagouda on 14/02/16.
 */
public class Okhttp {
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();


    public Page run() throws Exception {
        final Page[] page = new Page[1];
        Request request = new Request.Builder()
                .url(Network.URL_TMDB_DISCOVER_MOVIES_POPULAR_API)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                page[0] = gson.fromJson(response.body().charStream(), Page.class);
            }
        });

//        Response response = client.newCall(request).execute();

        return page[0];
    }

}
