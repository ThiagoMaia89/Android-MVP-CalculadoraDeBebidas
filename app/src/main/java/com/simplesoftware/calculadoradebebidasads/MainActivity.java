package com.simplesoftware.calculadoradebebidasads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private AdView adView;
    private AdRequest adRequest;
    private InterstitialAd interstitialAd;
    ArrayList<String> listItens = new ArrayList<>();
    ArrayAdapter<String> adapter;
    int opcao = 1;
    double menor = Double.MAX_VALUE;
    int bestOption;
    int count01 = 0, count02 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-9823376642365552/8011245989");
        interstitialAd.loadAd(new AdRequest.Builder().build());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.buy_Premium:
                        gotoUrl("https://play.google.com/store/apps/details?id=com.simplesoftware.calculadoradecerveja.paid");
                }
                return false;
            }
        });

        final EditText et_ml = (EditText) findViewById(R.id.et_ml);
        final EditText et_valor = (EditText) findViewById(R.id.et_valor);
        final EditText melhor_opcao = (EditText) findViewById(R.id.tv_melhor_opcao);
        Button btn_adicionar = (Button) findViewById(R.id.btn_adicionar);
        Button btn_limpar = (Button) findViewById(R.id.btn_limpar);
        final ListView list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItens);
        list.setAdapter(adapter);

        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                count01 = count01 + 1;
                if (count01 == 1) {
                    interstitialAd.show();
                }
                if (count01 % 5 == 0) {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }
                if (et_ml.getText().toString().trim().equals("") || et_valor.getText().toString().trim().equals("")) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Campo não preenchido")
                            .setMessage("Favor preencher todos os campos")
                            .setPositiveButton("OK", null)
                            .show();
                } else {
                    double valor = Double.parseDouble(et_valor.getText().toString());
                    double ml = Double.parseDouble(et_ml.getText().toString());
                    double total = (valor / ml) * 1000;
                    listItens.add("Opção " + opcao++ + ": R$ " + String.format("%.2f", total).replace(".", ",") + " por litro");

                    double[] vet = new double[listItens.size()];

                    for (int i = 0; i < vet.length; i++) {
                        vet[i] = total;
                        if (vet[i] < menor) {
                            menor = vet[i];
                            bestOption = vet.length;
                            String melhorOpt = "Opção " + bestOption;
                            melhor_opcao.setText(melhorOpt);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    et_valor.setText("");
                    et_ml.setText("");
                    et_ml.requestFocus();
                }
            }
        });
        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                count02 = count02 + 1;

                if (count02 == 1) {
                    interstitialAd.show();
                }
                if (count02 % 5 == 0) {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }
                listItens.clear();
                melhor_opcao.setText("");
                opcao = 1;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}