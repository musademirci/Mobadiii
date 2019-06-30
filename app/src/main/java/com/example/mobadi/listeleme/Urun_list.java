package com.example.mobadi.listeleme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mobadi.Model.Model;
import com.example.mobadi.R;
import com.example.mobadi.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.UUID;

public class Urun_list extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    FirebaseDatabase database =FirebaseDatabase.getInstance();
    DatabaseReference myref = database.getReference();

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori_list);




        mrecyclerView=findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Menu");
        String urun = getIntent().getStringExtra("urunKategori");
        firebaseSearch(urun);

    }


    private void firebaseSearch(String src){
        Query firebaseSerachQuery = mRef.orderByChild("urunKategori").startAt(src).endAt(src+"\uf8ff");

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.urun_list,
                        ViewHolder.class,
                        firebaseSerachQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetailss(getApplicationContext(),model.getAdi(),model.getFiyat());



                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                                UUID uuid1 = UUID.randomUUID();
                                String randomid = uuid1.toString();

                                preferences = getSharedPreferences("masa",MODE_PRIVATE);
                                String masaadi = preferences.getString("masaadi","");

                                TextView urun = findViewById(R.id.rTitleTv);
                                TextView fiyat = findViewById(R.id.rUrunFiyat);


                                myref.child("Siparişler").child(masaadi).child(randomid).child("urunadi").setValue(urun.getText().toString());
                                myref.child("Siparişler").child(masaadi).child(randomid).child("fiyat").setValue(fiyat.getText().toString());
                                Toast.makeText(Urun_list.this,urun.getText().toString(),Toast.LENGTH_SHORT).show();

                        }

                            @Override
                            public void onItemLongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };
        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
