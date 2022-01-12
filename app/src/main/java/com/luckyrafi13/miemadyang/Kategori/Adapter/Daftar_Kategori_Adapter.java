package com.luckyrafi13.miemadyang.Kategori.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Detail_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;

import java.util.List;

public class Daftar_Kategori_Adapter extends BaseAdapter {

    Activity activity;
    List<DaftarKategori> items;

    private LayoutInflater inflater;

    public Daftar_Kategori_Adapter(Activity activity, List<DaftarKategori> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) view = inflater.inflate(R.layout.list_daftar_kategori, null);

        TextView tv_IDKategori, tv_Kategori;
        CardView cv_Kategori;

        tv_IDKategori = view.findViewById(R.id.tv_IDKategori);
        tv_Kategori = view.findViewById(R.id.tv_Kategori);
        cv_Kategori = view.findViewById(R.id.cv_Kategori);

        DaftarKategori daftarKategori = items.get(i);
        tv_IDKategori.setText(daftarKategori.getId_kategori());
        tv_Kategori.setText(daftarKategori.getNama_kategori());

        cv_Kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), Detail_Kategori_Activity.class);
                intent.putExtra("KEY_id_Kategori",daftarKategori.getId_kategori());
                intent.putExtra("KEY_nama_Kategori",daftarKategori.getNama_kategori());
                intent.putExtra("KEY_deskripsi_Kategori",daftarKategori.getDeskripsi_kategori());
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
