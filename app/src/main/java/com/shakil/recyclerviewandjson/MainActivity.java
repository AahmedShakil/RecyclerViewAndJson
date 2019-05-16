package com.shakil.recyclerviewandjson;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String data;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MyWeather>listItems;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();
        new MyAsyncTask().execute();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String LoadRecyclerViewData(String url)throws IOException {
        ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            progressDialog.dismiss();

            try (Response response = client.newCall(request).execute()) {
                return ((Response) response).body().string();
            }
    }
    public class MyAsyncTask extends AsyncTask{

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                 data=LoadRecyclerViewData("http://api.openweathermap.org/data/2.5/forecast/daily?APPID=c13159d2d9b7d01343afbc8acde7572b&q=Dhaka&mode=json&units=metric&cnt=16&fbclid=IwAR0V6nAS6Yozo_C1kEZAYSkSXxBVWv-1s9kN7R4Tj_H4VzZvbVuZYJOyA0w");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            MyWeather myWeather=new Gson().fromJson(data,MyWeather.class);
            recyclerView.setAdapter(new MyAdapter(listItems,getApplicationContext()));

            //Mybe problem
            recyclerView.setAdapter(adapter);
        }
    }
}
