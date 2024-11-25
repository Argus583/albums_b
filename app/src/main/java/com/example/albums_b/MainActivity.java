package com.example.albums_b;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "taggg";
    private String url = "https://jsonplaceholder.typicode.com/albums";

    RecyclerView recyclerView;
    Button button;
    AlbumAdapter albumAdapter;
    List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);

        albumList = new ArrayList<>();
        albumAdapter = new AlbumAdapter(albumList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(albumAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void getData() {
        Log.d(TAG, "Fetching data from server...");

        // Create a new RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a JsonArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Response received: " + response.toString());
                        albumList.clear(); // Clear existing data
                        try {
                            // Loop through the JSON array and create Album objects
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject albumJson = response.getJSONObject(i);
                                Album album = new Album(
                                        albumJson.getInt("id"),
                                        albumJson.getInt("userId"),
                                        albumJson.getString("title")
                                );
                                albumList.add(album);
                            }

                            // Notify adapter that data has changed
                            albumAdapter.notifyDataSetChanged();
                        } catch (Exception ex) {
                            Log.e(TAG, "Error parsing response: " + ex.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
            }
        });

        // Add the request to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }
}
