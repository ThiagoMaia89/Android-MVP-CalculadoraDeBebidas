package com.simplesoftware.calculadoradebebidasads.main;

import android.view.View;
import android.widget.EditText;

import com.simplesoftware.calculadoradebebidasads.R;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView mView;

    MainPresenter(MainContract.MvpView view){
        mView = view;
    }

    @Override
    public void handleAddButtonClick(View view) {
        mView.getDataFromEditTexts();


    }

    @Override
    public void handleClearButtonClick(View view) {

    }
}
