package com.example.mobadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UrunEkle extends Activity {

    Button anasayfa;
    Button anasayfaa;


    Button btn;
    EditText urunadi;
    EditText urunfiyat;
    EditText urunkategori;

    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference();



    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        btn =(Button)findViewById(R.id.urunekle);
        urunadi =(EditText)findViewById(R.id.urunadi);
        urunfiyat = (EditText)findViewById(R.id.urunfiyat);
        urunkategori =(EditText)findViewById(R.id.eturunkategori);




            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String adi=urunadi.getText().toString();
                    String fiyat= urunfiyat.getText().toString();
                    String kategori = urunkategori.getText().toString();
                    if (TextUtils.isEmpty(adi)||TextUtils.isEmpty(fiyat)||TextUtils.isEmpty(kategori)){
                        Toast.makeText(UrunEkle.this,"Alanlar boş bırakılamaz",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Menu");
                        dbref.push().setValue(
                                new Urunler(
                                        urunadi.getText().toString(),
                                        urunfiyat.getText().toString(),
                                        urunkategori.getText().toString().toLowerCase()

                                )
                        );
                    }


                    finish();
                    startActivity(getIntent());

                }
            });





        anasayfa=(Button) findViewById(R.id.banner);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(UrunEkle.this,Anasayfa.class);
                startActivity(degis);
            }
        });

        anasayfaa=(Button) findViewById(R.id.banner2);
        anasayfaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(UrunEkle.this,Anasayfa.class);
                startActivity(degis);
            }
        });
    }

}