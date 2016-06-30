package like.ry.org.likefast.test;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseActivity;
import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.widget.recyclerfooter.FooterDecoration;
import like.ry.org.likefast.widget.recyclerfooter.StickyFooterTouchListener;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/20
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class TestActivity extends BaseActivity{

    private RecyclerView recyclerView;

    @Override
    protected void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.rv);
    }

    @Override
    protected void initData() {
        TestAdapter testAdapter = new TestAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false));
        FooterDecoration footerDecoration = new FooterDecoration(this, testAdapter);
        recyclerView.addItemDecoration(footerDecoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(testAdapter);
        StickyFooterTouchListener stickyFooterTouchListener = new StickyFooterTouchListener(recyclerView,footerDecoration);
        stickyFooterTouchListener.setOnHeaderClickListener(new StickyFooterTouchListener.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(View header, int position, long headerId) {
                AppLog.i("xxxxxxx");
            }
        });
        recyclerView.addOnItemTouchListener(stickyFooterTouchListener);
    }

    @Override
    protected int fromLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initListener() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
