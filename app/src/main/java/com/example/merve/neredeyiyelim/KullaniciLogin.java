package com.example.merve.neredeyiyelim;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KullaniciLogin extends AppCompatActivity {

    EditText etKmail,etKsifre;
    Button btnGiris;
    TextView tvyazi,tvcafeGiris;

    FirebaseAuth mAuth;
    String username,password;

FirebaseDatabase database;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        etKmail = (EditText) findViewById(R.id.editTextKullaniciMail);
        etKsifre = (EditText) findViewById(R.id.editTextKullaniciSifre);
        btnGiris = (Button) findViewById(R.id.buttonKullaniciGiris);
        tvyazi = (TextView) findViewById(R.id.textViewUseryazi);
        tvcafeGiris = (TextView) findViewById(R.id.textViewCafeGirisi);


        tvcafeGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CafeGiris.class);
                startActivity(i);
            }
        });



        tvyazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KayitOlUser.class);
                startActivity(i);
            }
        });
database=FirebaseDatabase.getInstance();
        dbRef=database.getReference("roles");
        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etKmail.getText().toString();
                password = etKsifre.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
dbRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String role=dataSnapshot.getValue().toString();
        if(role.equals("User")){
            Intent i = new Intent(getApplicationContext(),UserAnasayfa.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(),"Bu kullanıcı hesabı değil,lütfen cafe girişini deneyiniz!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});


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
