package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import java.util.ArrayList;
import java.util.List;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.adapter.ReviewAdapter;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.ReviewModel;

public class ReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter mAdapter;
    private Toolbar toolbar;

    private List<ReviewModel> reviewList = new ArrayList<>();
    private String TAG = ReviewActivity.class.getSimpleName();
    private String jsonResponse;
    private static String url = "https://tukutanah-9ce2.restdb.io/rest/review?apikey=863c530719b214bc1bbe4cb9a39f84c8e75d3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new ReviewAdapter(reviewList, this);
        recyclerView = (RecyclerView) findViewById(R.id.tv_comment);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);

        reqData();
    }

    private void reqData() {
        Ion
            .with(this)
            .load(url)
            .asJsonArray()
            .withResponse()
            .setCallback(new FutureCallback<Response<JsonArray>>() {
                @Override
                public void onCompleted(Exception e, Response<JsonArray> response) {
                    int code = response.getHeaders().code();
                    if (code == 200) {
                        JsonArray result = response.getResult().getAsJsonArray();
                        System.out.println(result.toString());
                        for (int i = 0; i < result.size(); i++) { //digunakan untuk perulangan data array
                            JsonObject data = result.get(i).getAsJsonObject();
                            String id = data.get("id_review").getAsString();
                            String name = data.get("name").getAsString();
                            String comment = data.get("comment").getAsString();
                            reviewList.add(new ReviewModel(name, comment));
                        }
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ReviewActivity.this,
                            "Error: " + code,
                            Toast.LENGTH_LONG).show();
                    }
                }
            });
    }
}
