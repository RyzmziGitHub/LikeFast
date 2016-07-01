package like.ry.org.likefast.main;
import android.view.View;

import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseActivity;
import like.ry.org.likefast.manager.PermissionManager;

public class MainActivity extends BaseActivity implements PermissionManager.OnPermissionListenter{

    private PermissionManager permissionManager;

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        permissionManager = new PermissionManager(this);
        permissionManager.requestPermission(this);
    }

    @Override
    protected int fromLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCancel() {
        finish();
    }
}
