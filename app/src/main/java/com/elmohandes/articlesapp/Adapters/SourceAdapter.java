package com.elmohandes.articlesapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elmohandes.articlesapp.Activities.DetailedNewsActivity;
import com.elmohandes.articlesapp.Activities.ListstackActivity;
import com.elmohandes.articlesapp.Models.Source;
import com.elmohandes.articlesapp.R;
import com.elmohandes.articlesapp.databinding.ItemSourceBinding;

import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SHolder> {

    private Context context;
    private List<Source> sourceList;

    public SourceAdapter(Context context, List<Source> sourceList) {
        this.context = context;
        this.sourceList = sourceList;
    }

    @NonNull
    @Override
    public SHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SHolder(
                LayoutInflater.from(context).inflate(R.layout.item_source,parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SHolder holder, int position) {

        holder.binding.sourceTxt.setText(sourceList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return sourceList.size();
    }

    public class SHolder extends RecyclerView.ViewHolder{

        ItemSourceBinding binding;

        public SHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSourceBinding.bind(itemView);
            itemView.setOnClickListener(view -> {
                Source sourceItem = sourceList.get(getAdapterPosition());

                sendUserToActivity(sourceItem);
            });
        }

        private void sendUserToActivity(Source sourceItem) {

            CharSequence options[] = new CharSequence[]{"Popular News" , "latest News"};

            AlertDialog.Builder builder = new AlertDialog.Builder(itemView
                    .getRootView().getContext());
            builder.setTitle("Choose Option");
            builder.setItems(options, (dialog, item) -> {
                if (item == 0){
                    Intent intent = new Intent(context, ListstackActivity.class);
                    intent.putExtra("id" ,sourceItem.getId());
                    intent.putExtra("name" , sourceItem.getName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
                if (item == 1){
                    Intent intent = new Intent(context, DetailedNewsActivity.class);
                    intent.putExtra("id" ,sourceItem.getId());
                    intent.putExtra("name" , sourceItem.getName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            builder.show();

        }
    }

}
