package com.luckyrafi13.miemadyang.Kategori;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luckyrafi13.miemadyang.Menu.Adapter.DaftarMenu;
import com.luckyrafi13.miemadyang.Menu.Adapter.Daftar_Menu_Adapter;
import com.luckyrafi13.miemadyang.Menu.Form_Menu_Activity;
import com.luckyrafi13.miemadyang.R;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Detail_Kategori_Activity extends AppCompatActivity {

    FloatingActionButton fab_TambahMenuKategori;
    Button btn_UbahKategori,btn_HapusKategori;
    TextView tv_NamaKategori,tv_IdKategori,tv_DesrkipsiKategori;

    String id_kategori;
    public static final String URL_DELETE = "http://192.168.100.4:8080/surat/Surat/hapuskategori";

    //Tampil Daftar Menu
    public static final String URL_VIEW = "http://192.168.100.4:8080/surat/Surat/cek_daftar_menu";
    ListView listView;
    List<DaftarMenu> itemList = new ArrayList<DaftarMenu>();
    Daftar_Menu_Adapter daftarMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kategori);

        fab_TambahMenuKategori = findViewById(R.id.fab_TambahMenuKategori);
        btn_UbahKategori = findViewById(R.id.Btn_UbahMenu);
        btn_HapusKategori = findViewById(R.id.Btn_HapusMenu);
        tv_IdKategori = findViewById(R.id.tv_IDKategori);
        tv_NamaKategori = findViewById(R.id.tv_IdKategori);
        tv_DesrkipsiKategori = findViewById(R.id.tv_DeskripsiKategori);
        listView = findViewById(R.id.lv_DaftarMenu);

        Intent intent = getIntent();
        id_kategori = intent.getStringExtra("KEY_id_Kategori");
        tv_IdKategori.setText(id_kategori);
        tv_NamaKategori.setText(intent.getStringExtra("KEY_nama_Kategori"));
        tv_DesrkipsiKategori.setText(intent.getStringExtra("KEY_deskripsi_Kategori"));

        daftarMenuAdapter = new Daftar_Menu_Adapter(Detail_Kategori_Activity.this,itemList);
        listView.setAdapter(daftarMenuAdapter);
        tampilDaftarMenu();

        btn_UbahKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Kategori_Activity.this, Ubah_Kategori_Activity.class);
                intent.putExtra("KEY_id_Kategori",tv_IdKategori.getText().toString());
                intent.putExtra("KEY_nama_Kategori",tv_NamaKategori.getText().toString());
                intent.putExtra("KEY_deskripsi_Kategori",tv_DesrkipsiKategori.getText().toString());
                startActivity(intent);
            }
        });

        btn_HapusKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HapusKategori();
            }
        });

        fab_TambahMenuKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Kategori_Activity.this, Form_Menu_Activity.class);
                intent.putExtra("KEY_id_Kategori",tv_IdKategori.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void tampilDaftarMenu() {
        itemList.clear();
        daftarMenuAdapter.notifyDataSetChanged();
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id_kategori";
                //Creating array for data
                String[] data = new String[1];
                data[0] = id_kategori;
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

                                DaftarMenu item = new DaftarMenu();

                                item.setId_produk(object.getString("id_produk"));
                                item.setNama_produk(object.getString("nama_produk"));
                                item.setHarga_produk(object.getString("harga_produk"));
                                item.setStok(object.getString("stok"));
                                item.setTerjual(object.getString("terjual"));
                                item.setId_users(object.getString("id_users"));
                                item.setId_kategori(object.getString("id_kategori"));

                                //menambahkan item ke array
                                itemList.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //notifikasi adanya perubahan data pada adapter
                        daftarMenuAdapter.notifyDataSetChanged();
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Detail_Kategori_Activity.this, "Gagal Memuat Data",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Detail_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

    public void HapusKategori(){
        id_kategori = tv_IdKategori.getText().toString();
        //Start ProgressBar first (Set visibility VISIBLE)
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "id_kategori";
                //Creating array for data
                String[] data = new String[1];
                data[0] = id_kategori;
                PutData putData = new PutData(URL_DELETE, "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if (result.equals("Berhasil")){
                            Toast.makeText(Detail_Kategori_Activity.this, "Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Detail_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                            startActivity(intent);
                        }
                    }
                    if (putData.isInterrupted()) {
                        Toast.makeText(Detail_Kategori_Activity.this, "Gagal Hapus Data",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Detail_Kategori_Activity.this, Daftar_Kategori_Activity.class);
                        startActivity(intent);
                    }
                }
                //End Write and Read data with URL
            }
        });
    }

}