package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.examen.Adaptadores.GanadorAdaptador;
import com.example.examen.Modelos.Ganador;
import com.example.examen.Modelos.Numero;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RequestQueue nQueue;
    Button btnNumero, btnEnviar, btnResultados, btnReiniciar;
    TextView txtNumero, txtSuma, txtTotal;
    int valor, acumSuma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumero = (Button) findViewById(R.id.btnNumero);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnResultados = (Button) findViewById(R.id.btnResultados);
        btnReiniciar = (Button) findViewById(R.id.btnReiniciar);
        txtNumero = (TextView) findViewById(R.id.txtNumero);
        txtSuma = (TextView) findViewById(R.id.txtSuma);
        txtTotal = (TextView) findViewById(R.id.txtTotal);
        nQueue = SingletonRequest.getInstance(MainActivity.this).getRequestQueue();

        btnNumero.setOnClickListener(this);
        btnResultados.setOnClickListener(this);
        btnReiniciar.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);
    }

    private void getNumero() {
        String url="https://ramiro.uttics.com/api/numero";
        final JsonObjectRequest obtenerNumero = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("respuesta", response.toString());
                final Gson gson = new Gson();
                final Numero numero = gson.fromJson(response.toString(), Numero.class);
                valor = numero.getNumero();
                acumSuma += valor;
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("errorPeticion", error.toString());
            }
        });

        nQueue.add(obtenerNumero);
    }

    private void sendResultado() {
        String url="";
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("resultado", acumSuma);
        }

        catch (JSONException e) {

        }

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNumero) {
            getNumero();
            txtNumero.setText("" + valor);
            txtSuma.setText("" + acumSuma);

            if (acumSuma > 21) {
                txtTotal.setText("Perdiste");
                txtSuma.setText(null);
            }
        }

        if (view.getId() == R.id.btnResultados) {
            startActivity(new Intent(this, Results.class));
        }

        if (view.getId() == R.id.btnReiniciar) {
            finish();
            startActivity(getIntent());
        }
    }
}
