package com.flickr.joshtalks.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class AppUtil {

    public static final String API_KEY = "ada73417582985add25e9bd7bdef4d94";
    public static final String BASE_URL = "https://api.flickr.com/services/";
    public static final String METHOD_SEARCH = "flickr.photos.search";


    private static volatile AppUtil instance = null;

    private AppUtil() {

    }

    public static AppUtil getInstance() {
        if (instance == null) {
            synchronized (AppUtil.class) {
                if (instance == null) {
                    instance = new AppUtil();
                }
            }
        }
        return instance;
    }

    public static ProgressDialog getProgressDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
