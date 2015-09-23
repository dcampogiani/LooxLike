package com.disorder.presentation.presenter;

import com.disorder.networking.services.FakeLooxLikeAPI;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.model.mapper.NewsPostMapperImpl;
import com.disorder.presentation.utils.ImmediateRxScheduler;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class NewsPresenterImplTest {

    private NewsPresenterImpl subjectUnderTest;
    private LooxLikeAPI mLooxLikeAPI;
    private RxScheduler mRxScheduler;
    private NewsPostMapper mNewsPostMapper;
    private NewsView mNewsView;

    private static final int viewTimeOut = 100;

    @Before
    public void setUp() throws Exception {

        mNewsView = Mockito.mock(NewsView.class);
        mLooxLikeAPI = new FakeLooxLikeAPI();
        mRxScheduler = new ImmediateRxScheduler();
        mNewsPostMapper = new NewsPostMapperImpl();
        subjectUnderTest = new NewsPresenterImpl(mLooxLikeAPI, mRxScheduler, mNewsPostMapper);
        subjectUnderTest.attachView(mNewsView);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenGenderNotSet() {
        subjectUnderTest.loadMore();
    }

    @Test
    public void testLoadMoreMale() throws Exception {
        testLoadMoreWithSex(NewsView.MALE);

    }

    @Test
    public void testLoadMoreFemale() throws Exception {
        testLoadMoreWithSex(NewsView.FEMALE);

    }

    private void testLoadMoreWithSex(@NewsView.Gender int gender) {
        subjectUnderTest.setGender(gender);
        subjectUnderTest.loadMore();
        verify(mNewsView, timeout(viewTimeOut)).showLoading();
        verify(mNewsView, timeout(viewTimeOut)).hideLoading();
        verify(mNewsView, timeout(viewTimeOut)).updateModel(any(NewsPost[].class));
    }
}