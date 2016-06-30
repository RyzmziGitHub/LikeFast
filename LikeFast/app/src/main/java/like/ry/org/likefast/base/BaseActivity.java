package like.ry.org.likefast.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import like.ry.org.likefast.Constant;
import like.ry.org.likefast.R;
import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.util.AppUtil;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/16
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected View rootView;

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int fromLayout();

    protected abstract void initListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(fromLayout());
        rootView = findViewById(R.id.root);
        rootView.setPadding(0, AppUtil.getStatusBarHeight(this), 0, 0);
        rootView.setBackgroundColor(getResources().getColor(R.color.app_bg));
        initView();
        initListener();
        initData();
    }

    public static void startUIActivity(Context context,Class<?> cls,boolean isFinsh,int requestCode){
        BaseActivity instance = ((BaseActivity) context);
        Intent intent = new Intent(context,cls);
        instance.startActivityForResult(intent,requestCode);
        if(isFinsh){
            instance.finish();
        }
    }

    public static void startUIActivity(Context context,Class<?> cls){
        startUIActivity(context,cls,false, Constant.DEFAULT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> listFragment = getSupportFragmentManager().getFragments();
        if(listFragment != null){
            for(Fragment fragment : listFragment){
                fragment.onActivityResult(requestCode,resultCode,data);
            }
        }else{
            AppLog.i("onActivityResult-listFragment == null");
        }
    }
}
