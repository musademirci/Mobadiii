package com.example.mobadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Anasayfa extends Activity {

    Button urun;
    Button masa,listele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anasayfa);
        listele =(Button)findViewById(R.id.b1);

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intocan = new Intent(Anasayfa.this, Kategori_list.class);
                startActivity(intocan);
            }
        });


        urun=(Button)findViewById(R.id.b5);
        urun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Anasayfa.this,UrunVeyaKategori.class);
                startActivity(degis);
            }
        });

        masa=(Button)findViewById(R.id.b4);
        masa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Anasayfa.this,Masaekle.class);
                startActivity(degis);
            }
        });


    }


}
