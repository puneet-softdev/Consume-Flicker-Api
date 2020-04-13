package com.flickr.joshtalks.repository;

import android.util.Log;

import com.flickr.joshtalks.BaseApplication;
import com.flickr.joshtalks.data.GitWebService;
import com.flickr.joshtalks.models.FlickrModel;
import com.flickr.joshtalks.models.FlickrPhotos;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PhotoRepository {

    private final String taskKey = "flickrPhotos";

    private GitWebService gitWebService;

    public PhotoRepository(GitWebService gitWebService){
        this.gitWebService = gitWebService;
    }

    public Single<FlickrModel> apiCall(String methodSearch, String apiKey, String format, String noJsonCallBack, String query, String page) {
        return networkCall(methodSearch, apiKey, format, noJsonCallBack, query, page);
    }

    private Single<FlickrModel> networkCall(String methodSearch, String apiKey, String format, String noJsonCallBack, String query, String page){
        Single<FlickrModel> call = BaseApplication.getInstance().gitWebService.loadPhotos(methodSearch, apiKey, format, noJsonCallBack, query, page)
                .subscribeOn(Schedulers.io());
        call.subscribe(repos -> {
            Single.just(repos);
        }, error->{
            Log.e(taskKey, error.getMessage());
        });
        return call;
    }
}
