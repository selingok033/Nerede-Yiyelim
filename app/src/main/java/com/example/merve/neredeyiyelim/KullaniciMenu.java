package com.example.merve.neredeyiyelim;

/**
 * Created by Merve on 30.08.2017.
 */

public class KullaniciMenu {

    private String menu,cafeAdi,adres;
    private int fiyat;

    public KullaniciMenu(){}

    public KullaniciMenu(String menu, String cafeAdi, int fiyat, String adres) {
        this.menu = menu;
        this.cafeAdi = cafeAdi;
        this.fiyat = fiyat;
        this.adres = adres;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getCafeAdi() {
        return cafeAdi;
    }

    public void setCafeAdi(String cafeAdi) {
        this.cafeAdi = cafeAdi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
