package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.adapter.TanahAdapter;
import tukutanah.uas.anisashihhatin.com.tukutanah.manager.AppController;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.TanahModel;
import tukutanah.uas.anisashihhatin.com.tukutanah.tools.SpacesItemDecorationGridView;

public class MainActivity extends ActionBarActivity {

    private RecyclerView recyclerViewCar;
    private StaggeredGridLayoutManager mGridViewLayoutManager;
    private TanahAdapter mAdapter;
    private List<TanahModel> soilModelList = new ArrayList<>();
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private LinearLayout item_filter;
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> soilList;
    private String TAG = MainActivity.class.getSimpleName();
    private String jsonResponse;
    private static String url = "https://tukutanah-9ce2.restdb.io/rest/list-tanah?apikey=863c530719b214bc1bbe4cb9a39f84c8e75d3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soilList = new ArrayList<>();

        recyclerViewCar = (RecyclerView) findViewById(R.id.recyclerview_car);

        mAdapter = new TanahAdapter(soilModelList, this); //inisialisasi adapter
        recyclerViewCar.setHasFixedSize(true); //set size adapter
        mGridViewLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);//inisialisasi decorasi untuk grid
        mGridViewLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerViewCar.setLayoutManager(mGridViewLayoutManager);
        SpacesItemDecorationGridView decoration = new SpacesItemDecorationGridView(10);
        recyclerViewCar.addItemDecoration(decoration);

//        mAdapter = new TanahAdapter(soilModelList, getBaseContext());
        recyclerViewCar.setAdapter(mAdapter);
        soilModelList.addAll(soilModelList);
        getData(); //pemanggilan void getData

        mAdapter.notifyDataSetChanged();

//        recyclerViewCar.setAdapter(mAdapter);
//        mAdapter.setClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Menginisiasi  NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        //Mengatur Navigasi View Item yang akan dipanggil untuk menangani item klik menu navigasi
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()) {
                    // pilihan menu item navigasi akan menampilkan pesan toast klik kalian bisa menggantinya
                    //dengan intent activity
                    case R.id.navigation1:
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        Toast.makeText(getApplicationContext(), "Home Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation2:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        Toast.makeText(getApplicationContext(), "List Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation3:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        Toast.makeText(getApplicationContext(), "About Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation4:
                        startActivity(new Intent(MainActivity.this, HelpActivity.class));
                        Toast.makeText(getApplicationContext(), "Help telah dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        return true;
                }
            }
        });
        // Menginisasi Drawer Layout dan ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Kode di sini akan merespons setelah drawer menutup disini kita biarkan kosong
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //  Kode di sini akan merespons setelah drawer terbuka disini kita biarkan kosong
                super.onDrawerOpened(drawerView);
            }
        };
        //Mensetting actionbarToggle untuk drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //memanggil synstate
        actionBarDrawerToggle.syncState();
    }

    public void itemClicked(View view, int position) {

        Intent intent = new Intent(MainActivity.this, DetailTanahActivity.class);
//        intent.putExtra("ItemPosition",position);

        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void getData() {
        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json array response
                    // loop through each json object
                    jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) { //digunakan untuk perulangan data array

                        JSONObject c = (JSONObject) response.get(i);

                        String id = c.getString("id_tanah"); //digunakan untuk mengambil array object di API
                        String name = c.getString("name");
                        String image = c.getString("image");
                        String price = c.getString("price");
                        String address = c.getString("adrress");
                        String phone = c.getString("phone");
                        String large = c.getString("large");
                        String city = c.getString("city");
                        String sertificate = c.getString("sertificate");
                        String description = c.getString("description");
                        int lat = c.getInt("latitude");
                        int lng = c.getInt("longitude");
                        String district = c.getString("districts");

                        Log.e(TAG, "KENEK O" + c);
                        soilModelList.add(new TanahModel(id, image, name, price, address, phone, large, city,sertificate, description, lat, lng, district));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

//                        hidepDialog();
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
