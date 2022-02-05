package com.elmohandes.articlesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elmohandes.articlesapp.Models.Articles;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.util.Constans;
import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SwipeStackAdapter extends BaseAdapter {

    private List<Articles> articlesList;
    private Context context;
    private String convertedDate;

    public SwipeStackAdapter(List<Articles> articlesList, Context context) {
        this.articlesList = articlesList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return articlesList.size();
    }

    @Override
    public Object getItem(int i) {
        return articlesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.card_item,viewGroup,false);
        }

        final ProgressBar progressBar = view.findViewById(R.id.card_progress);
        ImageView articleImage = view.findViewById(R.id.card_img);
        ImageView share = view.findViewById(R.id.card_share);
        TextView title = view.findViewById(R.id.card_title);
        TextView description = view.findViewById(R.id.card_description);
        TextView url = view.findViewById(R.id.card_link);
        TextView author = view.findViewById(R.id.card_websitename_txt);
        TextView timePublished = view.findViewById(R.id.card_time);

        //image resolution
        if (articlesList.get(position).getUrlToImage() != null
                && articlesList.get(position).getUrlToImage().length() >0){
            Picasso.get().load(articlesList.get(position).getUrlToImage())
                    .placeholder(R.drawable.ic_image_search)
                    .into(articleImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }

        if (articlesList.get(position).getTitle().length() > 65){
            title.setText(articlesList.get(position).getTitle().substring(0,65)+"...");
        }else {
            title.setText(articlesList.get(position).getTitle());
        }

        if (articlesList.get(position).getPublishedAt() != null){
            try {

                timePublished.setText(articlesList.get(position).getPublishedAt());

            } catch (Exception e) {
                Log.e("date" , e.getMessage());
            }
        }

        description.setText(articlesList.get(position).getDescription());
        url.setText(articlesList.get(position).getUrl());
        author.setText(articlesList.get(position).getAuthor());

        share.setOnClickListener(view1 -> {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT , "Read this article\n"
                    +articlesList.get(position).getTitle() + "\n" +
                    articlesList.get(position).getUrl());
            intent.setType("text/plain");
            context.startActivity(intent);

            Toast.makeText(context, "share this news with your friends",
                    Toast.LENGTH_SHORT).show();

        });

        return view;
    }

    private String setupDateTime(String publishedAt) {

        try {
            DateFormat dateFormat = new SimpleDateFormat
                    ("yyy-MM-dd 'T' HH:mm:ss" , Locale.getDefault());
            SimpleDateFormat format = new SimpleDateFormat("yyy/MM/dd hh:mm a");
            Date date = dateFormat.parse(publishedAt);
            convertedDate = format.format(date);
        } catch (ParseException e) {
            Log.e("date" , e.getMessage());
        }

        return convertedDate;
    }
}
