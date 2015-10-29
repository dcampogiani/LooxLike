package com.disorder.looxlike.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.disorder.looxlike.R;
import com.disorder.presentation.utils.ImageDownloader;
import com.disorder.presentation.utils.ImageUrlGenerator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    public interface OnItemSelectedListener {
        void onItemSelected(String item);
    }


    private final List<String> mData;
    private final ImageDownloader mImageDownloader;
    private final ImageUrlGenerator mImageUrlGenerator;
    private final WeakReference<OnItemSelectedListener> mOnItemSelectedListener;

    private static final ImageUrlGenerator.ZOOM_LEVEL zoom_level = ImageUrlGenerator.ZOOM_LEVEL.MEDIUM;

    public ItemAdapter(OnItemSelectedListener onItemSelectedListener, ImageDownloader mImageDownloader, ImageUrlGenerator mImageUrlGenerator) {
        this.mOnItemSelectedListener = new WeakReference<>(onItemSelectedListener);
        this.mData = new ArrayList<>();
        this.mImageDownloader = mImageDownloader;
        this.mImageUrlGenerator = mImageUrlGenerator;
    }

    public void addData(List<String> newData) {
        this.mData.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String c10 = mData.get(position);
        ImageView imageView = holder.mImageView;
        String urlToLoad = mImageUrlGenerator.getUrl(c10, zoom_level);
        mImageDownloader.request(urlToLoad, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemSelectedListener listener = mOnItemSelectedListener.get();
                if (listener != null) {
                    listener.onItemSelected(c10);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
