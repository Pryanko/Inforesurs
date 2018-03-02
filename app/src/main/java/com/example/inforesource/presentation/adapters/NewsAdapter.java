package com.example.inforesource.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.inforesource.R;
import com.example.inforesource.data.News;
import com.example.inforesource.presentation.contracts.ContractNewsList;
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
    private ContractNewsList.ClickItemAdapter clickItemAdapter;

    public void addList(List<News> list, boolean postSearch){
            if (postSearch) {
                if (list != null) {
                    updateList(list);
                }
            } else {
                if (list != null) {
                    dataList.addAll(list);
                    notifyDataSetChanged();
                }
            }
        }


    public void updateList(List<News> list){
        if(list != null){
            dataList.clear();
            dataList.addAll(list);
            notifyDataSetChanged();

        }

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News news = dataList.get(position);
        if(news.getUrlToImage() != null){
        holder.imageNews.setImageURI(news.getUrlToImage()); }
        else {holder.imageNews.setImageResource(R.drawable.ic_launcher_foreground);}
        holder.titleText.setText(news.getTitle());
        holder.descriptionText.setText(news.getDescription());
        holder.dateText.setText(news.getPublishedAt().substring(0,10));
        holder.timeText.setText(news.getPublishedAt().substring(11,19));
        holder.buttonRead.setOnClickListener(v -> clickItemAdapter.readMore(news.getUrl()));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void initClickItem(ContractNewsList.ClickItemAdapter clickItemAdapter) {
        this.clickItemAdapter = clickItemAdapter;
    }

    private List<News> getDataList() {
        return dataList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_title_news)
        TextView titleText;
        @BindView(R.id.image_news)
        SimpleDraweeView imageNews;
        @BindView(R.id.text_description_news)
        TextView descriptionText;
        @BindView(R.id.text_date)
        TextView dateText;
        @BindView(R.id.text_time)
        TextView timeText;
        @BindView(R.id.button_read)
        Button buttonRead;

         ViewHolder(View itemView) {
            super(itemView);
             ButterKnife.bind(this, itemView);
        }
    }
}
