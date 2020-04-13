package com.flickr.joshtalks.ui.photos.view.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.flickr.joshtalks.BaseApplication;
import com.flickr.joshtalks.R;
import com.flickr.joshtalks.data.ApiResponse;
import com.flickr.joshtalks.databinding.FragmentPhotosBinding;
import com.flickr.joshtalks.models.Photo;
import com.flickr.joshtalks.ui.photos.adapter.PhotosAdapter;
import com.flickr.joshtalks.ui.photos.view.BaseFragment;
import com.flickr.joshtalks.ui.photos.viewmodel.PhotosViewModel;
import com.flickr.joshtalks.utils.AppUtil;

import java.util.ArrayList;
import java.util.List;

import dagger.android.AndroidInjection;

public class PhotosFragment extends BaseFragment {

    PhotosViewModel photosViewModel;
    FragmentPhotosBinding fragmentPhotosBinding;
    ProgressDialog progressDialog;

    List<Photo> reposList;

    PhotosAdapter photosAdapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);
        photosViewModel.loginResponse().observe(getViewLifecycleOwner(), this::consumeResponse);
        fragmentPhotosBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_photos, container, false);
        View view = fragmentPhotosBinding.getRoot();

        photosAdapter = new PhotosAdapter(getContext(), reposList);
        fragmentPhotosBinding.recyclerPhotos.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        fragmentPhotosBinding.recyclerPhotos.setAdapter(photosAdapter);
        fragmentPhotosBinding.recyclerPhotos.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressDialog = AppUtil.getProgressDialog(getContext(), "Please wait...");

        photosViewModel.hitFlickrPhotosApi("cat", "1", BaseApplication.getInstance().gitWebService);

    }

    @Override
    public void onAttach(Context context) {
        AndroidInjection.inject(getActivity());
        super.onAttach(context);
    }

    private void consumeResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                ((PhotosAdapter)fragmentPhotosBinding.recyclerPhotos.getAdapter()).notifyData((ArrayList<Photo>) apiResponse.data.getPage().getPhotoList());
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"something went wrong", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
