package com.flickr.joshtalks.data;

import com.flickr.joshtalks.models.FlickrModel;
import com.flickr.joshtalks.models.FlickrPhotos;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitWebService {

    @GET("rest")
    Single<FlickrModel> loadPhotos(@Query("method") String methodSearch, @Query("api_key") String apiKey, @Query("format") String format, @Query("nojsoncallback") String noJsonCallBack, @Query("text") String query, @Query("page") String page);
}
