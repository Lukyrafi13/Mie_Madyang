package com.luckyrafi13.miemadyang.Penjualan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Detail_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Form_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;

public class Daftar_Penjualan_Activity extends AppCompatActivity {

    FloatingActionButton fab_TambahPenjualan;
    CardView cv_Penjualan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_penjualan);

        fab_TambahPenjualan = findViewById(R.id.fab_TambahPenjualan);
        cv_Penjualan1 = findViewById(R.id.cv_Penjualan1);

        fab_TambahPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_Penjualan_Activity.this, Form_Penjualan_Activity.class);
                startActivity(intent);
            }
        });

        cv_Penjualan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }
}