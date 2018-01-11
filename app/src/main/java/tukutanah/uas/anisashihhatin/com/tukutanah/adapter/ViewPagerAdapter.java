package tukutanah.uas.anisashihhatin.com.tukutanah.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.welcome_image1,R.drawable.welcome_image2, R.drawable.welcome_image3};

    public ViewPagerAdapter(Context context) { //deklarasi context
        this.context = context;
    }

    @Override
    public int getCount() { //menghitung isi dari image
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //deklarasi layout infaltter
        View view = layoutInflater.inflate(R.layout.image_slider_layout, null); //deklarasi view
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView); //deklarasi imageview
        imageView.setImageResource(images[position]); //deklarasi image dan posisinya


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
