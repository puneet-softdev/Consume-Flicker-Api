package com.flickr.joshtalks.ui.photos.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.flickr.joshtalks.data.ApiResponse;
import com.flickr.joshtalks.data.GitWebService;
import com.flickr.joshtalks.models.Photo;
import com.flickr.joshtalks.repository.PhotoRepository;
import com.flickr.joshtalks.utils.AppUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PhotosViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public MutableLiveData<ApiResponse> loginResponse() {
        return responseLiveData;
    }

    public void hitFlickrPhotosApi(String query, String page, GitWebService gitWebService){
        PhotoRepository repository = new PhotoRepository(gitWebService);
        disposables.add(repository.apiCall(AppUtil.METHOD_SEARCH, AppUtil.API_KEY, "json", "1", query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result-> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
