package com.simplesoftware.calculadoradebebidasads.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.simplesoftware.calculadoradebebidasads.R;
import com.simplesoftware.calculadoradebebidasads.adapters.RecyclerAdapter;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainPresenter implements MainContract.Presenter {


    private MainContract.MvpView mView;

    MainPresenter(MainContract.MvpView view) {
        mView = view;
    }


    @Override
    public Double findResultValue(double ml, double valor) {
        return (valor / ml) * 1000;
    }

    @Override
    public void addItemOnList(ArrayList<String> list, int opcao, String total, RecyclerAdapter adapter) {
        opcao = list.size() + 1;
        list.add("Opção " + opcao + ": R$ " + total + " por litro");
        adapter.notifyDataSetChanged();

    }



}