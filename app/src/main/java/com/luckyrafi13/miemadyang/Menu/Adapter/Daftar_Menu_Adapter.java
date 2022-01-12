package com.luckyrafi13.miemadyang.Menu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.luckyrafi13.miemadyang.Menu.Adapter.DaftarMenu;
import com.luckyrafi13.miemadyang.Menu.Detail_Menu_Activity;
import com.luckyrafi13.miemadyang.R;

import java.util.List;

public class Daftar_Menu_Adapter extends BaseAdapter {

    Activity activity;
    List<DaftarMenu> items;

    private LayoutInflater inflater;

    public Daftar_Menu_Adapter(Activity activity, List<DaftarMenu> items) {
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

        if (view == null) view = inflater.inflate(R.layout.list_menu, null);

        TextView tv_NamaMenu, tv_HargaMenu, tv_IdMenu;
        CardView cv_DetailMenu;

        tv_NamaMenu = view.findViewById(R.id.tv_NamaMenu);
        tv_HargaMenu = view.findViewById(R.id.tv_HargaMenu);
        cv_DetailMenu = view.findViewById(R.id.cv_DetailMenu);
        tv_IdMenu = view.findViewById(R.id.tv_IdMenu);

        DaftarMenu daftarMenu = items.get(i);
        tv_NamaMenu.setText(daftarMenu.getNama_produk());
        tv_HargaMenu.setText(daftarMenu.getHarga_produk());
        tv_IdMenu.setText(daftarMenu.getId_produk());

        cv_DetailMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(), Detail_Menu_Activity.class);
                intent.putExtra("KEY_IdMenu",daftarMenu.getId_produk());
                activity.startActivity(intent);
            }
        });

        return view;

    }
}
