package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.examen.Adaptadores.GanadorAdaptador;
import com.example.examen.Modelos.Data;
import com.example.examen.Modelos.Ganador;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Results extends AppCompatActivity {
    List<Ganador> g;
    List<Data> d;
    GanadorAdaptador adGanador;
    RecyclerView recycler;
    private RequestQueue nQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        nQueue = SingletonRequest.getInstance(Results.this).getRequestQueue();

        g = new ArrayList<>();
        d = new ArrayList<>();

        getData();
    }

    private void getData() {
        String url = "https://ramiro.uttics.com/api/ganadores";

        final JsonObjectRequest getGanadores = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("respuesta", response.toString());

                recycler = (RecyclerView) findViewById(R.id.recycler);
                recycler.setHasFixedSize(true);
                LinearLayoutManager linearManager = new LinearLayoutManager(getApplicationContext());
                recycler.setLayoutManager(linearManager);

                final Gson gson = new Gson();
                final Ganador ganador = gson.fromJson(response.toString(), Ganador.class);
                adGanador = new GanadorAdaptador(ganador.getData());
                recycler.setAdapter(adGanador);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("errorPeticion", error.toString());
            }
        });

        nQueue.add(getGanadores);
    }
}