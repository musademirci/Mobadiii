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

public class Masaekle extends Activity {

    Button anasayfa;
    Button anasayfaa,masaekle,masalistele;


    EditText masaadi;

    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference();


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masaekle);

        masaadi =(EditText)findViewById(R.id.masaadi);
        masaekle =(Button) findViewById(R.id.btnmasaekle);
        masalistele =(Button) findViewById(R.id.btnmasalistele);

        masalistele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Masaekle.this,Masalistele.class);
                startActivity(degis);
            }
        });





        masaekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adi=masaadi.getText().toString();

                if (TextUtils.isEmpty(adi)){
                    Toast.makeText(Masaekle.this,"Alanlar boş bırakılamaz",Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Masalar");
                    dbref.push().setValue(
                            new Masalar(
                                    masaadi.getText().toString().toLowerCase()

                            )
                    );
                }


                finish();
                startActivity(getIntent());

            }
        });




        // anasayfaya dön buton
        anasayfa=(Button) findViewById(R.id.banner);
        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Masaekle.this,Anasayfa.class);
                startActivity(degis);
            }
        });

        anasayfaa=(Button) findViewById(R.id.banner2);
        anasayfaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent degis = new Intent(Masaekle.this,Anasayfa.class);
                startActivity(degis);
            }
        });
    }

}