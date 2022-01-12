package com.luckyrafi13.miemadyang.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.luckyrafi13.miemadyang.Kategori.Daftar_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Detail_Kategori_Activity;
import com.luckyrafi13.miemadyang.Kategori.Form_Kategori_Activity;
import com.luckyrafi13.miemadyang.R;

public class Daftar_User_Activity extends AppCompatActivity {

    FloatingActionButton fab_TambahUser;
    CardView cv_User1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);

        fab_TambahUser = findViewById(R.id.fab_TambahUser);
        cv_User1 = findViewById(R.id.cv_User1);

        fab_TambahUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_User_Activity.this, Form_User_Activity.class);
                startActivity(intent);
            }
        });

        cv_User1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Daftar_User_Activity.this, Detail_User_Activity.class);
                startActivity(intent);
            }
        });
    }
}