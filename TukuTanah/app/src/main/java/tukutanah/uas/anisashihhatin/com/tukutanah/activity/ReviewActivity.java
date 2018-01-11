package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.adapter.ReviewAdapter;
import tukutanah.uas.anisashihhatin.com.tukutanah.manager.AppController;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.ReviewModel;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter mAdapter;
    private Toolbar toolbar;

    private List<ReviewModel> reviewModel = new ArrayList<>();
    private String TAG = ReviewActivity.class.getSimpleName();
    private String jsonResponse;
    private static String url = "https://tukutanah-9ce2.restdb.io/rest/review?apikey=863c530719b214bc1bbe4cb9a39f84c8e75d3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        recyclerView = (RecyclerView) findViewById(R.id.tv_comment);

        mAdapter = new ReviewAdapter(reviewModel, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        reqData();
        reviewModel.addAll(reviewModel);
        mAdapter.notifyDataSetChanged();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void reqData() {
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                try {
                    jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) { //digunakan untuk perulangan data array

                        JSONObject c = (JSONObject) response.get(i);

//                        if (c.getString("id_tanah").equals(sId)) {
                        String id = c.getString("id_review");
                        String name = c.getString("name");
                        String comment = c.getString("comment");
                        reviewModel.add(new ReviewModel(name, comment));
                        Log.e(TAG, "KENEK O TALA " + name);
                        mAdapter.notifyDataSetChanged();
                    }
                    mAdapter.notifyDataSetChanged();
                    Log.e(TAG, "FANANI " + mAdapter.getItemCount());
                    Log.e(TAG, "AHMAD " + response.length());
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
}
