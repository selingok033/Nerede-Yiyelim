package com.example.merve.neredeyiyelim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by Merve on 25.08.2017.
 */

public class CustomAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList<Menuler> menulerList;
    FirebaseUser fUser;

    public CustomAdapter(Activity activity, ArrayList<Menuler> menulerList, FirebaseUser fUser) {
        layoutInflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.menulerList = menulerList;
        this.fUser = fUser;
    }

    @Override
    public int getCount() {
        return menulerList.size();
    }

    @Override
    public Object getItem(int position) {
        return menulerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        Menuler menuler = menulerList.get(position);
        View satir=layoutInflater.inflate(R.layout.custom_satir,null);

        TextView tvadi= (TextView) satir.findViewById(R.id.textViewAdi);
        tvadi.setText(menuler.getMenuAdi().toString().toUpperCase());
        TextView tvfiyat = (TextView) satir.findViewById(R.id.textViewFiyati);
        tvfiyat.setText(menuler.getFiyat()+" ₺");

        return satir;
    }
}
