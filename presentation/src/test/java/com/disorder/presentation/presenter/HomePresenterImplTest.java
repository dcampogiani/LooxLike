package com.disorder.presentation.presenter;

import com.disorder.presentation.view.HomeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class HomePresenterImplTest {

    private HomePresenter subjectUnderTest;
    private HomeView view;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(HomeView.class);
        subjectUnderTest = new HomePresenterImpl();
        subjectUnderTest.attachView(view);
    }

    @Test
    public void testOnNewsButtonClick() throws Exception {
        subjectUnderTest.onNewsButtonClick();
        verify(view).showPage(HomeView.NEWS);
    }

    @Test
    public void testOnUserButtonClick() throws Exception {
        subjectUnderTest.onUserButtonClick();
        verify(view).showPage(HomeView.USER);
    }

    @Test
    public void testOnFavouritesButtonClick() throws Exception {
        subjectUnderTest.onFavouritesButtonClick();
        verify(view).showPage(HomeView.FAVOURITES);
    }
}