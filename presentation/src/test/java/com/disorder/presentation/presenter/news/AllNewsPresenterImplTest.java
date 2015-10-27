package com.disorder.presentation.presenter.news;

import com.disorder.networking.services.FakeLooxLikeAPI;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.Browser;
import com.disorder.presentation.utils.ImmediateRxScheduler;
import com.disorder.presentation.utils.ItemPageUrlEvaluator;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class AllNewsPresenterImplTest {

    private static final int viewTimeOut = 100;

    private AllNewsPresenterImpl subjectUnderTest;
    private NewsView mNewsView;

    @Before
    public void setUp() throws Exception {
        mNewsView = Mockito.mock(NewsView.class);
        LooxLikeAPI mLooxLikeAPI = new FakeLooxLikeAPI();
        RxScheduler mRxScheduler = new ImmediateRxScheduler();
        NewsPostMapper mNewsPostMapper = Mockito.mock(NewsPostMapper.class);
        ItemPageUrlEvaluator mItemPageUrlEvaluator = Mockito.mock(ItemPageUrlEvaluator.class);
        Browser mBrowser = Mockito.mock(Browser.class);
        subjectUnderTest = new AllNewsPresenterImpl(mLooxLikeAPI, mRxScheduler, mNewsPostMapper, mItemPageUrlEvaluator, mBrowser);
        subjectUnderTest.attachView(mNewsView);
    }

    @Test
    public void testLoadMore() throws Exception {
        subjectUnderTest.loadMore();
        verify(mNewsView, timeout(viewTimeOut)).showLoading();
        verify(mNewsView, timeout(viewTimeOut)).hideLoading();
        verify(mNewsView, timeout(viewTimeOut)).updateModel(any(NewsPost[].class));
    }
}