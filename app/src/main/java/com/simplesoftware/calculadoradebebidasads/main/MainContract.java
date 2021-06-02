package com.simplesoftware.calculadoradebebidasads.main;

import android.view.View;

public interface MainContract {

    interface MvpView{
        void showBestOptionOnTextView();

        void getDataFromEditTexts();
    }

    interface Presenter{
        void handleAddButtonClick(View view);

        void handleClearButtonClick(View view);
    }

}
