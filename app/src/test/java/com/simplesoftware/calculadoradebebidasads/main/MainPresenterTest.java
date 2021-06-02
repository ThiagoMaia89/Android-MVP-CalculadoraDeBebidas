package com.simplesoftware.calculadoradebebidasads.main;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Mock
    private MainContract.MvpView mView;
    private MainPresenter mPresenter;

    @Mock
    private View view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new MainPresenter(mView);
    }

    @Test
    public void handleAddButtonClick() {
        mPresenter.handleAddButtonClick(view);
        verify(mView).getDataFromEditTexts();
    }

    @Test
    public void handleClearButtonClick() {
    mPresenter.handleClearButtonClick(view);
    verify(mView).showBestOptionOnTextView();
    }
}