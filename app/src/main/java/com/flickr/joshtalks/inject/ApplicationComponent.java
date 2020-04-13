package com.flickr.joshtalks.inject;

import com.flickr.joshtalks.BaseApplication;
import com.flickr.joshtalks.ui.inject.ContributorsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, NetworkApiModule.class, ContributorsModule.class})
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    public interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

        ApplicationComponent build();
    }

}
