package com.luckyrafi13.miemadyang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Penjualan.Daftar_Penjualan_Activity;
import com.luckyrafi13.miemadyang.Stok.Daftar_Stok_Activity;
import com.luckyrafi13.miemadyang.User.Daftar_User_Activity;

public class Home_Activity extends AppCompatActivity {

    ImageButton Btn_DaftarKategori, Btn_DaftarMenu, Btn_DaftarPegewai, Btn_DaftarPelanggan, Btn_Penjualan, Btn_Stok,
            Btn_RekapPenjualan, Btn_RekapPenjualanHariIni, Btn_HakAkses, Btn_RekapDetailPenjualan, Btn_RekapJualByKasir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Btn_DaftarKategori = findViewById(R.id.Btn_DaftarKategori);
        Btn_DaftarMenu = findViewById(R.id.Btn_DaftarMenu);
        Btn_DaftarPegewai = findViewById(R.id.Btn_DaftarPegawai);
        Btn_DaftarPelanggan = findViewById(R.id.Btn_DaftarPelanggan);
        Btn_Penjualan = findViewById(R.id.Btn_Penjualan);
        Btn_Stok = findViewById(R.id.Btn_Stok);
        Btn_RekapPenjualan = findViewById(R.id.Btn_RekapPenjualan);
        Btn_RekapPenjualanHariIni = findViewById(R.id.Btn_RekapPenjualanDetailHariIni);
        Btn_HakAkses = findViewById(R.id.Btn_HakAkses);
        Btn_RekapDetailPenjualan = findViewById(R.id.Btn_RekapDetailPenjualan);
        Btn_RekapJualByKasir = findViewById(R.id.Btn_RekapJualByKasir);

        Btn_DaftarKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, Daftar_Kategori_Activity.class);
                startActivity(intent);
            }
        });


        Btn_DaftarPegewai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, Daftar_User_Activity.class);
                startActivity(intent);
            }
        });

        Btn_DaftarPelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Btn_Penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, Daftar_Penjualan_Activity.class);
                startActivity(intent);
            }
        });

        Btn_Stok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Activity.this, Daftar_Stok_Activity.class);
                startActivity(intent);
            }
        });

        Btn_RekapPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Btn_RekapPenjualanHariIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Btn_HakAkses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Btn_RekapDetailPenjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Btn_RekapJualByKasir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}