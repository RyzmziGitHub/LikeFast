package like.ry.org.likefast.widget.recyclerfooter;

import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import like.ry.org.likefast.util.AppLog;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/21
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class FooterViewCache {
    private static final String TAG = FooterViewCache.class.getSimpleName();

    private final StickyFooterAdapter mAdapter;
    private final LongSparseArray<View> mHeaderViews = new LongSparseArray<>();

    public FooterViewCache(StickyFooterAdapter adapter) {
        mAdapter = adapter;
    }

    public View getFooter(RecyclerView parent) {
        long headerId = mAdapter.getFooterId();

        View header = mHeaderViews.get(headerId);
        if (header == null) {
            AppLog.i(TAG,"new header");
            //TODO - recycle views
            RecyclerView.ViewHolder viewHolder = mAdapter.onCreateHeaderViewHolder(parent);
            mAdapter.onBindFooterViewHolder(viewHolder);
            header = viewHolder.itemView;
            if (header.getLayoutParams() == null) {
                header.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(), header.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);
            header.measure(childWidth, childHeight);
            header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
            mHeaderViews.put(headerId, header);
        }
        return header;
    }

    public void invalidate() {
        mHeaderViews.clear();
    }

    public int getItemWidth(){
        return mAdapter.itemWidth();
    }
}
