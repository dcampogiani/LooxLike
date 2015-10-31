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
import com.disorder.presentation.utils.UserAvatarUrlGenerator;

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
    private final WeakReference<PostListener> mPostListenerReference;
    private final WeakReference<ScrollListener> mScrollListenerReference;
    private final ImageDownloader mImageDownloader;
    private final UserAvatarUrlGenerator mUserAvatarUrlGenerator;

    public NewsPostAdapter(PostListener postListener, ImageDownloader imageDownloader, ScrollListener scrollListener, UserAvatarUrlGenerator userAvatarUrlGenerator) {
        this.mData = new ArrayList<>(0);
        this.mPostListenerReference = new WeakReference<>(postListener);
        this.mScrollListenerReference = new WeakReference<>(scrollListener);
        this.mImageDownloader = imageDownloader;
        this.mUserAvatarUrlGenerator = userAvatarUrlGenerator;

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
    public void onBindViewHolder(final NewsPostAdapter.ViewHolder holder, final int position) {

        if (almostAtTheEnd(position)) {
            ScrollListener scrollListener = mScrollListenerReference.get();
            if (scrollListener != null)
                scrollListener.almostAtTheEnd();
        }

        final NewsPost item = mData.get(position);
        ImageView avatar = holder.avatar;
        TextView username = holder.userName;
        final TextView likes = holder.likes;
        ImageView photo = holder.photo;
        mImageDownloader.request(mUserAvatarUrlGenerator.getUrl(item.username())).into(avatar);
        mImageDownloader.request(item.photoUrl()).withAnimation(ImageDownloader.Animation.CROSS_FADE).into(photo);

        username.setText(item.username());
        int likesCount = item.likes();
        setLikedText(likes, likesCount, item.username());
        setLikedIcon(likes, item.liked());

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
                toggleLikePost(likes, position);
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

    private void toggleLikePost(TextView textView, int position) {
        PostListener postListener = mPostListenerReference.get();
        if (postListener != null) {
            NewsPost updatedItem = mData.get(position);
            boolean wannaLike = !updatedItem.liked();
            int likes = updatedItem.likes();
            if (wannaLike)
                likes++;
            else likes--;
            setLikedIcon(textView, !updatedItem.liked());
            setLikedText(textView, likes, updatedItem.username());
            toggleLikePostStatus(updatedItem.id());
            postListener.onLike(updatedItem);
        }
    }

    private void toggleLikePostStatus(long id) {
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            NewsPost currentPost = mData.get(i);
            boolean newLikeStatus = !currentPost.liked();
            if (currentPost.id() == id) {
                NewsPost updatedPost = NewsPost.updateLike(currentPost, newLikeStatus);
                mData.set(i, updatedPost);
            }
        }
    }

    private void setLikedIcon(TextView textView, boolean liked) {
        @DrawableRes int favourite_icon = getLikedIcon(liked);
        Drawable fullFavourite = ContextCompat.getDrawable(textView.getContext(), favourite_icon);
        textView.setCompoundDrawablesWithIntrinsicBounds(fullFavourite, null, null, null);
    }

    private
    @DrawableRes
    int getLikedIcon(boolean value) {
        if (value)
            return R.drawable.ic_favorite_accent_full_36dp;
        else return R.drawable.ic_favorite_accent_empty_36dp;
    }

    private void setLikedText(TextView textView, int numLikes, String username) {
        if (numLikes > 0) {
            String likesText = textView.getContext().getResources().getQuantityString(R.plurals.likes, numLikes, numLikes) + " " + username;
            textView.setText(likesText);
        } else textView.setText(textView.getContext().getString(R.string.no_likes));
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
