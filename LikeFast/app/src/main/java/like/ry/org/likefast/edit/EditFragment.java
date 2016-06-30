package like.ry.org.likefast.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseActivity;
import like.ry.org.likefast.base.BaseFragment;
import like.ry.org.likefast.common.CommonButton;
import like.ry.org.likefast.common.CommonImageView;
import like.ry.org.likefast.manager.CircleManager;
import like.ry.org.likefast.manager.DialogManager;
import like.ry.org.likefast.manager.InputManager;
import like.ry.org.likefast.photo.CropActivity;
import like.ry.org.likefast.photo.PhotoChooseAdapter;
import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.util.AppUtil;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/17
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class EditFragment extends BaseFragment implements PhotoChooseAdapter.OnItemClickListener{

    private CommonButton cbHeader;
    private CommonImageView ciHeader;
    private PhotoChooseAdapter photoChooseAdapter;
    private String[] strPhotoChoose;
    private Handler handler = new Handler();
    @Override
    protected void initView(View view) {
        cbHeader = (CommonButton) view.findViewById(R.id.edit_btn_header);
        ciHeader = (CommonImageView) view.findViewById(R.id.edit_iv_header);
    }

    @Override
    protected void initData(View view) {
        strPhotoChoose = getResources().getStringArray(R.array.photo_choose);
        photoChooseAdapter = new PhotoChooseAdapter(getActivity(),strPhotoChoose,this);
        DialogManager.getInstance().create(getActivity(),
                photoChooseAdapter);
        loadHeaderIcon();
    }

    @Override
    protected int fromLayout() {
        return R.layout.fragment_edit;
    }

    @Override
    protected void initListener() {
        cbHeader.setOnClickListener(this);
        ciHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        InputManager.getInstances(getActivity()).setFocus(v);
        InputManager.getInstances(getActivity()).hideALlSoftInput();
        handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                if (v == cbHeader || v == ciHeader) {
                    DialogManager.getInstance().show();
                }
            }
        },200);
    }

    @Override
    public void onItemClick(int position, String name) {
        DialogManager.getInstance().hide();
        BaseActivity.startUIActivity(getActivity(), CropActivity.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.Crop.CODE_CROP) {
            AppLog.i("onActivityResult-Constant.Crop.CODE_CROP");
            loadHeaderIcon();
        }
    }

    private void loadHeaderIcon(){
        Bitmap bitmap = AppUtil.getIconForAppPath(getActivity());
        if(bitmap != null){
            CircleManager.getInstance().transform(bitmap,ciHeader);
        }
    }

}
