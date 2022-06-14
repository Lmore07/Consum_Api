package com.example.consum_api

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun consumir_api(view:View){
        val textView = findViewById<TextView>(R.id.respuesta_api);
        try{
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://gorest.co.in/public/v1/users"

            // Request a string response from the provided URL.
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    textView.text = response["data"].toString()
                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                }
            )
            queue.add(jsonObjectRequest);
        }catch (e:Exception){
            textView.setText(e.message);
        }

    }
}