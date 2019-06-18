package com.example.mobadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UrunVeyaKategori extends Activity {

    Button urun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_veya_kategori);

        urun=(Button)findViewById(R.id.urun);
        urun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(UrunVeyaKategori.this,UrunEkle.class);
                startActivity(degis);
            }
        });
    }



}
