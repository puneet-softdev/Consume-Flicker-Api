package com.flickr.joshtalks.ui.inject;

import com.flickr.joshtalks.ui.MainActivity;
import com.flickr.joshtalks.ui.photos.view.fragments.PhotosFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ContributorsModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract PhotosFragment bindReposFragment();
}
