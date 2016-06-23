package like.ry.org.likefast.test;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import like.ry.org.likefast.R;
import like.ry.org.likefast.base.BaseAdapter;
import like.ry.org.likefast.base.BaseViewholder;
import like.ry.org.likefast.util.AppLog;
import like.ry.org.likefast.widget.recyclerfooter.StickyFooterAdapter;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/20
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class TestAdapter extends BaseAdapter implements StickyFooterAdapter {

    List<String> listView = new ArrayList<String>();

    static class ViewHolder extends BaseViewholder{

        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv =(TextView)itemView.findViewById(R.id.tv_1);
        }
    }



    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    protected Animator[] getAnimators(View view) {
        return new Animator[0];
    }

    @Override
    public BaseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_test,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.tv.setText("fuck"+position);
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLog.i("123");
            }
        });
    }

    /**
     * Creates a new ViewHolder for a header.  This works the same way onCreateViewHolder in
     * Recycler.Adapter, ViewHolders can be reused for different views.  This is usually a good place
     * to inflate the layout for the header.
     *
     * @param parent
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_header,parent,false);
        return new XXX(view);
    }

    @Override
    public int itemWidth() {
        return 300;
    }

    @Override
    public long getFooterId() {
        return 100;
    }

    @Override
    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder) {
        XXX xxx = (XXX)holder;
        xxx.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLog.i("xxxx");
            }
        });

    }

    static class XXX extends BaseViewholder{
        View view;
        public XXX(View itemView) {
            super(itemView);
            view = itemView;
        }
    }

}
