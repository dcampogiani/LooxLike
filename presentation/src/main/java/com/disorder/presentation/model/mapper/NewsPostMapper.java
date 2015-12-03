package com.disorder.presentation.model.mapper;

import com.disorder.presentation.model.NewsPost;

public interface NewsPostMapper {

    NewsPost map(com.disorder.networking.responses.NewsPost origin);

    NewsPost[] map(com.disorder.networking.responses.NewsPost[] origin);
}
