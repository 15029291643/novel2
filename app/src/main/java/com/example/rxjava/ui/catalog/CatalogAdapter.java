package com.example.rxjava.ui.catalog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.R;
import com.example.rxjava.logic.model.Chapter;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {
    private List<Chapter> mCatalog;

    @SuppressLint("NotifyDataSetChanged")
    public void setCatalog(List<Chapter> catalog) {
        mCatalog = catalog;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatalogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_catalog, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.ViewHolder holder, int position) {
        holder.title.setText("第" + (position + 1) + "章 " +  mCatalog.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            Navigation.findNavController(v).navigate(R.id.action_catalogFragment_to_chapterFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return mCatalog != null ? mCatalog.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.catalog_title);
        }
    }
}
