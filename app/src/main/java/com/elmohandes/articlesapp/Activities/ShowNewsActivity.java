package com.elmohandes.articlesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.databinding.ActivityShowNewsBinding;
import com.squareup.picasso.Picasso;

public class ShowNewsActivity extends AppCompatActivity {

    ActivityShowNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        binding = ActivityShowNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        String content = getIntent().getStringExtra("content");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String imgUrl = getIntent().getStringExtra("img");
        String time = getIntent().getStringExtra("time");
        String author = getIntent().getStringExtra("author");
        String contentAll = getIntent().getStringExtra("contentAll");
        String titleAll = getIntent().getStringExtra("titleAll");
        String descriptionAll = getIntent().getStringExtra("descriptionAll");
        String imgUrlAll = getIntent().getStringExtra("imgAll");
        String timeAll = getIntent().getStringExtra("timeAll");
        String authorAll = getIntent().getStringExtra("authorAll");

        if (imgUrl != null){
            Picasso.get().load(imgUrl).placeholder(R.drawable.ic_image_search)
                    .into(binding.showImg);
        }
        if (content != null){
            binding.showContent.setText(content);
        }
        if (description != null){
            binding.showDescription.setText(description);
        }
        if (title != null){
            binding.showTitle.setText(title);
        }
        if (time != null){
            binding.showTime.setText(time);
        }
        if (author != null){
            binding.showAuthor.setText(author);
        }

        if (imgUrlAll != null){
            Picasso.get().load(imgUrlAll).placeholder(R.drawable.ic_image_search)
                    .into(binding.showImg);
        }
        if (contentAll != null){
            binding.showContent.setText(contentAll);
        }
        if (descriptionAll != null){
            binding.showDescription.setText(descriptionAll);
        }
        if (titleAll != null){
            binding.showTitle.setText(titleAll);
        }
        if (timeAll != null){
            binding.showTime.setText(timeAll);
        }
        if (authorAll != null){
            binding.showAuthor.setText(authorAll);
        }
    }
}