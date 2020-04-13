package com.flickr.joshtalks.ui.photos;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flickr.joshtalks.BR;
import com.flickr.joshtalks.R;
import com.flickr.joshtalks.databinding.RowPhotosBinding;
import com.flickr.joshtalks.models.Photo;
import com.flickr.joshtalks.ui.BitmapBind;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PhotosViewHolder extends RecyclerView.ViewHolder {

    public RowPhotosBinding rowPhotosBinding;

    public PhotosViewHolder(RowPhotosBinding rowPhotosBinding) {
        super(rowPhotosBinding.getRoot());
        this.rowPhotosBinding = rowPhotosBinding;
    }

    public void bind(Photo photo){
        rowPhotosBinding.setVariable(BR.photos, photo);
        BitmapBind.bindBitmapToImage(rowPhotosBinding.sharedImageRepoOwner, photo.getUrl());
    }

    public void setImage(ImageView imageView, String url){

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 200, 200, false);
                imageView.setImageBitmap(bitmap1);
                //BaseApplication.getInstance().imageCacheProvider.add(url, bitmap1);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e("bitmap failed", e.toString());

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso
                .get()
                .load(Uri.parse(url))
                .placeholder(R.drawable.ic_octoface)
                .noFade()
                .error(R.drawable.ic_octoface)
                .into(target);
        imageView.setTag(target);

    }


}
