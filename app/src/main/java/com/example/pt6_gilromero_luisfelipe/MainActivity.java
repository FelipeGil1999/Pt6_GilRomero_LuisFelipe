package com.example.pt6_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView mlb;
    ImageView mls;
    ImageView nba;
    ImageView nfl;
    ImageView nhl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mlb = findViewById(R.id.mlb);
        mls = findViewById(R.id.mls);
        nba = findViewById(R.id.nba);
        nfl = findViewById(R.id.nfl);
        nhl = findViewById(R.id.nhl);

        View.OnClickListener listener = this;
        mlb.setOnClickListener(listener);
        mls.setOnClickListener(listener);
        nba.setOnClickListener(listener);
        nfl.setOnClickListener(listener);
        nhl.setOnClickListener(listener);


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.mlb){
            Intent intent = new Intent(this, InfoLigues.class);
            intent.putExtra("extension", "mlb");
            startActivity(intent);
        }   else if (view.getId()==R.id.mls){
            Intent intent = new Intent(this, InfoLigues.class);
            intent.putExtra("extension", "mls");
            startActivity(intent);
        }   else if (view.getId()==R.id.nba){
            Intent intent = new Intent(this, InfoLigues.class);
            intent.putExtra("extension", "nba");
            startActivity(intent);
        }   else if (view.getId()==R.id.nfl){
            Intent intent = new Intent(this, InfoLigues.class);
            intent.putExtra("extension", "nfl");
            startActivity(intent);
        }   else if (view.getId()==R.id.nhl){
            Intent intent = new Intent(this, InfoLigues.class);
            intent.putExtra("extension", "nhl");
            startActivity(intent);
        }

    }
    }
