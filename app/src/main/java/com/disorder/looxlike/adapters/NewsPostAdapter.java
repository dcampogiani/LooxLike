package com.disorder.looxlike.adapters;


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

import java.util.ArrayList;
import java.util.List;

public class NewsPostAdapter extends RecyclerView.Adapter<NewsPostAdapter.ViewHolder> {

    private static final float loadMoreThreshold = 0.7f;

    public interface PostListener {
        void onUser(NewsPost newsPost);

        void onLike(NewsPost newsPost);

        void onBuy(NewsPost newsPost);
    }

    public interface ScrollListener {
        void almostAtTheEnd();
    }

    private List<NewsPost> mData;
    private PostListener mPostListener;
    private ImageDownloader mImageDownloader;
    private ScrollListener mScrollListener;

    public NewsPostAdapter(PostListener postListener, ImageDownloader imageDownloader, ScrollListener scrollListener) {
        this.mData = new ArrayList<>(0);
        this.mPostListener = postListener;
        this.mImageDownloader = imageDownloader;
        this.mScrollListener = scrollListener;
    }

    public void addData(List<NewsPost> newData) {
        this.mData.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public NewsPostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsPostAdapter.ViewHolder holder, int position) {

        if (almostAtTheEnd(position))
            mScrollListener.almostAtTheEnd();

        final NewsPost item = mData.get(position);
        ImageView avatar = holder.avatar;
        TextView username = holder.userName;
        TextView likes = holder.likes;
        ImageView photo = holder.photo;

        //TODO load avatar
        mImageDownloader.request(item.photoUrl(), avatar);
        mImageDownloader.request(item.photoUrl(), photo);

        username.setText(item.username());
        likes.setText(Integer.toString(item.likes()));
        if (item.liked())
            likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_favorite_accent_full_36dp, 0, 0, 0);
        holder.description.setText(item.description());
        holder.creation.setText(item.creation());

        View.OnClickListener userClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPostListener.onUser(item);
            }
        };

        avatar.setOnClickListener(userClickListener);
        username.setOnClickListener(userClickListener);
        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPostListener.onLike(item);
            }
        });
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPostListener.onBuy(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private boolean almostAtTheEnd(int position) {
        return position >= mData.size() * loadMoreThreshold;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView userName;
        public ImageView photo;
        public TextView likes;
        public ImageButton buy;
        public TextView description;
        public TextView creation;

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
