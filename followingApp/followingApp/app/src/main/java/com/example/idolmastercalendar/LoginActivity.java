package com.example.idolmastercalendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.idolmastercalendar.network.RemoteClient;
import com.example.idolmastercalendar.network.api.WimiApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ImageView loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        WimiApi wimiApi = RemoteClient.INSTANCE.createRetrofit(true).create(WimiApi.class);

        Call<ResponseBody> call = wimiApi.login("lani");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Timber.e(response.message());
                startActivity(new Intent(LoginActivity.this, ChooserActivity.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Timber.e(t.getLocalizedMessage());
            }
        });
    }

}
