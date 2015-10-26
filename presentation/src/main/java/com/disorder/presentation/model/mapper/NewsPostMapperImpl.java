package com.disorder.presentation.model.mapper;


import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.utils.DateIntervalCalculator;
import com.disorder.presentation.utils.DaysRangeProvider;

import org.threeten.bp.LocalDateTime;

import javax.inject.Inject;

public class NewsPostMapperImpl implements NewsPostMapper {


    private DateIntervalCalculator mDateIntervalCalculator;
    private DaysRangeProvider mDaysRangeProvider;

    @Inject
    public NewsPostMapperImpl(DateIntervalCalculator dateIntervalCalculator, DaysRangeProvider daysRangeProvider) {
        this.mDateIntervalCalculator = dateIntervalCalculator;
        this.mDaysRangeProvider = daysRangeProvider;
    }

    @Override
    public NewsPost map(com.disorder.networking.responses.NewsPost origin) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime postTime = LocalDateTime.parse(origin.getCreationTime());
        int days = mDateIntervalCalculator.getDays(postTime, now);
        String presentationCreation = mDaysRangeProvider.getString(days);
        return NewsPost.create(origin.getPostId(), origin.getDescription(), origin.getPhotoUrl(), origin.getC10(), presentationCreation, origin.getUserName(), origin.getNumLikes(), origin.isLiked());
    }

    public NewsPost[] map(com.disorder.networking.responses.NewsPost[] origin) {
        NewsPost[] result = new NewsPost[origin.length];
        int len = origin.length;
        for (int i = 0; i < len; i++)
            result[i] = map(origin[i]);
        return result;
    }
}
