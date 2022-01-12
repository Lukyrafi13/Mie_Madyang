package com.luckyrafi13.miemadyang.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luckyrafi13.miemadyang.R;

import java.text.Normalizer;

public class Detail_User_Activity extends AppCompatActivity {

    Button Btn_UbahDataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Btn_UbahDataUser = findViewById(R.id.Btn_UbahDataUser);

        Btn_UbahDataUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_User_Activity.this, Form_User_Activity.class);
                startActivity(intent);
            }
        });
    }
}