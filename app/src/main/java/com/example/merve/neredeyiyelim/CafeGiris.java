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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CafeGiris extends AppCompatActivity {

    EditText etCuser,etCpass;
    Button btngiris;
    TextView tvCyazi;

    FirebaseAuth mAuth;
    String mail,sifre;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_giris);



        etCuser = (EditText) findViewById(R.id.editTextCafeUser);
        etCpass = (EditText) findViewById(R.id.editTextCafePass);
        btngiris = (Button) findViewById(R.id.buttonCafeGiris);
        tvCyazi = (TextView) findViewById(R.id.textViewCafeYazi);
        tvCyazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),KayitOlCafe.class);
                startActivity(i);
            }
        });

        database=FirebaseDatabase.getInstance();
        dbRef=database.getReference("roles");
        btngiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mail = etCuser.getText().toString();
                sifre = etCpass.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(mail,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dbRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String role=dataSnapshot.getValue().toString();
                                    if(role.equals("Cafe")){
                                        Intent i = new Intent(getApplicationContext(),CafeAnaSayfa.class);
                                        startActivity(i);
                                    }
                                    else{


                                        Toast.makeText(getApplicationContext(),"Bu cafe hesabı değil,lütfen kullanıcı girişini deneyiniz!",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            })   ;


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
