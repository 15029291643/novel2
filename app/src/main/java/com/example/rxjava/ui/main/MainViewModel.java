package com.example.rxjava.ui.main;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.rxjava.logic.model.Chapter;
import com.example.rxjava.logic.model.Novel;
import com.example.rxjava.logic.network.OkhttpUtils;

import java.util.function.Function;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private final MutableLiveData<Novel> mNovel = new MutableLiveData<>();
    private final MutableLiveData<Chapter> mChapter = new MutableLiveData<>();
    private final LiveData<String> mTitle = Transformations.map(mChapter, Chapter::getTitle);
    private final LiveData<String> mContent = Transformations.map(mChapter, input ->
            input.getContent()
                    .stream()
                    .reduce((s1, s2) -> s1 + "\n\n" + "        " + s2)
                    .get());

    public MutableLiveData<Novel> getNovel() {
        return mNovel;
    }

    public LiveData<String> getTitle() {
        return mTitle;
    }

    public LiveData<String> getContent() {
        return mContent;
    }

    public void setNovel(String url) {
        mDisposable.add(getNovel(url)
                .subscribeOn(Schedulers.io())
                .subscribe(mNovel::postValue));
    }

    public void setChapter(int position) {
        mDisposable.add(getChapter(mNovel.getValue().getCatalog().get(position).getUrl())
                .subscribeOn(Schedulers.io())
                .subscribe(mChapter::postValue));
    }

    @SuppressLint("CheckResult")
    public Flowable<Novel> getNovel(String url) {
        return Flowable.create(emitter ->
                emitter.onNext(OkhttpUtils.getNovel(url)), BackpressureStrategy.ERROR);
    }

    public Flowable<Chapter> getChapter(String url) {
        return Flowable.create(emitter ->
                emitter.onNext(OkhttpUtils.getChapter(url)), BackpressureStrategy.ERROR);
    }
}
