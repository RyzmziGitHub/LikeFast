package like.ry.org.likefast.widget.recyclerfooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.util.AppUtil;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/20
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class FooterDecoration extends RecyclerView.ItemDecoration {

    private FooterViewCache footerViewCache;
    private Context context;
    private Rect footerRect = new Rect();

    public FooterDecoration(Context context, StickyFooterAdapter stickyFooterAdapter) {
        this.context = context;
        footerViewCache = new FooterViewCache(stickyFooterAdapter);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(canvas, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int visiPos = linearLayoutManager.findLastVisibleItemPosition() + 1;
            View view = footerViewCache.getFooter(parent);
            int maxWidth = visiPos * footerViewCache.getItemWidth();
            if (maxWidth + view.getWidth() <= AppUtil.getWindows(context).widthPixels) {
                draw(canvas, view, maxWidth);
            } else {
                draw(canvas, view, AppUtil.getWindows(context).widthPixels - view.getWidth());
            }
        }
    }

    private void draw(Canvas canvas, View view, int x) {
        AppLog.i("draw...");
        footerRect.set(x, 0, x + view.getWidth(), view.getHeight());
        canvas.save();
        canvas.translate(x, 0);
        view.draw(canvas);
        canvas.restore();
    }

    public boolean findHeaderPositionUnder(int x, int y) {
        if (footerRect.contains(x, y)) {
            return true;
        }
        return false;
    }

    public View getFooterView(RecyclerView parent) {
        return footerViewCache.getFooter(parent);
    }
}
