package tukutanah.uas.anisashihhatin.com.tukutanah.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tukutanah.uas.anisashihhatin.com.tukutanah.R;
import tukutanah.uas.anisashihhatin.com.tukutanah.model.ReviewModel;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<ReviewModel> data;
    private Context context;

    public ReviewAdapter(List<ReviewModel> data, Context context) { //deklarasi model dan context
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //deklarasi layout item_tanah
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_comment, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ReviewModel review = data.get(position);
        holder.name.setText(review.getName());
        holder.comment.setText(review.getComment());
    }

    @Override
    public int getItemCount() { // untuk menghitung jumlah data di model tanah
        return data != null ? data.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, comment;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.txt_review_name);
            comment = (TextView) itemView.findViewById(R.id.txt_review_comment);
        }
    }
}
