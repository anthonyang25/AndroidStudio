package com.example.anthonyang.flickrbrowser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anthonyang on 8/10/17.
 */

public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImageViewHolder> {
    private List<Photo> mPhotoList;
    private Context mContext;
    private final String LOG_TAG = FlickrRecyclerViewAdapter.class.getSimpleName();

    public FlickrRecyclerViewAdapter(List<Photo> PhotoList, Context Context) {
        this.mPhotoList = PhotoList;
        this.mContext = Context;
    }

    //create obj -> get data from obj -> put into imageHolder

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (LayoutInflater.from(parent.getContext())) //create view by inflating layout
                .inflate(R.layout.browse, null);
        FlickrImageViewHolder flickrImageViewHolder = new FlickrImageViewHolder(view); //creates flickrImageViewHolder obj
        return flickrImageViewHolder;
    }

    //download images
    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        Photo photoItem = mPhotoList.get(position);
        Log.d(LOG_TAG, "Processing photo item " + photoItem.getTitle() + "--->" + Integer.toString(position));
        Picasso.with(mContext).load(photoItem.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(FlickrImageViewHolder.thumbnail);
        FlickrImageViewHolder.title.setText(photoItem.getTitle());
    }

    //returns number of items found in photo list
    @Override
    public int getItemCount() {
        if (mPhotoList != null) {
            return mPhotoList.size();
        } else {
            return 0;
        }
        //return (null != mPhotoList ? mPhotoList.size() : 0);
    }

    public void loadNewData(List<Photo> newPhotos){
        mPhotoList = newPhotos;
        notifyDataSetChanged(); //Notify any registered observers that the data set has changed.
    }

    public Photo getPhoto(int position) {
        return (null != mPhotoList ? mPhotoList.get(position) : null);
    }
}
