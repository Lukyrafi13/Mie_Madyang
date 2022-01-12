package com.luckyrafi13.miemadyang.Penjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luckyrafi13.miemadyang.R;

public class Form_Penjualan_Activity extends AppCompatActivity {

    Button Btn_TambahMenuPenjualan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_penjualan);

        Btn_TambahMenuPenjualan = findViewById(R.id.Btn_TambahMenuPenjualan);

        Btn_TambahMenuPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Form_Penjualan_Activity.this, Tambah_Item_Menu_Penjualan_Activity.class);
                startActivity(intent);
            }
        });
    }
}