package com.e.uts_aplikasi_krs;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

public class Login extends AppCompatActivity {
 SharedPrefManager sharedPrefManager;
    @BindView(R.id.btnLogout)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_log = findViewById(R.id.btnLogin);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Admin.class);
                startActivity(intent);

            }
        });
        Button btnLogout;

        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(Login.this, Login.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(myBtnLoginClick);
        if (statusLogin != null) {
            button3.setText("Logout");
        } else {
            button3.setText("Login");
        }
        sharedPrefManager = new SharedPrefManager(this);
    }

    private final View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = Login.this.getSharedPreferences("prefs_file", MODE_PRIVATE);
            String statusLogin = prefs.getString("isLogin", null);
            SharedPreferences.Editor edit = prefs.edit();

            Button btnLogin = findViewById(R.id.button3);
            if (statusLogin != null) {
                edit.putString("isLogin", null);
                btnLogin.setText("Login");
            } else {
                edit.putString("isLogin", "Admin");
                btnLogin.setText("Logout");
            }
            edit.commit();

        }

        private void requestLogin() {
            mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                loading.dismiss();
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("error").equals("false")) {
                                        // Jika login berhasil maka data nama yang ada di response API
                                        // akan diparsing ke activity selanjutnya.
                                        Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                        String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                        // Shared Pref ini berfungsi untuk menjadi trigger session login
                                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                        startActivity(new Intent(mContext, MainActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                        finish();
                                    } else {
                                        // Jika login gagal
                                        String error_message = jsonRESULTS.getString("error_msg");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                loading.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("debug", "onFailure: ERROR > " + t.toString());
                            loading.dismiss();
                        }
                    });

        }