package com.elmohandes.articlesapp.retrofitPackage;

import com.elmohandes.articlesapp.Models.News;
import com.elmohandes.articlesapp.Models.WebSite;
import com.elmohandes.articlesapp.util.Constans;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("v2/sources?language=en&apikey="+ Constans.API_KEY)
    Call<WebSite> getResources();


    @GET
    Call<News> getNewsestArticles(@Url String url);

}
