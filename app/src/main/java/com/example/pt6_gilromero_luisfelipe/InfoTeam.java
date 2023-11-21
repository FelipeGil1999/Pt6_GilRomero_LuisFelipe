package com.example.pt6_gilromero_luisfelipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class InfoTeam extends AppCompatActivity {

    List<Team> team;

    ImageView shield;
    TextView titles;
    TextView stadium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_team);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recuperem l'extra de les dades anteriors
        Intent intent = getIntent();
        final String abbreviation = intent.getStringExtra("team_abbreviation").toLowerCase();
        final String extension = intent.getStringExtra("extension");

        //Creem URL
        String URL = "https://www.vidalibarraquer.net/android/sports/"+ extension + abbreviation + ".json";
        String URL2 = "https://www.vidalibarraquer.net/android/sports/"+ extension + abbreviation + ".png";
        System.out.println(URL);


        shield = (ImageView) findViewById(R.id.shield);
        titles = (TextView) findViewById(R.id.titles);
        stadium = (TextView) findViewById(R.id.stadium);


        ImageRequest imageRequest = new ImageRequest(URL2, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(final Bitmap response) {
                shield.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoTeam.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(InfoTeam.this);
        requestQueue.add(imageRequest);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}