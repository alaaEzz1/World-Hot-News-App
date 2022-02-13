package com.elmohandes.articlesapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

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
        String link = getIntent().getStringExtra("link");
        String contentAll = getIntent().getStringExtra("contentAll");
        String titleAll = getIntent().getStringExtra("titleAll");
        String descriptionAll = getIntent().getStringExtra("descriptionAll");
        String imgUrlAll = getIntent().getStringExtra("imgAll");
        String timeAll = getIntent().getStringExtra("timeAll");
        String authorAll = getIntent().getStringExtra("authorAll");
        String linkAll = getIntent().getStringExtra("linkAll");



        if (imgUrl != null){
            Picasso.get().load(imgUrl).placeholder(R.drawable.ic_image_search)
                    .into(binding.showImg);
        }
        if (content != null || link != null){
            String text = content+" Read more";
            //make read more different color
            SpannableString ss = new SpannableString(text);
            ForegroundColorSpan fs = new ForegroundColorSpan(Color.BLUE);
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    //open url in browser
                    Uri contentUri = Uri.parse(link);
                    startActivity(new Intent(Intent.ACTION_VIEW , contentUri));
                }
            };
            //you must make fs after cs because cs will make make color default
            //color clickable
            ss.setSpan(cs,text.length() - 10 , text.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(fs,text.length() - 10 , text.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.showContent.setText(ss);
            binding.showContent.setMovementMethod(LinkMovementMethod.getInstance());
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

        if (imgUrlAll != null || linkAll != null){
            Picasso.get().load(imgUrlAll).placeholder(R.drawable.ic_image_search)
                    .into(binding.showImg);
        }
        if (contentAll != null){
            String text = contentAll+" Read more";
            //make read more different color
            SpannableString ss = new SpannableString(text);
            //make read more clickable
            ForegroundColorSpan fs = new ForegroundColorSpan(Color.BLUE);
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    //open url in browser
                    Uri contentUri = Uri.parse(linkAll);
                    startActivity(new Intent(Intent.ACTION_VIEW , contentUri));
                }
            };
            ss.setSpan(cs,text.length() - 10 , text.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(fs,text.length() - 10 , text.length()
                    , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.showContent.setText(ss);
            binding.showContent.setMovementMethod(LinkMovementMethod.getInstance());
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