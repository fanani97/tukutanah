package tukutanah.uas.anisashihhatin.com.tukutanah.tools;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SpacesItemDecorationGridView extends RecyclerView.ItemDecoration {// digunakan untuk merubah list menjadi grid misal list menjadi 2 kolom dsb

    private final int mSpace;

    public SpacesItemDecorationGridView(int space) {
        this.mSpace = space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        outRect.top = mSpace;
    }
}
