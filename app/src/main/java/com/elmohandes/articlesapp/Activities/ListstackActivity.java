package com.elmohandes.articlesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.elmohandes.articlesapp.Adapters.SwipeStackAdapter;
import com.elmohandes.articlesapp.Models.Articles;
import com.elmohandes.articlesapp.Models.News;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.databinding.ActivityListstackBinding;
import com.elmohandes.articlesapp.retrofitPackage.APIClient;
import com.elmohandes.articlesapp.retrofitPackage.APIInterface;
import com.elmohandes.articlesapp.util.Constans;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListstackActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener {

    ActivityListstackBinding binding;
    APIInterface apiInterface;
    List<Articles> articlesList;
    SwipeStackAdapter adapter;

    String id , name ,webHotURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liststack);

        binding = ActivityListstackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        apiInterface = APIClient.getApiClient().create(APIInterface.class);
        articlesList = new ArrayList<>();

        adapter = new SwipeStackAdapter(articlesList,this);
        binding.swipestack.setAdapter(adapter);
        binding.swipestack.setListener(this);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        if (!id.isEmpty()){
            loadNews(id);
            getSupportActionBar().setTitle(name);
        }

    }

    private void loadNews(String id) {

        apiInterface.getNewsestArticles(APIClient.getApiUrl(id, Constans.API_KEY))
                .enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {

                        News news = response.body();

                        if (news != null) {

                            articlesList.clear();
                            webHotURL=news.getArticles().get(0).getUrl();
                            articlesList.addAll(news.getArticles());

                            adapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "body is null " + id, Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {

                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {

        binding.swipestack.setVisibility(View.GONE);
        binding.containerNoArticles.setVisibility(View.VISIBLE);

    }
}