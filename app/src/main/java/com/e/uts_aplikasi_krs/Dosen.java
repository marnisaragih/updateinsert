package com.e.uts_aplikasi_krs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Dosen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        ImageButton btn = findViewById(R.id.imgdatadiriDosen);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dosen.this, Data_diri_Dosen.class);
                startActivity(i);

                ImageButton btn = findViewById(R.id.imgkrsDosen);
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Dosen.this, CRUD_KRS.class);
                        startActivity(i);

                        ImageButton btn = findViewById(R.id.imgkelasDosen);
                        btn.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(Dosen.this, Data_Kelas.class);
                                startActivity(i);
            }
        });

    }
})

            ;}
            });

        }
    };


