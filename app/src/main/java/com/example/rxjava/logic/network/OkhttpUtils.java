package com.example.rxjava.logic.network;

import com.example.rxjava.logic.model.Chapter;
import com.example.rxjava.logic.model.Novel;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUtils {
    private static String getHtml(String url) {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = ClientUtils.getInstance().newCall(request).execute();
            return new String(response.body().bytes(), "gbk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Novel getNovel(String url) {
        Novel novel = JsoupUtils.getNovel(getHtml(url));
        novel.setUrl(url);
        return novel;
    }

    public static Chapter getChapter(String url) {
        Chapter chapter = JsoupUtils.getChapter(getHtml(url));
        chapter.setUrl(url);
        return chapter;
    }
}
