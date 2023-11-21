package com.example.pt6_gilromero_luisfelipe;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
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

public class InfoLigues extends AppCompatActivity {

    private final List<Ligue> elements = new ArrayList<>();
    private RequestQueue queue = null;

    public List<Ligue> getElements() {
        return elements;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_ligues);

        Intent intent = getIntent();
        String extension = intent.getStringExtra("extension");
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(elements);

        RecyclerView listTeams = findViewById(R.id.list_teams);
        listTeams.setAdapter(adapter);

        findViewById(R.id.load_teams).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hiHaConnexio())
                    loadData(listTeams, "https://www.vidalibarraquer.net/android/sports/" + extension +".json");
                else
                    Toast.makeText(getApplicationContext(), R.string.noInternet, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean hiHaConnexio() {
        boolean resultat = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // Comprovem la versió del dispositiu Android
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                    resultat = true;
                }
            }
        } else { //versions anteriors d'Android
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                resultat = true;
            } else {
                resultat = false;
            }
        }

        return resultat;
    }

    private void loadData(RecyclerView listTeams, String url) {
        if ( queue == null )
            queue = Volley.newRequestQueue(this);
        System.out.println(url);
        JsonObjectRequest request = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // S'esborra la llista
                            elements.clear();
                            // Obtenim l'array que té per nom data
                            JSONArray jsonArray = response.getJSONArray("teams");
                            //Recorrem tots els elements
                            for (int i = 0; i < jsonArray.length(); i++) {
                                // Afegim el personatge a la llista
                                Ligue ligue = new Ligue(
                                        jsonArray.getJSONObject(i).getInt("team_id"),
                                        jsonArray.getJSONObject(i).getString("team_name"),
                                        jsonArray.getJSONObject(i).getString("team_abbreviation")

                                );
                                elements.add(ligue);
                                System.out.println(ligue.getTeam_name() +" " +ligue.getTeam_abbreviation());
                            }
                            listTeams.getAdapter().notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listTeams.getAdapter().notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InfoLigues.this, "Error en obtenir dades", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }
}