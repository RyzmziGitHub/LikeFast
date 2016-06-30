package like.ry.org.likefast.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.util.AppUtil;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/28
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class CropManager {


    private Context context;
    private OnCropListener onCropListener;

    public CropManager(Context context) {
        this.context = context;
    }

    public void openPhoto(OnCropListener onCropListener) {
        this.onCropListener = onCropListener;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity) context).startActivityForResult(intent, Constant.Crop.CODE_CROP_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.Crop.CODE_CROP_REQUEST) {
            if (data != null) {
                onCropListener.cropBitmap(AppUtil.getBitmapByUri(context, data.getData()));
            }
        }
    }

    public interface OnCropListener {
        void cropBitmap(Bitmap bitmap);
    }

    public Bitmap crop(ImageView imageView, PhotoViewAttacher attacher) {
        Bitmap src = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        RectF rect = attacher.getDisplayRect();
        float scale = attacher.getScale();
        float croppingSize = Math.min(imageView.getWidth(), imageView.getHeight());
        float croppingLeft = -(rect.left - (imageView.getWidth() - croppingSize) / 2);
        float croppingTop = -(rect.top - (imageView.getHeight() - croppingSize) / 2);
        float cropLeftRate = croppingLeft / rect.width();
        float cropTopRate = croppingTop / rect.height();
        float cropSizeRate = croppingSize / Math.min(rect.width(), rect.height());
        int srcSize = (int) (cropSizeRate * Math.min(src.getWidth(), src.getHeight()));
        int srcLeft = (int) (cropLeftRate * src.getWidth());
        int srcTop = (int) (cropTopRate * src.getHeight());
        Rect srcRect = new Rect(srcLeft, srcTop, srcLeft + srcSize, srcTop + srcSize);
        Rect destRect = new Rect(0, 0, srcSize, srcSize);

        Bitmap bm = Bitmap.createBitmap(
                srcSize
                , srcSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(src, srcRect, destRect, null);
        return bm;
    }
}
