package com.example.rxjava.logic.network;

import com.example.rxjava.logic.model.Chapter;
import com.example.rxjava.logic.model.Novel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsoupUtils {
    private static final String TAG = "JsoupUtils";

    public static Novel getNovel(String html) {
        Document parse = Jsoup.parse(html);
        String title = parse.selectFirst("body > div.chapterlist > h2 > strong").text();
        Elements elements = parse.select("#myarticle > table > tbody > tr > td > a");
        List<Chapter> catalog = elements.stream().map(element ->
                new Chapter(element.text(),
                        ConstantUtils.BASE_URL + element.attr("href")))
                .collect(Collectors.toList());
        Novel novel = new Novel();
        novel.setTitle(title);
        novel.setCatalog(catalog);
        return novel;
    }

    public static Chapter getChapter(String html) {
        Document parse = Jsoup.parse(html);
        String title = parse.selectFirst("#title").text();
        String content = parse.select("body > div.chapterlist > div.Readarea").text();
        ArrayList<String> content2 = new ArrayList<>(Arrays.asList(content.split(" ")));
        Chapter chapter = new Chapter();
        chapter.setTitle(title);
        chapter.setContent(content2);
        return chapter;
    }
}
