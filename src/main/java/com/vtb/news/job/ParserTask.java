package com.vtb.news.job;

import com.vtb.news.model.News;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.vtb.news.service.NewsService;

import java.io.IOException;

@Component
public class ParserTask {

    @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = Integer.MAX_VALUE)
    public void parseNews() {
//finance
//        https://finance.rftoday.ru/1358/0/
        for (int i = 1; i <=175; i++) {
            String url = "https://afnews.ru/ekonomika";
            url+="?page=";
            url+=i;
            try {
                Document document = Jsoup.connect(url)
                        .userAgent("Mozilla")
                        .referrer("https://google.com")
                        .get();

                Elements news = document.getElementsByClass("title title-random-slider").select("a");

                for (Element el : news) {
                    String title = el.ownText();

                    if (!newsService.isExist(title)) {
                        String data = document.getElementsByClass("post-meta").get(0).text();
                        String textUrl = el.attr("href");

                        Document documentText = Jsoup.connect(textUrl)
                                .userAgent("Mozilla")
                                .referrer("https://google.com")
                                .get();

                        News obj = new News();
                        obj.setTitle(title);
                        obj.setText(documentText.getElementsByClass("post-summary").text());
                        obj.setDataNews(data.split(" ")[0]+" "+data.split(" ")[1]+" "+data.split(" ")[2]);
                        obj.setViewing(data.split(" ")[4]);
                        obj.setSourceNews("AFnews");
                        obj.setImportant(false);
                        newsService.save(obj);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //banki
        for (int i = 1; i <=7165; i++) {
            String url = "https://www.banki.ru/news/lenta/";
            url+="?page=";
            url+=i;
            try {
                Document document = Jsoup.connect(url)
                        .userAgent("Mozilla")
                        .referrer("https://google.com")
                        .get();

                Elements news = document.getElementsByClass("lf4cbd87d ld6d46e58 lb47af913");

                for (Element el : document.getElementsByClass("lf473447f text-weight-medium").select("a")) {
                    String title = el.ownText();
                    String textUrl = "https://www.banki.ru"+el.attr("href");

                    Document documentText = Jsoup.connect(textUrl)
                            .userAgent("Mozilla")
                            .referrer("https://google.com")
                            .get();

                    Elements textEl = documentText.getElementsByClass("l6d291019");
                    Elements dataEl = documentText.getElementsByClass("l51e0a7a5");
                    Elements viEl = documentText.getElementsByClass("l51e0a7a5");
                    Elements sourceEl = documentText.getElementsByClass("lcb20116d link-simple");
                    if (!newsService.isExist(title)) {
                        News obj = new News();
                        obj.setTitle(title);
                        obj.setText(textEl.get(0).text());
                        obj.setDataNews(dataEl.get(0).text());
                        obj.setViewing(viEl.get(1).text());
                        obj.setSourceNews(sourceEl.last().text());
                        obj.setImportant(true);
                        newsService.save(obj);
                    }
                }

                for (Element el : news.select("a.lf473447f")) {
                    String title = el.ownText();
                    String textUrl = "https://www.banki.ru"+el.attr("href");

                    Document documentText = Jsoup.connect(textUrl)
                            .userAgent("Mozilla")
                            .referrer("https://google.com")
                            .get();

                    Elements textEl = documentText.getElementsByClass("l6d291019");
                    Elements dataEl = documentText.getElementsByClass("l51e0a7a5");
                    Elements viEl = documentText.getElementsByClass("l51e0a7a5");
                    Elements sourceEl = documentText.getElementsByClass("lcb20116d link-simple");
                    if (!newsService.isExist(title)) {
                        News obj = new News();
                        obj.setTitle(title);
                        obj.setText(textEl.get(0).text());
                        obj.setDataNews(dataEl.get(0).text());
                        obj.setViewing(viEl.get(1).text());
                        obj.setSourceNews(sourceEl.last().text());
                        obj.setImportant(false);
                        newsService.save(obj);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("GOOO");
    }
}

