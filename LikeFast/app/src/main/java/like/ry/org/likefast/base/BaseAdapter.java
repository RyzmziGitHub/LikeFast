package like.ry.org.likefast.base;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/20
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public abstract class BaseAdapter <VH extends BaseViewholder> extends RecyclerView.Adapter<VH>{

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    protected abstract Animator[] getAnimators(View view);

    @Override
    public int getItemCount() {
        return 0;
    }
}
