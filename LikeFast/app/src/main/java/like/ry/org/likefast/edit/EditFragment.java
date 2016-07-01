package like.ry.org.likefast.edit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseActivity;
import like.ry.org.likefast.base.BaseFragment;
import like.ry.org.likefast.common.CommonButton;
import like.ry.org.likefast.common.CommonImageView;
import like.ry.org.likefast.manager.CircleManager;
import like.ry.org.likefast.manager.DialogManager;
import like.ry.org.likefast.manager.DictionaryManager;
import like.ry.org.likefast.manager.InputManager;
import like.ry.org.likefast.photo.CropActivity;
import like.ry.org.likefast.photo.PhotoChooseAdapter;
import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.util.AppUtil;
import rx.functions.Action1;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/17
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class EditFragment extends BaseFragment implements PhotoChooseAdapter.OnItemClickListener {

    private CommonButton cbHeader, cbCrop,cbWxShare;
    private CommonImageView ciHeader,ciCrop;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private PhotoChooseAdapter photoChooseAdapter;
    private String[] strPhotoChoose;
    private DictionaryManager dictionaryManager;
    private TextInputEditText textIENum, textIEName, textIETalk;
    private WxLikeLayout wxLikeLayout;
    private Handler handler = new Handler();
    private int cropHeight;

    @Override
    protected void initView(View view) {
        cbHeader = (CommonButton) view.findViewById(R.id.edit_btn_header);
        ciHeader = (CommonImageView) view.findViewById(R.id.edit_iv_header);
        cbCrop = (CommonButton) view.findViewById(R.id.edit_btn_crop);
        slidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.edit_pl);
        textIENum = (TextInputEditText) view.findViewById(R.id.edit_te_num);
        textIEName = (TextInputEditText) view.findViewById(R.id.edit_te_name);
        textIETalk = (TextInputEditText) view.findViewById(R.id.edit_te_talk);
        cbWxShare = (CommonButton)view.findViewById(R.id.edit_btn_wx_crop);
        ciCrop = (CommonImageView)view.findViewById(R.id.edit_iv_crop);
    }

    @Override
    protected void initData(View view) {
        wxLikeLayout = new WxLikeLayout(getActivity());
        strPhotoChoose = getResources().getStringArray(R.array.photo_choose);
        photoChooseAdapter = new PhotoChooseAdapter(getActivity(), strPhotoChoose, this);
        dictionaryManager = new DictionaryManager(getActivity());
        DialogManager.getInstance().create(getActivity(),
                photoChooseAdapter);
        loadHeaderIcon();
        textIEName.setText(AppUtil.getData(getActivity(),Constant.USER_NAME));
    }

    @Override
    protected int fromLayout() {
        return R.layout.fragment_edit;
    }

    @Override
    protected void initListener() {
        cbHeader.setOnClickListener(this);
        ciHeader.setOnClickListener(this);
        cbCrop.setOnClickListener(this);
        cbWxShare.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        if (v == cbHeader || v == ciHeader) {
            InputManager.getInstances(getActivity()).setFocus(v);
            InputManager.getInstances(getActivity()).hideALlSoftInput();
            handler.postAtTime(new Runnable() {
                @Override
                public void run() {
                    if (v == cbHeader || v == ciHeader) {
                        DialogManager.getInstance().show();
                    }
                }
            }, 200);
        } else if (v == cbCrop) {
            String num = textIENum.getText().toString();
            if(TextUtils.isEmpty(num)){
                textIENum.setError(getActivity().getResources().getString(R.string.not_null));
                return;
            }
            AppUtil.saveData(getActivity(), Constant.USER_NAME, textIEName.getText().toString());
            dictionaryManager.getNickName(Integer.parseInt(num)).subscribe(new Action1<String>() {
                @Override
                public void call(String s) {
                    cropHeight = wxLikeLayout.makeLikeCrop(s, textIETalk.getText().toString(),null);
                    Bitmap bitmap = AppUtil.loadBitmapFromView(
                            wxLikeLayout,AppUtil.getWindows(getActivity()).widthPixels,cropHeight);
                    AppUtil.saveIconForSDPath(bitmap);
                    ciCrop.setImageBitmap(bitmap);
                    slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                }
            });
        }else if(v == cbWxShare){

        }

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

    private void loadHeaderIcon() {
        Bitmap bitmap = AppUtil.getIconForAppPath(getActivity());
        if (bitmap != null) {
            CircleManager.getInstance().transform(bitmap, ciHeader);
        }
    }

}
