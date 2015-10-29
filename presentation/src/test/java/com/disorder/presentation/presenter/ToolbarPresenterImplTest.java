package com.disorder.presentation.presenter;

import com.disorder.presentation.view.ToolbarView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class ToolbarPresenterImplTest {

    private ToolbarPresenter subjectUnderTest;
    private ToolbarView view;

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(ToolbarView.class);
        subjectUnderTest = new ToolbarPresenterImpl();
        subjectUnderTest.attachView(view);
    }

    @Test
    public void testOnNewsButtonClick() throws Exception {
        subjectUnderTest.onNewsButtonClick();
        verify(view).showPage(ToolbarView.NEWS);
    }

    @Test
    public void testOnUserButtonClick() throws Exception {
        subjectUnderTest.onUserButtonClick();
        verify(view).showPage(ToolbarView.USER);
    }

    @Test
    public void testOnFavouritesButtonClick() throws Exception {
        subjectUnderTest.onFavouritesButtonClick();
        verify(view).showPage(ToolbarView.FAVOURITES);
    }
}