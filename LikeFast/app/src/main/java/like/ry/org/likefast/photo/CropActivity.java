package like.ry.org.likefast.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseActivity;
import like.ry.org.likefast.common.CommonButton;
import like.ry.org.likefast.util.AppUtil;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/29
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class CropActivity extends BaseActivity implements CropManager.OnCropListener{



    private CropManager cropManager;
    private PhotoViewAttacher attacher;
    private ImageView ivCrop;
    private CommonButton cbCrop;
    @Override
    protected void initView() {
        ivCrop = (ImageView)findViewById(R.id.crop_iv);
        cbCrop = (CommonButton)findViewById(R.id.crop_cb);
    }

    @Override
    protected void initData() {
        cropManager = new CropManager(this);
        cropManager.openPhoto(this);
        attacher = new PhotoViewAttacher(ivCrop);
    }

    @Override
    protected int fromLayout() {
        return R.layout.activity_crop;
    }

    @Override
    protected void initListener() {

        cbCrop.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(v == cbCrop){
            Bitmap bitmap = cropManager.crop(ivCrop, attacher);
            AppUtil.saveIconForAppPath(CropActivity.this, bitmap);
            setResult(Constant.Crop.CODE_CROP, new Intent());
            finish();
        }
    }

    @Override
    public void cropBitmap(Bitmap bitmap) {
        ivCrop.setImageBitmap(bitmap);
        attacher.update();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            finish();
        }else{
            cropManager.onActivityResult(requestCode, resultCode, data);
        }
    }

}
