package com.lcz.gotest;

import io.reactivex.Observable;
import retrofit2.http.GET;

interface ClassApi {
    String url = "https://api.apiopen.top";

    @GET("/getJoke?page=1&count=2&type=video")
    Observable<TextBean> getMax();
}