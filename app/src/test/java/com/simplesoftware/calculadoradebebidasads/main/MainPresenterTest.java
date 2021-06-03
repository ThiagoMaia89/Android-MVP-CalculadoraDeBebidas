package com.simplesoftware.calculadoradebebidasads.main;

import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Mock
    private MainContract.MvpView mView;
    private MainPresenter mPresenter;
    private int count;
    @Mock
    private MainContract.MvpView mvpView;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mPresenter = new MainPresenter(mView);
    }

    @Test
    public void buttonClickCountListener() {
        mPresenter.clickCountListener(count);
        verify(mView);
    }

}
