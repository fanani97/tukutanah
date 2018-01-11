package tukutanah.uas.anisashihhatin.com.tukutanah.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.activity.DetailTanahActivity;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.TanahModel;

public class TanahAdapter extends RecyclerView.Adapter<TanahAdapter.ViewHolder> implements Filterable {
    private List<TanahModel> listTanah;
    private List<TanahModel> mListTanah;
    private Context context;

    public TanahAdapter(List<TanahModel> listTanah, Context context) { //deklarasi model dan context
        this.listTanah = listTanah;
        this.mListTanah = listTanah;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //deklarasi layout item_tanah
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_tanah, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TanahModel soil = listTanah.get(position); //deklarasi list item per posisi

        System.out.println("COK A" + soil);

        holder.tvSoilName.setText("Nama : " + soil.getSoilName()); //digunakan untuk set text view yg diambil dari model yg sudah ditambah dari API
        holder.tvSoilPrice.setText("Lokasi " + soil.getSoilAddress()); //digunakan untuk set text view yg diambil dari model yg sudah ditambah dari API
        holder.tvSoilLarge.setText("Luas : " + soil.getSoilLarge()); //digunakan untuk set text view yg diambil dari model yg sudah ditambah dari API
        holder.tvSoilAddress.setText("Harga : Rp. " + soil.getSoilPrice()); //digunakan untuk set text view yg diambil dari model yg sudah ditambah dari API

        Glide.with(context) //digunakan untuk merubah menampilkan gambar yg berupa link
                .load(soil.getSoilImage())
                .centerCrop()
                .into(holder.ivSoil);

        holder.llSoil.setOnClickListener(new View.OnClickListener() { //fungsi klik linear layout
            @Override
            public void onClick(View view) {
                TanahModel tanahModel = listTanah.get(position); //deklarasi model
                String passId = tanahModel.getId(); //mengisi passImage dengan data pada model
                String passImage = tanahModel.getSoilImage(); //mengisi passImage dengan data pada model
                String passName = tanahModel.getSoilName(); //mengisi passName dengan data pada model
                String passPrice = tanahModel.getSoilPrice(); //mengisi passPrice dengan data pada model
                String passAddress = tanahModel.getSoilAddress(); //mengisi passAddress dengan data pada model
                String passPhone = tanahModel.getSoilPhone(); //mengisi passPhone dengan data pada model
                String passLarge = tanahModel.getSoilLarge(); //mengisi passLarge dengan data pada model
                String passCity = tanahModel.getSoilCity(); //mengisi passCity dengan data pada model
                String passSertifivate = tanahModel.getSoilSertificate(); //mengisi passSertifivate dengan data pada model
                String passDesc = tanahModel.getSoilDescription(); //mengisi passDesc dengan data pada model
                double passLat = tanahModel.getSoilLat(); //mengisi passLat dengan data pada model
                double passLng = tanahModel.getSoilLng(); //mengisi passLng dengan data pada model
                String passDistrict = tanahModel.getSoilDistrict(); //mengisi passDistrict dengan data pada model

                Intent intent = new Intent(view.getContext(), DetailTanahActivity.class); //fungsi untuk memindah activity
                intent.putExtra("id", passId); // deklrasai passing data untuk image
                intent.putExtra("image", passImage); // deklrasai passing data untuk image
                intent.putExtra("name", passName); // deklrasai passing data untuk name
                intent.putExtra("price", passPrice); // deklrasai passing data untuk price
                intent.putExtra("address", passAddress); // deklrasai passing data untuk address
                intent.putExtra("phone", passPhone); // deklrasai passing data untuk phone
                intent.putExtra("large", passLarge); // deklrasai passing data untuk large
                intent.putExtra("ciry", passCity); // deklrasai passing data untuk city
                intent.putExtra("sertificate", passSertifivate); // deklrasai passing data untuk sertificate
                intent.putExtra("desc", passDesc); // deklrasai passing data untuk description
                intent.putExtra("lat", passLat); // deklrasai passing data untuk latitude
                intent.putExtra("lng", passLng); // deklrasai passing data untuk longitude
                intent.putExtra("dist", passDistrict); // deklrasai passing data untuk district
                context.startActivity(intent); //digunakan untuk start activity atau berpindah ke activity yg sudah ditentukan
                Toast.makeText(context, "Position : " + position, Toast.LENGTH_SHORT).show(); //memunculkan notif dibawah layar
            }
        });

    }

    @Override
    public int getItemCount() { // untuk menghitung jumlah data di model tanah
        return null != listTanah ? listTanah.size() : 0;
    }

    // inner class of adapter
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivSoil; //deklarasi image view dll
        public TextView tvSoilName;
        public TextView tvSoilPrice;
        public TextView tvSoilLarge;
        public TextView tvSoilAddress;
        public LinearLayout llSoil;
        private String tvSoilPhone;
        private String tvSoilCity;
        private String tvSoilSertificate;
        private String tvSoilDescription;
        private int tvSoilLat;
        private int tvSoilLng;
        private String tvSoilDistrict;

        public ViewHolder(View itemView) {
            super(itemView);

            ivSoil = (ImageView) itemView.findViewById(R.id.image_view_soil); //deklarasi untuk menghubungkan dari id di xml ke object di java
            tvSoilName = (TextView) itemView.findViewById(R.id.text_view_tanah_name);
            tvSoilPrice = (TextView) itemView.findViewById(R.id.text_view_tanah_price);
            tvSoilAddress = (TextView) itemView.findViewById(R.id.text_view_tanah_loc);
            tvSoilLarge = (TextView) itemView.findViewById(R.id.text_view_tanah_large);
            llSoil = (LinearLayout) itemView.findViewById(R.id.ll_soil);
//            itemView.setOnClickListener(this);
        }
    }

    @Override
    public Filter getFilter() { //fungsi filter search
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) { // kondisi saat search kosong akan menampilkan seluruh data pada model
                    listTanah = mListTanah;
                } else {
                    ArrayList<TanahModel> filteredList = new ArrayList<>();
                    for (TanahModel androidVersion : mListTanah) { // jika pada search ada text akan di samakan dengan data name address dan district
                        if (androidVersion.getSoilName().toLowerCase().contains(charString) || androidVersion.getSoilAddress().toLowerCase().contains(charString) || androidVersion.getSoilDistrict().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    listTanah = filteredList; // digunakan untuk menampilkan data pada model yg sama dengan isi search view
                }
                FilterResults filterResults = new FilterResults(); //deklarasi filter result
                filterResults.values = listTanah; //hasil dari search adalah listtanah yg berupa model array list
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listTanah = (ArrayList<TanahModel>) filterResults.values;
                notifyDataSetChanged(); //diguankan untuk merubah data saat sudah mengisi search view
            }
        };
    }
}
