package com.namezio.lab4_networking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostActivity extends AppCompatActivity {
    private TextView tvPost;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        tvPost = findViewById(R.id.tvPost);

        queue = Volley.newRequestQueue(this);
        jsonPaser();
    }

    private void jsonPaser() {
        String url = "http://asian.dotplays.com/wp-json/wp/v2/posts?category=18&per_page=5&paging=1";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.e("code",String.valueOf(response.length()));
                        JSONObject object = response.getJSONObject(i);
                        int id = object.getInt("id");
                        JSONObject title = object.getJSONObject("title");
                        String rendered =title.getString("rendered");
                        tvPost.append(rendered + "\n" +id+"\n\n");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

}
