package com.luckyrafi13.miemadyang.Stok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Form_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;

public class Daftar_Stok_Activity extends AppCompatActivity {

    FloatingActionButton fab_TambahStok;
    CardView cv_Stok1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_stok);

        fab_TambahStok = findViewById(R.id.fab_TambahStok);
        cv_Stok1 = findViewById(R.id.cv_Stok1);

        cv_Stok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_Stok_Activity.this, Detail_Stok_Activity.class);
                startActivity(intent);
            }
        });

        fab_TambahStok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_Stok_Activity.this, Form_Stok_Activity.class);
                startActivity(intent);
            }
        });
    }
}