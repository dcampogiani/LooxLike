package com.disorder.looxlike.adapters;


import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.disorder.looxlike.R;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.utils.ImageDownloader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class NewsPostAdapter extends RecyclerView.Adapter<NewsPostAdapter.ViewHolder> {

    private static final float loadMorePercentageThreshold = 0.7f;

    public interface PostListener {
        void onUser(NewsPost newsPost);

        void onLike(NewsPost newsPost);

        void onBuy(NewsPost newsPost);
    }

    public interface ScrollListener {
        void almostAtTheEnd();
    }

    private final List<NewsPost> mData;
    private WeakReference<PostListener> mPostListenerReference;
    private WeakReference<ScrollListener> mScrollListenerReference;
    private ImageDownloader mImageDownloader;

    public NewsPostAdapter(PostListener postListener, ImageDownloader imageDownloader, ScrollListener scrollListener) {
        this.mData = new ArrayList<>(0);
        this.mPostListenerReference = new WeakReference<>(postListener);
        this.mScrollListenerReference = new WeakReference<>(scrollListener);
        this.mImageDownloader = imageDownloader;

        //setHasStableIds(true);
        /*
        In a real environment each post will have a different id
        Right now we are receiving the same posts for different pages
        So we can't use hasStableId optimization
        */
    }

    /*
    I know future Daniele is gonna hate me
    But to date we don't have stable ids
    */
    /*
    @Override
    public long getItemId(int position) {
        return mData.get(position).id();
    }*/

    public void addData(List<NewsPost> newData) {
        this.mData.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public NewsPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(NewsPostAdapter.ViewHolder holder, int position) {

        if (almostAtTheEnd(position)) {
            ScrollListener scrollListener = mScrollListenerReference.get();
            if (scrollListener != null)
                scrollListener.almostAtTheEnd();
        }

        final NewsPost item = mData.get(position);
        ImageView avatar = holder.avatar;
        TextView username = holder.userName;
        TextView likes = holder.likes;
        ImageView photo = holder.photo;

        //TODO load avatar
        mImageDownloader.request(item.photoUrl(), avatar);
        mImageDownloader.request(item.photoUrl(), photo);

        username.setText(item.username());
        int likesCount = item.likes();
        if (likesCount > 0) {
            StringBuilder textBuilder = new StringBuilder(likes.getContext().getResources().getQuantityString(R.plurals.likes, item.likes(), item.likes()));
            String likesText = textBuilder.append(" ").append(item.username()).toString();
            likes.setText(likesText);
        }
        @DrawableRes int favourite_icon = R.drawable.ic_favorite_accent_empty_36dp;
        if (item.liked())
            favourite_icon = R.drawable.ic_favorite_accent_full_36dp;
        Drawable fullFavourite = ContextCompat.getDrawable(likes.getContext(), favourite_icon);
        likes.setCompoundDrawablesWithIntrinsicBounds(fullFavourite, null, null, null);
        holder.description.setText(item.description());
        holder.creation.setText(item.creation());

        View.OnClickListener userClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostListener postListener = mPostListenerReference.get();
                if (postListener != null)
                    postListener.onUser(item);
            }
        };

        avatar.setOnClickListener(userClickListener);
        username.setOnClickListener(userClickListener);
        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostListener postListener = mPostListenerReference.get();
                if (postListener != null)
                    postListener.onLike(item);
            }
        });
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostListener postListener = mPostListenerReference.get();
                if (postListener != null)
                    postListener.onBuy(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private boolean almostAtTheEnd(int position) {
        final int currentItem = position + 1;
        final float threshold = mData.size() * loadMorePercentageThreshold;
        return currentItem > threshold;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView avatar;
        public final TextView userName;
        public final ImageView photo;
        public final TextView likes;
        public final ImageButton buy;
        public final TextView description;
        public final TextView creation;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            userName = (TextView) itemView.findViewById(R.id.username);
            photo = (ImageView) itemView.findViewById(R.id.image);
            likes = (TextView) itemView.findViewById(R.id.likes);
            buy = (ImageButton) itemView.findViewById(R.id.buy);
            description = (TextView) itemView.findViewById(R.id.description);
            creation = (TextView) itemView.findViewById(R.id.creation);
        }
    }
}
