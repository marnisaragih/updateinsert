package com.e.uts_aplikasi_krs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        ImageButton btn = findViewById(R.id.buttonimgdaftardosen); //ketika di klik button daftar dosen maka akan pindah ke CRUD dosen
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Admin.this, CRUD_Dosen.class);
                startActivity(i);


                ImageButton btn = findViewById(R.id.buttonimgkrs); //ketika di klik button daftar dosen maka akan pindah ke CRUD KRS
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Admin.this, CRUD_KRS.class);
                        startActivity(i);

                        ImageButton btn = findViewById(R.id.buttonimgmatkul); //ketika di klik button daftar dosen maka akan pindah ke CRUD Matkul
                        btn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Admin.this, CRUD_Matkul.class);
                                startActivity(i);

                                ImageButton btn = findViewById(R.id.buttonimgMhs); //ketika di klik button daftar dosen maka akan pindah ke CRUD Mhs
                                btn.setOnClickListener(new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(Admin.this, CRUD_Mhs.class);
                                        startActivity(i);
                                    }
                                });

                            }
                        })

                        ;}
                });

            }
        };
