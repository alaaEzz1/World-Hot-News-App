package com.elmohandes.articlesapp.retrofitPackage;

import com.elmohandes.articlesapp.Models.DetailedNews;
import com.elmohandes.articlesapp.Models.News;
import com.elmohandes.articlesapp.Models.WebSite;
import com.elmohandes.articlesapp.util.Constans;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("v2/sources?language=en&apikey="+ Constans.API_KEY)
    Call<WebSite> getResources();

    @GET
    Call<News> getNewsestArticles(@Url String url);

    @GET("v2/everything?q=tesla&language=en&sortBy=publishedAt&apiKey="+Constans.API_KEY)
    Call<DetailedNews> getDetailedNews(@Query("sources") String sources);

}
