package com.luckyrafi13.miemadyang.Kategori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luckyrafi13.miemadyang.Kategori.Adapter.DaftarKategori;
import com.luckyrafi13.miemadyang.Kategori.Adapter.Daftar_Kategori_Adapter;
import com.luckyrafi13.miemadyang.R;
import com.vishnusivadas.advanced_httpurlconnection.FetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Daftar_Kategori_Activity extends AppCompatActivity {

    FloatingActionButton fab_TambahKategori;

    public static final String URL_VIEW = "http://192.168.100.4:8080/surat/Surat/cek_daftar_kategori";
    ListView listView;
    List<DaftarKategori> itemList = new ArrayList<DaftarKategori>();
    Daftar_Kategori_Adapter daftar_kategori_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kategori);

        listView = findViewById(R.id.lv_DaftarKategori);
        fab_TambahKategori = findViewById(R.id.fab_TambahKategori);


        daftar_kategori_adapter = new Daftar_Kategori_Adapter(Daftar_Kategori_Activity.this, itemList);
        listView.setAdapter(daftar_kategori_adapter);
        tampilDaftarKategori();


        fab_TambahKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_Kategori_Activity.this, Form_Kategori_Activity.class);
                startActivity(intent);
            }
        });
        

    }

    private void tampilDaftarKategori() {
        itemList.clear();
        daftar_kategori_adapter.notifyDataSetChanged();
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                FetchData fetchData = new FetchData(URL_VIEW);
                if (fetchData.startFetch()) {
                    if (fetchData.onComplete()) {
                        String result = fetchData.getResult();
                        //End ProgressBar (Set visibility to GONE)

                        JSONArray jsonArray_result = null;
                        try {
                            jsonArray_result = new JSONArray(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < jsonArray_result.length(); i++) {
                            try {
                                JSONObject object = jsonArray_result.getJSONObject(i);

                                DaftarKategori item = new DaftarKategori();

                                item.setId_kategori(object.getString("id_kategori"));
                                item.setDeskripsi_kategori(object.getString("deskripsi_kategori"));
                                item.setNama_kategori(object.getString("nama_kategori"));

                                //menambahkan item ke array
                                itemList.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //notifikasi adanya perubahan data pada adapter
                        daftar_kategori_adapter.notifyDataSetChanged();

                    }
                    if (FetchData.interrupted()) {
                        Toast.makeText(Daftar_Kategori_Activity.this, "Gagal Memuat Data", Toast.LENGTH_SHORT).show();
                    }
                }
                //End Write and Read data with URL
            }
        });
    }


}