package com.e.uts_aplikasi_krs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    DataDosenService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = RetrofitClient.getRetrofitInstance()
                .create(DataDosenService.class);
        getAllDosen();
        insertDosen();

    }


    private void getAllDosen() {
        Call<List<DataDosen>> call = service.getDosenAll("1");
        call.enqueue(new Callback<List<DataDosen>>() {
            @Override
            public void onResponse(Call<List<DataDosen>> call, Response<List<DataDosen>> response) {
                for (DataDosen dataDosen : response.body()) {
                    // System.out.println("Nama : "+dataDosen.getNama());
                    // System.out.println("Nidn : "+dataDosen.getNidn());

                }
            }

            @Override
            public void onFailure(Call<List<DataDosen>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something wrong.....",
                        Toast.LENGTH_LONG).show();
                System.out.println(t.getMessage());
            }
        });
    }

    private void insertDosen() {
        Call<DefaultResult> call = service.insertDosen("dendy", "001",
                "jogja", "dendy@gmail.com", "skomeng", "dendy.jpg",
                "1");
        call.enqueue(new Callback<DefaultResult>() {
            @Override
            public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                System.out.println(response.body().getStatus());
            }

            @Override
            public void onFailure(Call<DefaultResult> call, Throwable t) {
                System.out.println("message:" + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong... please try later",
                        Toast.LENGTH_SHORT).show();
            }

            private void updateDosen() {
                Call<DefaultResult> call = service.updateDosen("777222", "dendy",
                        "001", "jogja", "dendy@gmail.com",
                        "dendy.jpg", "001");
                call.enqueue(new Callback<DefaultResult>() {
                    @Override
                    public void onResponse(Call<DefaultResult> call, Response<DefaultResult> response) {
                        System.out.println(response.body().getStatus());
                    }

                    @Override
                    public void onFailure(Call<DefaultResult> call, Throwable t) {
                        System.out.println("message:" + t.getMessage());
                        Toast.makeText(MainActivity.this, "sucsessful update",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
