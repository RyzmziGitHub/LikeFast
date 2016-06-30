package like.ry.org.likefast.photo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import like.ry.org.likefast.R;
/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/28
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class CropLayout extends FrameLayout {

    private Paint paint;
    private int spacing;

    public CropLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }

    public CropLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    public CropLayout(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.app_gray));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        spacing = getResources().getDimensionPixelOffset(R.dimen.crop_spacing);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.dispatchDraw(canvas);
        int diff = (getHeight() - getWidth()) / 2;
        canvas.drawRect(new Rect(spacing, diff, getWidth() - spacing, diff + getWidth()), paint);
    }
}
