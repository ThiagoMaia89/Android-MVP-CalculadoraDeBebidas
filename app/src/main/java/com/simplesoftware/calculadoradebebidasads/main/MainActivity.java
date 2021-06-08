package com.simplesoftware.calculadoradebebidasads.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.simplesoftware.calculadoradebebidasads.R;
import com.simplesoftware.calculadoradebebidasads.adapters.RecyclerAdapter;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements MainContract.MvpView {

    MainPresenter mPresenter;

    private InterstitialAd interstitialAd;
    private final ArrayList<String> listItens = new ArrayList<>();
    private EditText et_ml, et_valor, tv_melhor_opcao, et_marca;
    private Button btn_adicionar, btn_limpar;
    private RecyclerView rv_lista_opcoes;
    private RecyclerAdapter adapter;
    int opcao = 1, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        instanciarInterstitialAd();
        instanciarComponentes();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);

        adapter = new RecyclerAdapter(listItens);
        rv_lista_opcoes.setAdapter(adapter);
        rv_lista_opcoes.setLayoutManager(layoutManager);

        btn_adicionar.setOnClickListener(v -> {
            loadInterstitial();

            try {

                double ml = Double.parseDouble(String.valueOf(et_ml.getText()));
                double valor = Double.parseDouble(String.valueOf(et_valor.getText()));
                double totalDouble = mPresenter.findResultValue(ml, valor);
                @SuppressLint("DefaultLocale") String totalString = String.format("%.2f", totalDouble);
                mPresenter.addItemOnList(listItens, opcao, totalString, adapter, et_marca, et_ml);
                tv_melhor_opcao.setText(mPresenter.handleBestOptionText(listItens, ml, valor));

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                et_valor.setText("");
                et_ml.setText("");
                et_marca.setText("");
                et_ml.requestFocus();
                onSuccess();

            } catch (Exception e) {

                onFailure();

            }
        });

        btn_limpar.setOnClickListener(v -> {

            loadInterstitial();
            mPresenter.handleBtnClearOnClick(listItens, tv_melhor_opcao, opcao, adapter);

        });
    }

    private void loadInterstitial() {

        count = count + 1;
        if (count % 5 == 0) {

            if (interstitialAd.isLoaded()) {
                interstitialAd.show();

            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");

            }
        }
    }

    private void instanciarComponentes() {

        et_ml = findViewById(R.id.et_ml);
        et_valor = findViewById(R.id.et_valor);
        tv_melhor_opcao = findViewById(R.id.tv_melhor_opcao);
        btn_adicionar = findViewById(R.id.btn_adicionar);
        btn_limpar = findViewById(R.id.btn_limpar);
        rv_lista_opcoes = findViewById(R.id.rv_lista_opcoes);
        et_marca = findViewById(R.id.et_marca);

    }

    private void instanciarInterstitialAd() {

        MobileAds.initialize(this, initializationStatus -> {
        });
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9823376642365552/8011245989");
        interstitialAd.loadAd(new AdRequest.Builder().build());

    }

    @Override
    public void onSuccess() {

        Toasty.success(this, "Opção adicionada à lista", Toasty.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure() {

        Toasty.error(this, "Favor preencher todos os campos", Toasty.LENGTH_SHORT).show();

    }
}