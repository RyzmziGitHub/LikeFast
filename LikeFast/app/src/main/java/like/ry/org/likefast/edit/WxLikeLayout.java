package like.ry.org.likefast.edit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.NinePatchDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseFrameLayout;
import like.ry.org.likefast.util.AppUtil;
import like.ry.org.likefast.util.ThemeUtil;
import like.ry.org.likefast.widget.span.CenterImageSpan;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/7/1
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class WxLikeLayout extends BaseFrameLayout{

    private TextView tvStart;
    private LinearLayout llFriends;
    private ImageView ivHeaderIcon,ivContent;
    private TextView tvName,tvContent,tvTime;
    private SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

    public WxLikeLayout(Context context) {
        super(context);
    }

    public WxLikeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView() {
        tvStart = (TextView)findViewById(R.id.tv_star_content);
        llFriends = (LinearLayout)findViewById(R.id.ll_friends);
        ivHeaderIcon = (ImageView)findViewById(R.id.iv_header_icon);
        tvName = (TextView)findViewById(R.id.tv_name);
        tvContent = (TextView)findViewById(R.id.tv_content_text);
        ivContent = (ImageView)findViewById(R.id.iv_content_image);
        tvTime = (TextView)findViewById(R.id.tv_time);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int fromLayout() {
        return R.layout.layout_wx_like;
    }

    @Override
    protected void initListener() {

    }

    public int makeLikeCrop(String likeNames,String talk,Bitmap talkBitmap){
        //like name
        spannableStringBuilder.clear();
        spannableStringBuilder.append("  " + likeNames);
        spannableStringBuilder.setSpan(new CenterImageSpan(context, R.drawable.heart), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvStart.setText(spannableStringBuilder);
        // header
        ivHeaderIcon.setImageBitmap(AppUtil.getIconForAppPath(context));
        //talk
        if(TextUtils.isEmpty(talk)){
            AppUtil.setVisibility(tvContent,GONE);
        }else{
            AppUtil.setVisibility(tvContent,VISIBLE);
            tvContent.setText(talk);
        }
        //nickname
        String name = AppUtil.getData(context, Constant.USER_NAME);
        if(!TextUtils.isEmpty(name)){
            tvName.setText(name);
        }
        //talk bitmap
        if(talkBitmap == null){
            AppUtil.setVisibility(ivContent,INVISIBLE);
        }else{
            AppUtil.setVisibility(ivContent,VISIBLE);
            ivContent.setImageBitmap(talkBitmap);
        }
        NinePatchDrawable ninePatchDrawable = (NinePatchDrawable)tvStart.getBackground();
        int height = AppUtil.getHeight(context, likeNames, tvStart.getTextSize(), ninePatchDrawable.getIntrinsicWidth());
        return height + ThemeUtil.dpToPx(context,200);
    }
}
