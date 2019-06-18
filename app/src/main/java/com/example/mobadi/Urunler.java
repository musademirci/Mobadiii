package com.example.mobadi;

import com.google.firebase.database.DatabaseReference;

public class Urunler {

    private String  Adi, Fiyat,UrunKategori;


    public Urunler(String adi, String fiyat, String urunKategori) {
        Adi = adi;
        Fiyat = fiyat;
        UrunKategori = urunKategori;
    }

    public Urunler(String toString, String toString1) {

    }

    public String getAdi() {
        return Adi;
    }

    public void setAdi(String adi) {
        Adi = adi;
    }

    public String getFiyat() {
        return Fiyat;
    }

    public void setFiyat(String fiyat) {
        Fiyat = fiyat;
    }

    public String getUrunKategori() {
        return UrunKategori;
    }

    public void setUrunKategori(String urunKategori) {
        UrunKategori = urunKategori;
    }
}
