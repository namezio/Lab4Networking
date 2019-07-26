package com.namezio.lab4_networking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);

        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl("http://asian.dotplays.com/").
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final JsonPlaceHolderCategoryApi categoryApi = retrofit.create(JsonPlaceHolderCategoryApi.class);
        Call<List<Category>> call = categoryApi.getPost();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(!response.isSuccessful()){
                    tvResult.setText(response.code());
                    return;
                }
                List<Category>list = response.body();
                String s = "";
                for (Category category : list){
                    s += "Name : " + category.getName() + "\n";
                    s += "Id : " + category.getId() + "\n\n";
                    Log.e("name" ,category.getName());
                    tvResult.setText(s);
                }

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
        tvResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent);
            }
        });
    }
}
