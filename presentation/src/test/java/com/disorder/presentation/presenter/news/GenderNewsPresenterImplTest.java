package com.disorder.presentation.presenter.news;

import com.disorder.networking.services.FakeLooxLikeAPI;
import com.disorder.networking.services.LooxLikeAPI;
import com.disorder.presentation.model.NewsPost;
import com.disorder.presentation.model.mapper.NewsPostMapper;
import com.disorder.presentation.utils.ImmediateRxScheduler;
import com.disorder.presentation.utils.RxScheduler;
import com.disorder.presentation.view.NewsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

public class GenderNewsPresenterImplTest {

    private GenderNewsPresenterImpl subjectUnderTest;
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
        mNewsPostMapper = Mockito.mock(NewsPostMapper.class);
    }


    @Test
    public void testLoadMoreMale() {
        testLoadMoreWithGender(NewsView.MALE);
    }

    @Test
    public void testLoadMoreFemale() {
        testLoadMoreWithGender(NewsView.FEMALE);
    }

    @Test
    public void testLoadMoreNoGender() {
        testLoadMoreWithGender(NewsView.NO_GENDER);
    }

    private void testLoadMoreWithGender(int gender) {
        subjectUnderTest = new GenderNewsPresenterImpl(mLooxLikeAPI, mRxScheduler, mNewsPostMapper, gender);
        subjectUnderTest.attachView(mNewsView);
        subjectUnderTest.loadMore();
        verify(mNewsView, timeout(viewTimeOut)).showLoading();
        verify(mNewsView, timeout(viewTimeOut)).hideLoading();
        verify(mNewsView, timeout(viewTimeOut)).updateModel(any(NewsPost[].class));
    }

}