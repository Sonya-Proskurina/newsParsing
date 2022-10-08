package com.vtb.news.api;

import com.vtb.news.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vtb.news.service.NewsService;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping(value = "/news")
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }
}
