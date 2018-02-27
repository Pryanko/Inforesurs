package com.example.inforesource.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.inforesource.R;
import com.example.inforesource.data.News;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Libgo on 27.02.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> dataList = new ArrayList<>();


    public void updateList(List<News> list){
        dataList = list;
        notifyDataSetChanged();
    }
    
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News news = dataList.get(position);
        holder.imageNews.setImageURI(news.getUrlToImage());
        holder.titleText.setText(news.getTitle());
        holder.descriptionText.setText(news.getDescription());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_title_news)
        TextView titleText;
        @BindView(R.id.image_news)
        SimpleDraweeView imageNews;
        @BindView(R.id.text_description_news)
        TextView descriptionText;

         ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }
    }
}
