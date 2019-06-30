package com.example.mobadi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class kayit_ol extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    EditText nameEt, surnameEt,usernameEt,emailEt,passEt,passctrEt;
    Button btn;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        auth = FirebaseAuth.getInstance();

        nameEt = findViewById(R.id.nameEt);
        surnameEt = findViewById(R.id.surnameEt);
        emailEt = findViewById(R.id.emailEt);
        passEt = findViewById(R.id.passEt);
        passctrEt = findViewById(R.id.passctrlEt);
        btn = findViewById(R.id.kayit);




        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEt.getText().toString();
                String surname = surnameEt.getText().toString();
                String email = emailEt.getText().toString().trim();
                String pass = passEt.getText().toString();
                String passctrl = passctrEt.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) ||  TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(passctrl)){
                    Toast.makeText(kayit_ol.this, "Alanlar boş bırakılarak kayıt işlemi yapılamaz.",Toast.LENGTH_LONG).show();
                }
        else if(!pass.equals(passctrl)){
                    Toast.makeText(kayit_ol.this, "Sifre tekrarı yanlış girildi.",Toast.LENGTH_LONG).show();
                }
                else if(pass.length() < 6){
                    Toast.makeText(kayit_ol.this, "Sifre 6 karakterden az olamaz.",Toast.LENGTH_LONG).show();
                }
                else{
                    register(name,surname,email,pass);
                }
            }
        });



    }
    private void register(final String name, final String surname,  final String email, final String pass){
        auth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(kayit_ol.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            final String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                            HashMap<String,Object> hashMap = new HashMap<>();
                            hashMap.put("id",userId);
                            hashMap.put("isim",name);
                            hashMap.put("soyad",surname);
                            hashMap.put("email",email);
                            hashMap.put("yetki","user");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        loginPrefsEditor.putBoolean("hatirla", true);
                                        loginPrefsEditor.putString("id", userId);
                                        loginPrefsEditor.putString("name", name);
                                        loginPrefsEditor.putString("email", email);
                                        loginPrefsEditor.putString("surname", surname);
                                        loginPrefsEditor.putString("yetki", "user");
                                        loginPrefsEditor.commit();

                                        Intent intent = new Intent(kayit_ol.this,Giris_yap.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(kayit_ol.this, "b�yle bir e-mail kay�tl� ya da e-mail d�zeninde hata bulunmaktad�r", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
