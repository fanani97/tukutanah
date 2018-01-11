package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.TanahModel;

public class DetailTanahActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    ImageView tvImage;
    TextView tvName, tvAddress, tvPrice, tvPhone, tvLarge, tvCity, tvSert, tvDesc, tvDist;

    private FloatingActionButton fabCall;

    private View parent_view = null;
    private TanahModel place = null;
    private String TAG = DetailTanahActivity.class.getSimpleName();

    private GoogleMap mMap;
    private LatLng mSourceLatLng = null;
    private LatLng mDestinationLatLng = null;
    private TextView tv = null;
    private RelativeLayout mFooterLayout = null;
    private Button btnReview;

    String sId;
    String sName;
    int sLat, sLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tanah);

        btnReview = (Button) findViewById(R.id.btn_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailTanahActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        sId = getIntent().getStringExtra("id"); //mengambil daata parsing sesuai identitas, dibagian ini iamge
        String sImage = getIntent().getStringExtra("image"); //mengambil daata parsing sesuai identitas, dibagian ini iamge
        sName = getIntent().getStringExtra("name"); //mengambil daata parsing sesuai identitas
        String sPrice = getIntent().getStringExtra("price"); //mengambil daata parsing sesuai identitas
        String sAddress = getIntent().getStringExtra("address"); //mengambil daata parsing sesuai identitas
        final String sPhone = getIntent().getStringExtra("phone"); //mengambil daata parsing sesuai identitas
        String sLarge = getIntent().getStringExtra("large"); //mengambil daata parsing sesuai identitas
        String sCity = getIntent().getStringExtra("ciry"); //mengambil daata parsing sesuai identitas
        String sSert = getIntent().getStringExtra("sertificate"); //mengambil daata parsing sesuai identitas
        String sDesc = getIntent().getStringExtra("desc"); //mengambil daata parsing sesuai identitas
        sLat = getIntent().getIntExtra("lat", 0); //mengambil daata parsing sesuai identitas
        sLng = getIntent().getIntExtra("lng", 0); //mengambil daata parsing sesuai identitas
        String sDist = getIntent().getStringExtra("dist"); //mengambil daata parsing sesuai identitas

        tvImage = (ImageView) findViewById(R.id.detail_image); // deklarasi image view dengan id di xml
        tvName = (TextView) findViewById(R.id.detail_name); //deklarasi text view dengan id di xml
        tvAddress = (TextView) findViewById(R.id.detail_address);
        tvPrice = (TextView) findViewById(R.id.detail_price);
        tvPhone = (TextView) findViewById(R.id.detail_phone);
        tvLarge = (TextView) findViewById(R.id.detail_large);
        tvCity = (TextView) findViewById(R.id.detail_city);
        tvSert = (TextView) findViewById(R.id.detail_sert);
        tvDesc = (TextView) findViewById(R.id.detail_desc);
        tvDist = (TextView) findViewById(R.id.detail_district);

        Glide.with(this) //mengubha data link menjadi gambar
                .load(sImage)
                .centerCrop()
                .into(tvImage);

        tvName.setText(sName); //set data dari pasring ke text view yg ada di detail tanah
        tvAddress.setText(sAddress);
        tvPrice.setText(sPrice);
        tvPhone.setText(sPhone);
        tvLarge.setText("Luas " + sLarge);
        tvCity.setText("Kota " + sCity);
        tvSert.setText("Sertifikat " + sSert);
        tvDesc.setText(sDesc);
        tvDist.setText("Kecamatan " + sDist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail); // deklarasi toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager() // deklrasai Map
                .findFragmentById(R.id.mapPlaces);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Log.e(TAG, "LATLNG O" + sLat + " " + sLng);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(sLat, sLng); // set latitude dan longitude di map
        mMap.addMarker(new MarkerOptions().position(sydney).title("Soil Place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}