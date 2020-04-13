package com.flickr.joshtalks.ui.photos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flickr.joshtalks.R;
import com.flickr.joshtalks.databinding.RowPhotosBinding;
import com.flickr.joshtalks.models.Photo;
import com.flickr.joshtalks.ui.photos.PhotosViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosViewHolder> {

    RowPhotosBinding rowPhotosBinding;
    private List<Photo> photos;
    private Context context;

    public PhotosAdapter(Context context, List<Photo> photos){
        this.photos = photos;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rowPhotosBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_photos, parent, false);
        return new PhotosViewHolder(rowPhotosBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.bind(photo);

//        Glide.with(context)
//                .load(photo.getUrl())
//                .thumbnail(0.5f)
//                .into(holder.rowPhotosBinding.sharedImageRepoOwner);


    }

    @Override
    public int getItemCount() {
        return photos!=null?photos.size():0;
    }

    public void notifyData(ArrayList<Photo> list) {
        photos = new ArrayList<>();
        photos.clear();
        photos.addAll(list);
        notifyDataSetChanged();
    }
}
