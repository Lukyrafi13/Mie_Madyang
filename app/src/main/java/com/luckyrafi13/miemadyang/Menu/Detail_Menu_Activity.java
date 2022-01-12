package com.luckyrafi13.miemadyang.Menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_Menu_Activity extends AppCompatActivity {

    private Button btn_UbahMenu,btn_HapusMenu;
    private TextView tv_IdMenu,tv_NamaMenu, tv_IdKategori,tv_HargaJual,tv_Stok,tv_Terjual;
    private String IdMenu;

    public static final String URL_VIEW = "http://192.168.100.4:8080/surat/Surat/cek_detail_menu";
    public static final String URL_DELETE = "http://192.168.100.4:8080/surat/Surat/hapus_menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        tv_IdMenu = findViewById(R.id.tv_IdMenu);
        tv_NamaMenu = findViewById(R.id.tv_NamaMenu);
        tv_IdKategori = findViewById(R.id.tv_IdKategori);
        tv_HargaJual = findViewById(R.id.tv_HargaJual);
        tv_Stok = findViewById(R.id.tv_Stok);
        tv_Terjual = findViewById(R.id.tv_Terjual);
        btn_HapusMenu = findViewById(R.id.Btn_HapusMenu);
        btn_UbahMenu = findViewById(R.id.Btn_UbahMenu);

        Intent intent = getIntent();
        IdMenu = intent.getStringExtra("KEY_IdMenu");
        tampilDetailMenu();


        btn_UbahMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Detail_Menu_Activity.this,Ubah_Menu_Activity.class);
                intent1.putExtra("KEY_IdMenu",IdMenu);
                startActivity(intent1);
            }
        });

        btn_HapusMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HapusMenu();
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
                data[0] = IdMenu;
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


                                tv_IdMenu.setText(object.getString("id_produk"));
                                tv_IdKategori.setText(object.getString("id_kategori"));
                                tv_NamaMenu.setText(object.getString("nama_produk"));
                                tv_HargaJual.setText(object.getString("harga_produk"));
                                tv_Stok.setText(object.getString("stok"));
                                tv_Terjual.setText(object.getString("terjual"));

                                //menambahkan item ke array
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Detail_Menu_Activity.this, "Gagal Hapus Data",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Detail_Menu_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    public void HapusMenu(){
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
                data[0] = IdMenu;
                PutData putData = new PutData(URL_DELETE, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Berhasil")){
                            Toast.makeText(Detail_Menu_Activity.this, "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Detail_Menu_Activity.this, Daftar_Kategori_Activity.class);
                            startActivity(intent);
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Detail_Menu_Activity.this, "Gagal Hapus Data",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Detail_Menu_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }
}