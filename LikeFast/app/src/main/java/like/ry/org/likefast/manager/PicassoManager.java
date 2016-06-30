package like.ry.org.likefast.manager;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import like.ry.org.likefast.widget.picasso.PicassoCirclTransform;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/29
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class PicassoManager {

    private static PicassoManager ourInstance = new PicassoManager();

    public static PicassoManager getInstance() {
        return ourInstance;
    }

    private PicassoManager() {
    }


    public void loadCircle(Context context,int url,ImageView imageView){
        loadBase(context,url,new PicassoCirclTransform(),imageView);
    }

    private void loadBase(final Context context, final int url,final Transformation transformation, final ImageView imageView){
        Picasso.with(context).load(url).transform(transformation).fit()
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(url).transform(transformation).fit().into(imageView);
                    }
                });
    }
}
