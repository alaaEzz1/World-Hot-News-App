package com.elmohandes.articlesapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elmohandes.articlesapp.Activities.ShowNewsActivity;
import com.elmohandes.articlesapp.Models.DetailedNews;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.databinding.DetailedNewsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailedNewsAdapter extends RecyclerView.Adapter<DetailedNewsAdapter
        .DetailedNewsVH> {

    private Context context;
    private List<DetailedNews.Article> articleList;

    public DetailedNewsAdapter(Context context, List<DetailedNews.Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public DetailedNewsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailedNewsVH(LayoutInflater.from(context).
                inflate(R.layout.detailed_news_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedNewsVH holder, int position) {

        DetailedNews.Article article = articleList.get(position);

        holder.binding.detailedTitle.setText(article.getTitle());
        holder.binding.detailedPublishedAt.setText(article.getPublishedAt());
        Picasso.get().load(article.getUrlToImage()).
                placeholder(R.drawable.ic_image_search)
                .into(holder.binding.detailedImage);
        holder.binding.detailedDesc.setText(article.getDescription());

        if (article.getAuthor() != null){
            holder.binding.detailedAuthor.setText("Author : "+article.getAuthor());
        }
        if (article.getUrl() != null){
            holder.binding.detailedLink.setText(article.getUrl());
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowNewsActivity.class);
            intent.putExtra("contentAll" , article.getContent());
            intent.putExtra("titleAll" , article.getTitle());
            intent.putExtra("descriptionAll" , article.getDescription());
            intent.putExtra("imgAll" , article.getUrlToImage());
            intent.putExtra("timeAll" , article.getPublishedAt());
            intent.putExtra("authorAll" , article.getAuthor());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class DetailedNewsVH extends RecyclerView.ViewHolder{

        DetailedNewsItemBinding binding;

        public DetailedNewsVH(@NonNull View itemView) {
            super(itemView);

            binding = DetailedNewsItemBinding.bind(itemView);
        }
    }

}
