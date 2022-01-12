package com.luckyrafi13.miemadyang.Kategori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luckyrafi13.miemadyang.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Ubah_Kategori_Activity extends AppCompatActivity {

    Button btn_SimpanKategori;
    EditText et_NamaKategori, et_DeskripsiKategori, et_IdKategori;

    private String id_kategori, nama_kategori, deskripsi_kategori;
    private String URL_UPDATE = "http://192.168.100.4:8080/surat/Surat/ubahkategori";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_kategori);

        btn_SimpanKategori = findViewById(R.id.btn_SimpanKategori);
        et_NamaKategori = findViewById(R.id.et_NamaKategori);
        et_DeskripsiKategori = findViewById(R.id.et_DeskripsiKategori);
        et_IdKategori = findViewById(R.id.et_IDKategori);

        Intent intent = getIntent();
        et_IdKategori.setText(intent.getStringExtra("KEY_id_Kategori"));
        et_NamaKategori.setText(intent.getStringExtra("KEY_nama_Kategori"));
        et_DeskripsiKategori.setText(intent.getStringExtra("KEY_deskripsi_Kategori"));

        btn_SimpanKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateKategori();
            }
        });
    }

    private void UpdateKategori() {
        id_kategori = et_IdKategori.getText().toString();
        nama_kategori = et_NamaKategori.getText().toString();
        deskripsi_kategori = et_DeskripsiKategori.getText().toString();
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[3];
                field[0] = "id_kategori";
                field[1] = "nama_kategori";
                field[2] = "deskripsi_kategori";
                //Creating array for data
                String[] data = new String[3];
                data[0] = id_kategori;
                data[1] = nama_kategori;
                data[2] = deskripsi_kategori;
                PutData putData = new PutData(URL_UPDATE, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Berhasil")){
                            Toast.makeText(Ubah_Kategori_Activity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Ubah_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                            startActivity(intent);
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Ubah_Kategori_Activity.this, "Gagal Ubah Data",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Ubah_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}