package com.luckyrafi13.miemadyang.Kategori;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luckyrafi13.miemadyang.Konfigurasi.Requesthandler;
import com.luckyrafi13.miemadyang.R;

import java.util.HashMap;

public class Form_Kategori_Activity extends AppCompatActivity {

    Button btn_SimpanKategori;
    EditText et_NamaKategori, et_DeskripsiKategori;


    private String nama_kategori, deskripsi_kategori;
    private String URL_ADD = "http://192.168.100.4:8080/surat/Surat/tambahkategori";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kategori);

        btn_SimpanKategori = findViewById(R.id.btn_SimpanKategori);
        et_NamaKategori = findViewById(R.id.et_NamaKategori);
        et_DeskripsiKategori = findViewById(R.id.et_DeskripsiKategori);

        btn_SimpanKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TambahKategori();
            }
        });
    }

    private void TambahKategori() {


        nama_kategori = et_NamaKategori.getText().toString();
        deskripsi_kategori = et_DeskripsiKategori.getText().toString();

        class TambahKategori extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Form_Kategori_Activity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Form_Kategori_Activity.this, "Berhasil Menambahkan Kategori", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Form_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("nama_kategori", nama_kategori);
                params.put("deskripsi_kategori", deskripsi_kategori);

                Requesthandler rh = new Requesthandler();
                String res = rh.sendPostRequest(URL_ADD, params);
                return res;
            }
        }

        TambahKategori as = new TambahKategori();
        as.execute();

    }
}