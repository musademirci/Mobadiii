package com.example.mobadi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.mobadi.Model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Urun_list extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    Button siparisekle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori_list);

        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference();



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

        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.urun_list,
                        ViewHolder.class,
                        firebaseSerachQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getAdi());
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {



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
