package like.ry.org.likefast.widget.recyclerfooter;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/22
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class StickyFooterTouchListener implements RecyclerView.OnItemTouchListener {
    private final GestureDetector mTapDetector;
    private final RecyclerView mRecyclerView;
    private final FooterDecoration mDecor;
    private OnHeaderClickListener mOnHeaderClickListener;

    public interface OnHeaderClickListener {
        void onHeaderClick(View header, int position, long headerId);
    }

    public StickyFooterTouchListener(final RecyclerView recyclerView,
                                              final FooterDecoration decor) {
        mTapDetector = new GestureDetector(recyclerView.getContext(), new SingleTapDetector());
        mRecyclerView = recyclerView;
        mDecor = decor;
    }


    public void setOnHeaderClickListener(OnHeaderClickListener listener) {
        mOnHeaderClickListener = listener;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        if (this.mOnHeaderClickListener != null) {
            boolean tapDetectorResponse = this.mTapDetector.onTouchEvent(e);
            if (tapDetectorResponse) {
                // Don't return false if a single tap is detected
                return true;
            }
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                return mDecor.findHeaderPositionUnder((int)e.getX(), (int)e.getY());
            }
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent e) { /* do nothing? */ }

    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // do nothing
    }

    private class SingleTapDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (mDecor.findHeaderPositionUnder((int) e.getX(), (int) e.getY())) {
                View headerView = mDecor.getFooterView(mRecyclerView);
                mOnHeaderClickListener.onHeaderClick(headerView, 0, 0);
                mRecyclerView.playSoundEffect(SoundEffectConstants.CLICK);
                headerView.onTouchEvent(e);
                return true;
            }
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }
    }
}

