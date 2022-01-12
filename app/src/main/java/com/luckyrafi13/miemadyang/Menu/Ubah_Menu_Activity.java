package com.luckyrafi13.miemadyang.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Ubah_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ubah_Menu_Activity extends AppCompatActivity {

    private String idMenu, namaMenu, hargaJual;

    private EditText et_IdMenu, et_IdKategori, et_NamaMenu, et_HargaJual, et_Stok;
    private Button btn_SimpanMenu;

    public static final String URL_VIEW = "http://192.168.100.4:8080/surat/Surat/cek_detail_menu";
    public static final String URL_UPDATE = "http://192.168.100.4:8080/surat/Surat/ubah_menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_menu);

        et_IdMenu = findViewById(R.id.et_IdMenu);
        et_IdKategori = findViewById(R.id.et_IdKategori);
        et_NamaMenu = findViewById(R.id.et_NamaMenu);
        et_HargaJual = findViewById(R.id.et_HargaJual);
        btn_SimpanMenu = findViewById(R.id.btn_SimpanMenu);

        Intent intent = getIntent();
        idMenu = intent.getStringExtra("KEY_IdMenu");

        tampilDetailMenu();

        btn_SimpanMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateMenu();
            }
        });

    }

    private void tampilDetailMenu() {
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id_produk";
                //Creating array for data
                String[] data = new String[1];
                data[0] = idMenu;
                PutData putData = new PutData(URL_VIEW, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();

                        JSONArray jsonArray_result = null;
                        try {
                            jsonArray_result = new JSONArray(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArray_result.length(); i++) {
                            try {
                                JSONObject object = jsonArray_result.getJSONObject(i);


                                et_IdMenu.setText(object.getString("id_produk"));
                                et_IdKategori.setText(object.getString("id_kategori"));
                                et_NamaMenu.setText(object.getString("nama_produk"));
                                et_HargaJual.setText(object.getString("harga_produk"));

                                //menambahkan item ke array
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Ubah_Menu_Activity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Ubah_Menu_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    private void UpdateMenu() {
        idMenu = et_IdMenu.getText().toString();
        namaMenu = et_NamaMenu.getText().toString();
        hargaJual = et_HargaJual.getText().toString();
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[3];
                field[0] = "id_produk";
                field[1] = "nama_produk";
                field[2] = "harga_produk";
                //Creating array for data
                String[] data = new String[3];
                data[0] = idMenu;
                data[1] = namaMenu;
                data[2] = hargaJual;
                PutData putData = new PutData(URL_UPDATE, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Berhasil")) {
                            Toast.makeText(Ubah_Menu_Activity.this, "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Ubah_Menu_Activity.this, Daftar_Kategori_Activity.class);
                            startActivity(intent);
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Ubah_Menu_Activity.this, "Gagal Ubah Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Ubah_Menu_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}