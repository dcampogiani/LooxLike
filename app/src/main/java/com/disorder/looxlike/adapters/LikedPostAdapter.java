package com.disorder.looxlike.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.disorder.looxlike.R;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.utils.ImageDownloader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class LikedPostAdapter extends RecyclerView.Adapter<LikedPostAdapter.ViewHolder> {

    private static final float loadMorePercentageThreshold = 0.7f;


    public interface PhotoListener {
        void onPhoto(String c10);
    }

    public interface ScrollListener {
        void almostAtTheEnd();
    }

    private final List<NewsPost> mData;
    private final WeakReference<PhotoListener> mPostListenerReference;
    private final WeakReference<ScrollListener> mScrollListenerReference;
    private final ImageDownloader mImageDownloader;

    public LikedPostAdapter(PhotoListener photoListener, ImageDownloader imageDownloader, ScrollListener scrollListener) {
        mPostListenerReference = new WeakReference<>(photoListener);
        mScrollListenerReference = new WeakReference<>(scrollListener);
        mImageDownloader = imageDownloader;
        mData = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (almostAtTheEnd(position)) {
            ScrollListener scrollListener = mScrollListenerReference.get();
            if (scrollListener != null)
                scrollListener.almostAtTheEnd();
        }

        final NewsPost post = mData.get(position);
        ImageView photo = holder.photo;
        mImageDownloader.request(post.photoUrl()).withAnimation(ImageDownloader.Animation.CROSS_FADE).into(photo);

        final View.OnClickListener photoClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoListener photoListener = mPostListenerReference.get();
                if (photoListener != null)
                    photoListener.onPhoto(post.c10());

            }
        };
        photo.setOnClickListener(photoClickListener);

    }

    private boolean almostAtTheEnd(int position) {
        final int currentItem = position + 1;
        final float threshold = mData.size() * loadMorePercentageThreshold;
        return currentItem > threshold;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void addData(List<NewsPost> newData) {
        this.mData.addAll(newData);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView photo;

        public ViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
