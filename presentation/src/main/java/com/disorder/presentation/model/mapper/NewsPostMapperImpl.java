package com.disorder.presentation.model.mapper;


import com.disorder.presentation.model.NewsPost;

public class NewsPostMapperImpl implements NewsPostMapper {

    @Override
    public NewsPost map(com.disorder.networking.responses.NewsPost origin) {
        return NewsPost.create(origin.getId(), origin.getPhotoUrl(), origin.getDescription(), origin.getItemId(), origin.getLikes(), origin.getUsername(), origin.isLiked());
    }

    public NewsPost[] map(com.disorder.networking.responses.NewsPost[] origin) {
        NewsPost[] result = new NewsPost[origin.length];
        int len = origin.length;
        for (int i = 0; i < len; i++)
            result[i] = map(origin[i]);
        return result;
    }
}
