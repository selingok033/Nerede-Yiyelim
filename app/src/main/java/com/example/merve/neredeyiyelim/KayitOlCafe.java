package com.example.merve.neredeyiyelim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KayitOlCafe extends AppCompatActivity {

    EditText etCafeAdi,etCafeMail,etCafeSifre,etCafeAdres;
    Button btnOlustur;


    FirebaseAuth mAuth;
    String cafeismi,mail,sifre,adres;

    FirebaseDatabase database;
    DatabaseReference dbRef;
    DatabaseReference cafelerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol_cafe);


        etCafeAdi= (EditText) findViewById(R.id.editTextCafeAdi);
        etCafeMail = (EditText) findViewById(R.id.editTextMail);
        etCafeSifre = (EditText) findViewById(R.id.editTextSifre);
        etCafeAdres = (EditText) findViewById(R.id.editTextAdres);
        btnOlustur= (Button) findViewById(R.id.buttonKayitOl);
        database=FirebaseDatabase.getInstance();
        dbRef=database.getReference("roles");
cafelerRef=database.getReference("cafeler");

        btnOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cafeismi =etCafeAdi.getText().toString();
                mail = etCafeMail.getText().toString();
                sifre = etCafeSifre.getText().toString();
                adres = etCafeAdres.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(mail,sifre).addOnCompleteListener(KayitOlCafe.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //db'de roles altında bu cafenin id'sine cafe rolü atadık
                            dbRef.child(mAuth.getCurrentUser().getUid()).setValue("Cafe");//Cafe rolü atadık.
                            //db'de bu cafenin adını ve adresini kaydettik.
                            cafelerRef.child(mAuth.getCurrentUser().getUid()).child("ad").setValue(cafeismi);
                            cafelerRef.child(mAuth.getCurrentUser().getUid()).child("adres").setValue(adres);

                            Intent i = new Intent(getApplicationContext(),CafeGiris.class);
                            startActivity(i);
                            finish();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }
}

