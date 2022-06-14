package com.example.consum_api

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
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
        var rpta:String;
        try{
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)
            val url = "https://gorest.co.in/public/v1/users"

            // Request a string response from the provided URL.
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    rpta=response.getString("data")
                    Log.i("","")
                    var lstususarios = ""
                    val JSONlista = JSONArray(rpta)
                    for (i in 0 until JSONlista.length()) {
                        val usuario: JSONObject = JSONlista.getJSONObject(i)
                        lstususarios = lstususarios +
                        "\n"+"id: "+usuario.getString("id")+
                        "; name: "+usuario.getString("name")+
                        "; email: "+usuario.getString("email")+
                        "; gender: "+usuario.getString("gender")+
                        "; status: "+usuario.getString("status") +"\n"
                        }

                    textView.setText(lstususarios)

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