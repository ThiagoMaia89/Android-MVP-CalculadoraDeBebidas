package com.simplesoftware.calculadoradebebidasads.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.simplesoftware.calculadoradebebidasads.R;
import com.simplesoftware.calculadoradebebidasads.adapters.RecyclerAdapter;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.MvpView mView;
    double menor = Double.MAX_VALUE;
    String melhorOpt;
    int bestOption;

    MainPresenter(MainContract.MvpView view) {
        mView = view;
    }

    @Override
    public Double findResultValue(double ml, double valor) {
        return (valor / ml) * 1000;
    }

    @Override
    public void addItemOnList(ArrayList<String> list, int opcao, String total, RecyclerAdapter adapter, Spinner et_marca, EditText et_ml) {
        opcao = list.size() + 1;
        if (et_marca.getSelectedItemPosition() == 0) {
            list.add("Opção " + opcao + ":\nR$ " + total + " por litro");
        } else {
            list.add("Opção " + opcao + ":\nR$ " + total + " por litro (" + et_marca.getSelectedItem()+" "+et_ml.getText().toString()+"ml)");
        }
        adapter.notifyDataSetChanged();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String handleBestOptionText(ArrayList<String> listItens, double ml, double valor) {
        double total = (valor / ml) * 1000;
        double[] vet = new double[listItens.size()];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = total;
            if (vet[i] < menor) {
                menor = vet[i];
                bestOption = vet.length;
                melhorOpt = "Opção " + bestOption + " -> " + String.format("%.0f", ml) + " ml por R$ " + String.format("%.2f", valor).replace(".", ",");
            }
        }
        return melhorOpt;
    }

    @Override
    public void handleBtnClearOnClick(ArrayList<String> listItens, EditText melhor_opcao, int opcao, RecyclerAdapter adapter) {
        listItens.clear();
        melhor_opcao.setText("");
        opcao = 1;
        adapter.notifyDataSetChanged();
    }
}