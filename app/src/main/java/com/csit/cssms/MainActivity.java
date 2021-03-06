package com.csit.cssms;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String app_server_url= "http://crystalgrade.com/cssms/sms_insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendBtn(View view){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(
                R.string.FCM_PREF), Context.MODE_PRIVATE);
       final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN),"");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("fcm_token",token);

                return params;
            }
        };
        MySingleton.getmInstance(MainActivity.this).addRequestQue((stringRequest));
    }
}
