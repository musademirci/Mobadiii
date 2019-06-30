package com.example.mobadi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobadi.listeleme.Masalistele;
import com.example.mobadi.listeleme.Siparis_list;

public class Anasayfa extends Activity {

    Button urun;
    Button masa,listele,siparis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anasayfa);

        listele =(Button)findViewById(R.id.b1);

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Masalistele.class);
                startActivity(intocan);
            }
        });

        siparis =(Button)findViewById(R.id.b6);
        siparis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siparis = new Intent(Anasayfa.this, Siparis_list.class);
                startActivity(siparis);
            }
        });

        urun=(Button)findViewById(R.id.b5);
        urun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Anasayfa.this,UrunEkle.class);
                startActivity(degis);
            }
        });

        masa=(Button)findViewById(R.id.b4);
        masa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Anasayfa.this,Masaekle.class);
                //
                startActivity(degis);
            }
        });
        Button removeIv = findViewById(R.id.b5);
        Button removeIv2 = findViewById(R.id.b4);


        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String kayit = preferences.getString("yetki", "");

        if("admin".equals(kayit)){
            removeIv.setVisibility(Button.VISIBLE);
        }
        if("admin".equals(kayit)){
            removeIv2.setVisibility(Button.VISIBLE);
        }

    }

}
