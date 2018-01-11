package tukutanah.uas.anisashihhatin.com.tukutanah.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;

public class HomeViewPagerAdapter extends PagerAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

    public HomeViewPagerAdapter(Context context) { //deklarasi context
        this.context = context;
    }

    @Override
    public int getCount() { //untuk menghiung isi data dari image
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) { //deklarasi object
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //deklarasi layout inflate
        View view = layoutInflater.inflate(R.layout.home_slider, null); //deklarasi layout home_slider
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView); //deklarasi image view
        imageView.setImageResource(images[position]); //untuk set image dan pengaturan posisi image


        ViewPager vp = (ViewPager) container; //deklarasi view pager
        vp.addView(view, 0); //menambah view
        return view; //pengembalian ke layout view

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) { //penghapusan item setelah di lihat
        ViewPager vp = (ViewPager) container; //deklarasi view pager
        View view = (View) object; //deklarasi view
        vp.removeView(view); //hapus view pager
    }
}
