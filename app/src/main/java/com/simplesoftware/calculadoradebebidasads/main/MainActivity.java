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


    private AdView adView;
    private AdRequest adRequest;
    private InterstitialAd interstitialAd;
    private ArrayList<String> listItens = new ArrayList<>();
    private EditText et_ml, et_valor, melhor_opcao;
    private Button btn_adicionar, btn_limpar;
    private RecyclerView rv_lista_opcoes;
    private RecyclerAdapter adapter;
    private GridLayoutManager layoutManager;
    int opcao = 1, bestOption, count = 0, position = 0;
    double menor = Double.MAX_VALUE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);

        instanciarInterstitialAd();
        instanciarComponentes();

        layoutManager = new GridLayoutManager(this, 1);

        adapter = new RecyclerAdapter(listItens);
        rv_lista_opcoes.setAdapter(adapter);
        rv_lista_opcoes.setLayoutManager(layoutManager);


        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onClick(View v) {
                loadInterstitial();
                if (et_ml.getText().toString().trim().equals("") || et_valor.getText().toString().trim().equals("")) {
                    onFailure();
                } else {
                    onSuccess();
                    double ml = Double.parseDouble(String.valueOf(et_ml.getText()));
                    double valor = Double.parseDouble(String.valueOf(et_valor.getText()));
                    double totalDouble = mPresenter.findResultValue(ml, valor);
                    @SuppressLint("DefaultLocale") String totalString = String.format("%.2f", totalDouble);
                    mPresenter.addItemOnList(listItens, opcao, totalString, adapter);
                    double[] vet = new double[listItens.size()];
                    for (int i = 0; i < vet.length; i++) {
                        vet[i] = totalDouble;
                        if (vet[i] < menor) {
                            menor = vet[i];
                            bestOption = vet.length;
                            String melhorOpt = "Opção " + bestOption;
                            melhor_opcao.setText(melhorOpt + " -> " + String.format("%.0f", ml) + " ml por R$ " + String.format("%.2f", valor).replace(".", ","));
                        }
                    }
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    et_valor.setText("");
                    et_ml.setText("");
                    et_ml.requestFocus();
                }
            }
        });



        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInterstitial();
                mPresenter.handleBtnClearOnClick(listItens, melhor_opcao, opcao, adapter);
            }
        });
    }

    private void loadInterstitial() {
        interstitialAd.loadAd(new AdRequest.Builder().build());
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
        et_ml = (EditText) findViewById(R.id.et_ml);
        et_valor = (EditText) findViewById(R.id.et_valor);
        melhor_opcao = (EditText) findViewById(R.id.tv_melhor_opcao);
        btn_adicionar = (Button) findViewById(R.id.btn_adicionar);
        btn_limpar = (Button) findViewById(R.id.btn_limpar);
        rv_lista_opcoes = findViewById(R.id.rv_lista_opcoes);
    }

    private void instanciarInterstitialAd() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
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