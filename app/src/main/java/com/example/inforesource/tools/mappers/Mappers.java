package com.example.inforesource.tools.mappers;

import com.example.inforesource.data.News;
import com.example.inforesource.data.answer.ArticlesItem;
import com.example.inforesource.data.answer.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Libgo on 27.02.2018.
 */

public class Mappers {

    public static List<News> mapNewsList(Response response) {

        List<News> list = new ArrayList<>();

        for (ArticlesItem articlesItem : response.getArticles()){
            News news = new News();
            news.setTitle(articlesItem.getTitle());
            news.setUrlToImage(articlesItem.getUrlToImage());
            news.setUrl(articlesItem.getUrl());
            news.setDescription(articlesItem.getDescription());
            news.setPublishedAt(articlesItem.getPublishedAt());
            list.add(news);
        }
        return list;
    }


}
