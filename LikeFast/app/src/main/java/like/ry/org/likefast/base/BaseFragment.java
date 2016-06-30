package like.ry.org.likefast.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/17
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(fromLayout(),null);
        initView(view);
        initListener();
        initData(view);
        return view;
    }


    protected abstract void initView(View view);

    protected abstract void initData(View view);

    protected abstract int fromLayout();

    protected abstract void initListener();
}
