package com.example.merve.neredeyiyelim;

/**
 * Created by Merve on 25.08.2017.
 */

public class Menuler {

    private String menuAdi;
    private int fiyat;

    public Menuler(){}

    public Menuler(String menuAdi, int fiyat) {
        this.menuAdi = menuAdi;
        this.fiyat = fiyat;
    }

    public String getMenuAdi() {
        return menuAdi;
    }

    public void setMenuAdi(String menuAdi) {
        this.menuAdi = menuAdi;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
