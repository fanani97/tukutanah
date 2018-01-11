package tukutanah.uas.anisashihhatin.com.tukutanah.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.adapter.HomeViewPagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private SharedPreferences pre ;
    private SharedPreferences.Editor editor ;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewPager); //inisialisasi id view pager di xml
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots); //inisialisasi linear layout dots di xml

        HomeViewPagerAdapter viewPagerAdapter = new HomeViewPagerAdapter(this); //set adapter untuk dots di bawah viewpager
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        Timer timer = new Timer(); //inisialisasi timer untuk otomatis slide di viewpager
        timer.scheduleAtFixedRate(new MyTimeTest(), 2000, 4000);

        txtName = (TextView) findViewById(R.id.txt_name);
        try {
            pre = getSharedPreferences("store",MODE_PRIVATE);
            String name =  pre.getString("name",null);
            if (name.equals(""))
            {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
            else
            {
                txtName.setText("Wellcome, " + name);
            }
        }
        catch (Exception e){

            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }

        for (int i = 0; i < dotscount; i++) { //perulangan untuk jumlah dots sesuai jumlah image yg di pasang
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot)); //set default dots active saat pertama mulai aplikasi
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) { //perulangan untuk active dots saat pindah dari gambar 1 ke 2
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        toolbar = (Toolbar) findViewById(R.id.toolbar); //inisialisasi toolbar pada xml
        setSupportActionBar(toolbar); //set toolbar untuk layout home
        navigationView = (NavigationView) findViewById(R.id.navigation_view); //inisialisasi navigation drawer di xml
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
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        Toast.makeText(getApplicationContext(), "Beranda Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation2:
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Search Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation3:
                        startActivity(new Intent(HomeActivity.this, AboutActivity.class));
                        Toast.makeText(getApplicationContext(), "About Telah Dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    case R.id.navigation4:
                        startActivity(new Intent(HomeActivity.this, HelpActivity.class));
                        Toast.makeText(getApplicationContext(), "Help telah dipilih", Toast.LENGTH_SHORT).show();
                        finish();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Kesalahan Terjadi ", Toast.LENGTH_SHORT).show();
                        finish();
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

    public class MyTimeTest extends TimerTask{
        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){ //pemberian statement untuk slider gambar 1 ke 2 dst
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
