package like.ry.org.likefast.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/7/1
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public abstract class BaseFrameLayout extends FrameLayout{

    protected Context context;

    public BaseFrameLayout(Context context) {
        super(context);
        init(context,null);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    protected void init(Context context, AttributeSet attrs){
        this.context = context;
        inflate(context, fromLayout(),this);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initData();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int fromLayout();

    protected abstract void initListener();

}
