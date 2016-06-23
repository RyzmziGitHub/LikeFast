package like.ry.org.likefast.widget.recyclerfooter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/21
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public interface StickyFooterAdapter<VH extends RecyclerView.ViewHolder>{

    VH onCreateHeaderViewHolder(ViewGroup parent);

    int itemWidth();

    long getFooterId();

    void onBindFooterViewHolder(VH holder);
}
