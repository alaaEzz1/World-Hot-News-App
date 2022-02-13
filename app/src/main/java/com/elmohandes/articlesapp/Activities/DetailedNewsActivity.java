package com.elmohandes.articlesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.elmohandes.articlesapp.Adapters.DetailedNewsAdapter;
import com.elmohandes.articlesapp.Models.DetailedNews;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.retrofitPackage.APIClient;
import com.elmohandes.articlesapp.retrofitPackage.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedNewsActivity extends AppCompatActivity {

    private RecyclerView detailed_rv;
    private DetailedNewsAdapter adapter;
    private List<DetailedNews.Article> articleList;
    private APIInterface apiInterface;
    private String id , name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        detailed_rv = findViewById(R.id.detailed_recycler);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");

        articleList = new ArrayList<>();
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
        adapter = new DetailedNewsAdapter(this,articleList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        detailed_rv.setLayoutManager(layoutManager);
        detailed_rv.setAdapter(adapter);

        if (!id.isEmpty()){
            loadDataFromApi();
            getSupportActionBar().setTitle(name);
        }


    }

    private void loadDataFromApi() {
        apiInterface.getDetailedNews(id).enqueue(new Callback<DetailedNews>() {
            @Override
            public void onResponse(Call<DetailedNews> call, Response<DetailedNews> response) {
                DetailedNews news = response.body();
                articleList.clear();
                if (news != null){
                    articleList.addAll(news.getArticles());
                    adapter.notifyDataSetChanged();
                    Log.d("titles",news.getArticles().get(0).getTitle());
                }else {
                    Toast.makeText(getApplicationContext(),
                            "body is null " + id, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailedNews> call, Throwable t) {

            }
        });
    }
}