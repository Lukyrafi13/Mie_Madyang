package com.luckyrafi13.miemadyang.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Konfigurasi.Requesthandler;
import com.luckyrafi13.miemadyang.R;

import java.util.HashMap;

public class Form_Menu_Activity extends AppCompatActivity {

    EditText et_NamaMenu,et_HargaJual,et_Stok,et_IdKategori;
    Button btn_SimpanMenu;

    private String URL_ADD = "http://192.168.100.4:8080/surat/Surat/tambah_menu";
    private String idMenu,namaMenu,hargaJual,stok,kategoriMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_menu);

        et_NamaMenu = findViewById(R.id.et_NamaMenu);
        et_HargaJual = findViewById(R.id.et_HargaJual);
        et_Stok = findViewById(R.id.et_Stok);
        et_IdKategori = findViewById(R.id.et_IdKategori);
        btn_SimpanMenu = findViewById(R.id.btn_SimpanMenu);

        Intent intent = getIntent();
        et_IdKategori.setText(intent.getStringExtra("KEY_id_Kategori"));

        btn_SimpanMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahMenu();
            }
        });

    }
    private void TambahMenu() {

        namaMenu = et_NamaMenu.getText().toString();
        hargaJual = et_HargaJual.getText().toString();
        stok = et_Stok.getText().toString();
        kategoriMenu = et_IdKategori.getText().toString();

        class TambahMenu extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Form_Menu_Activity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Form_Menu_Activity.this, "Berhasil Menambahkan Menu", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Form_Menu_Activity.this, Daftar_Kategori_Activity.class);
                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("nama_produk", namaMenu);
                params.put("harga_produk", hargaJual);
                params.put("stok", stok);
                params.put("id_kategori", kategoriMenu);
                params.put("terjual", "0");

                Requesthandler rh = new Requesthandler();
                String res = rh.sendPostRequest(URL_ADD, params);
                return res;
            }
        }

        TambahMenu as = new TambahMenu();
        as.execute();

    }
}