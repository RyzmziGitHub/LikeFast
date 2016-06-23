package like.ry.org.likefast.common;
import android.content.Context;
import android.util.AttributeSet;
import like.ry.org.likefast.widget.ripple.RippleLayout;
/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/16
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class CommonRipple extends RippleLayout {

    public CommonRipple(Context context) {
        super(context);
        init();
    }

    public CommonRipple(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CommonRipple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

    }
}
