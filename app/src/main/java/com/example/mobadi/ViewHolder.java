package com.example.mobadi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
        itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mclickListener.onItemClick(view,getAdapterPosition());
            }
        }));
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mclickListener.onItemLongClick(view ,getAdapterPosition());
                return true;
            }
        });


    }
    public void setDetails(Context ctx,String title){
        TextView mTitleView = mView.findViewById(R.id.rTitleTv);


        mTitleView.setText(title);
    }

    public void setDetailss(Context ctx,String title,String fiyat){
        TextView mTitleView = mView.findViewById(R.id.rTitleTv);
        mTitleView.setText(title);
        TextView mTitleView2 = mView.findViewById(R.id.rUrunFiyat);
        mTitleView2.setText(fiyat);
    }
    public void setDetailsss(Context ctx,String title,String fiyat){
        TextView mTitleView = mView.findViewById(R.id.rTitleTv);
        mTitleView.setText(title);
        TextView mTitleView2 = mView.findViewById(R.id.rUrunFiyat);
        mTitleView2.setText(fiyat);
    }


    private  ViewHolder.ClickListener mclickListener;

    public  interface  ClickListener{
        void  onItemClick(View view,int position);
        void  onItemLongClick(View view,int position);
    }

    public  void  setOnClickListener(ViewHolder.ClickListener clickListener){
        mclickListener = clickListener;
    }
}
