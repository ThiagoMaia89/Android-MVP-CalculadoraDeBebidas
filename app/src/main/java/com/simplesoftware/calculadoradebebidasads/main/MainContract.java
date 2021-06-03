package com.simplesoftware.calculadoradebebidasads.main;

import android.view.View;
import android.widget.EditText;

import com.google.android.gms.ads.InterstitialAd;
import com.simplesoftware.calculadoradebebidasads.adapters.RecyclerAdapter;

import java.util.ArrayList;

public interface MainContract {

    interface MvpView {
        void onSuccess();

        void onFailure();

    }

    interface Presenter {

        Double findResultValue(double ml, double valor);

        void addItemOnList(ArrayList<String> list, int opcao, String total, RecyclerAdapter adapter);

        String handleBestOptionText(ArrayList<String> listItens, double ml, double valor);

        void handleBtnClearOnClick(ArrayList<String> listItens, EditText melhor_opcao, int opcao, RecyclerAdapter adapter);

    }

}
