package like.ry.org.likefast.edit;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseFragment;
import like.ry.org.likefast.common.CommonButton;
import like.ry.org.likefast.common.CommonRipple;
import like.ry.org.likefast.widget.ripple.RippleLayout;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/17
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class EditFragment extends BaseFragment{

    private CommonButton commonButton;

    @Override
    protected void initView(View view) {
        commonButton = (CommonButton)view.findViewById(R.id.edit_cb_name);
    }

    @Override
    protected void initData(View view) {
        RippleLayout rippleLayout = CommonRipple.on(view.findViewById(R.id.tv)).rippleBackground(Color.WHITE).rippleColor(Color.RED).rippleHover(true).create();
//        rippleLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "fff", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected int fromLayout() {
        return R.layout.fragment_edit;
    }
}
