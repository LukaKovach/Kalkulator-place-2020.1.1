package com.example.kalkulatrplace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView unos_bruto, unos_prirez, unos_clanovi, ispis, ispis_dohodak, ispis_odbitak, ispis_porez, ispis_prirez;
    private Double porez;
    private Double prirez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unos_bruto = findViewById(R.id.unos_bruto);
        unos_clanovi = findViewById(R.id.unos_clanovi);
        unos_prirez = findViewById(R.id.unos_prirez);
        ispis = findViewById(R.id.prikaz_neto);
        ispis_prirez = findViewById(R.id.ispis_prirez);
        ispis_porez = findViewById(R.id.ispis_porez);
        ispis_dohodak = findViewById(R.id.ispis_dohodak);
    }

    @SuppressLint("SetTextI18n")
    public void on_click(View view){

        try {
            String bruto_str = unos_bruto.getText().toString();
            double osobni_odbitak;
            double dohodak;
            double neto_placa;
            double osnovica;
            if (unos_clanovi.getText().toString().trim().isEmpty()){
                osobni_odbitak = 4000.0;
            }
            else {
                osobni_odbitak = 4000.0 + (2500.0 * 0.7 * Double.parseDouble(unos_clanovi.getText().toString()));
            }
            if (Double.parseDouble(bruto_str) > 52452){
                dohodak = Double.parseDouble(bruto_str) - 52452 * 0.20;
            }
            else {
                dohodak = Double.parseDouble(bruto_str) - Double.parseDouble(bruto_str) * 0.20;
            }
            if (osobni_odbitak < dohodak){
                osnovica = dohodak - osobni_odbitak;
                if (Double.parseDouble(String.valueOf(osnovica)) <= 30000.0){
                    porez = osnovica * 0.24;
                    prirez = porez * (Double.parseDouble(unos_prirez.getText().toString())/100);
                    neto_placa = dohodak - porez - prirez;
                    ispis.setText(Double.toString(neto_placa));
                    ispis_dohodak.setText(Double.toString(dohodak));
                    ispis_porez.setText(porez.toString());
                    ispis_prirez.setText(prirez.toString());
                }
                else {
                    porez = 30000 * 0.24;
                    double porez_veci = (osnovica - 30000) * 0.36;
                    prirez = (porez + porez_veci) * (Double.parseDouble(unos_prirez.getText().toString())/100);
                    neto_placa = dohodak - (porez + porez_veci) - prirez;
                    ispis.setText(Double.toString(neto_placa));
                    ispis_dohodak.setText(Double.toString(dohodak));
                    ispis_porez.setText(String.valueOf(porez_veci));
                    ispis_prirez.setText(prirez.toString());
                }
            }
            else {
                ispis.setText(Double.toString(dohodak));
                ispis_dohodak.setText(Double.toString(dohodak));
                ispis_porez.setText(porez.toString());
                ispis_prirez.setText(prirez.toString());
            }
        }
        catch (Exception e){
            //error
        }
    }



}
