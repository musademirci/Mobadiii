package com.example.mobadi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobadi.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Giris_yap extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    EditText emailEt, passEt;
    String id;
    Button giris,kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        emailEt = findViewById(R.id.mailet);
        passEt = findViewById(R.id.sifreet);
        giris=findViewById(R.id.bgiris);
        kayit=findViewById(R.id.gkayit);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Giris_yap.this, kayit_ol.class);
                Giris_yap.this.finish();
                startActivity(intent);
            }
        });

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEt.getText().toString();
                String pass = passEt.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
                    Toast.makeText(Giris_yap.this, "Alanlar bos bırakılamaz.",Toast.LENGTH_LONG).show();
                }
                else{
                    if("admin".equals(email)){
                        Intent intent = new Intent(Giris_yap.this, Anasayfa.class);
                        Giris_yap.this.finish();
                        startActivity(intent);
                    }
                    else {
                        login(email,pass);
                    }
                }
            }
        });
    }

    private void login(final String email ,final String pass){
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(Giris_yap.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            id = auth.getCurrentUser().getUid();

                            DatabaseReference newReference = firebaseDatabase.getReference("Users").child(id);
                            ValueEventListener listener = new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user;
                                    user = dataSnapshot.getValue(User.class);

                                    loginPrefsEditor.putBoolean("hatirla", true);
                                    loginPrefsEditor.putString("id", user.getId());
                                    loginPrefsEditor.putString("name", user.getAdi());
                                    loginPrefsEditor.putString("email", email);
                                    loginPrefsEditor.putString("surname", user.getEposta());
                                    loginPrefsEditor.putString("yetki", user.getYetki());
                                    loginPrefsEditor.commit();


                                    if("user".equals(user.getYetki())){
                                        Intent intent = new Intent(Giris_yap.this,Anasayfa.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                    else if("admin".equals(user.getYetki())){
                                        Intent intent = new Intent(Giris_yap.this,Anasayfa.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(Giris_yap.this,"hatali yetki",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };
                            newReference.addValueEventListener(listener);
                        }
                        else {
                            Toast.makeText(Giris_yap.this,"giri� yap�lamad�. Bilgilerinizi kontrol edip tekrar deneyeniz", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
