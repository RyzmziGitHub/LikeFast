package like.ry.org.likefast.manager;

import android.graphics.Bitmap;
import android.widget.ImageView;

import like.ry.org.likefast.widget.picasso.PicassoCirclTransform;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/29
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class CircleManager {
    private static CircleManager ourInstance = new CircleManager();

    private PicassoCirclTransform picassoCirclTransform;

    public static CircleManager getInstance() {
        return ourInstance;
    }

    private CircleManager() {
        picassoCirclTransform = new PicassoCirclTransform();
    }


    public void transform(Bitmap bitmap,ImageView imageView){
        imageView.setImageBitmap(picassoCirclTransform.transform(bitmap));
    }
}
