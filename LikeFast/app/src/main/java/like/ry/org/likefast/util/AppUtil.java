package like.ry.org.likefast.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.widget.AbsListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import like.ry.org.likefast.R;
import like.ry.org.likefast.widget.dialog.HeightAnimation;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/17
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class AppUtil {

    private static int statusH = 0;
    private static final int INVALID = -1;

    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelOffset(resourceId);
        }
        return 0;
    }

    public static int getStatusBarHeight(Context context) {
        if (statusH > 0) {
            AppLog.i("statusH:" + statusH);
            return statusH;
        }
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        statusH = result;
        return result;
    }

    private boolean hasNavBar(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Resources resources = context.getResources();
            int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                return resources.getBoolean(id);
            } else {
                boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
                boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
                return !hasMenuKey && !hasBackKey;
            }
        } else {
            return false;
        }
    }

    public static DisplayMetrics getWindows(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static DisplayMetrics getWindows(Context context) {
        return getWindows((Activity) context);
    }

    public static void animateContent(final View view, int to, Animation.AnimationListener listener) {
        HeightAnimation animation = new HeightAnimation(view, view.getHeight(), to);
        animation.setAnimationListener(listener);
        animation.setDuration(200);
        view.startAnimation(animation);
    }

    public static boolean listIsAtTop(AbsListView listView) {
        return listView.getChildCount() == 0 || listView.getChildAt(0).getTop() == listView.getPaddingTop();
    }

    /**
     * This will be called in order to create view, if the given view is not null,
     * it will be used directly, otherwise it will check the resourceId
     *
     * @return null if both resourceId and view is not set
     */
    public static View getView(Context context, int resourceId, View view) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (view != null) {
            return view;
        }
        if (resourceId != INVALID) {
            view = inflater.inflate(resourceId, null);
        }
        return view;
    }

    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        if ((gravity & Gravity.TOP) == Gravity.TOP) {
            return isInAnimation ? R.anim.slide_in_top : R.anim.slide_out_top;
        }
        if ((gravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
            return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
        }
        if ((gravity & Gravity.CENTER) == Gravity.CENTER) {
            return isInAnimation ? R.anim.fade_in_center : R.anim.fade_out_center;
        }
        return INVALID;
    }

    public static Bitmap getBitmapByUri(Context context, Uri uri) {
        try {
            return MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getMiddleValue(int prev, int next, float factor) {
        return Math.round(prev + (next - prev) * factor);
    }

    public static int getMiddleColor(int prevColor, int curColor, float factor) {
        if (prevColor == curColor)
            return curColor;

        if (factor == 0f)
            return prevColor;
        else if (factor == 1f)
            return curColor;

        int a = getMiddleValue(Color.alpha(prevColor), Color.alpha(curColor), factor);
        int r = getMiddleValue(Color.red(prevColor), Color.red(curColor), factor);
        int g = getMiddleValue(Color.green(prevColor), Color.green(curColor), factor);
        int b = getMiddleValue(Color.blue(prevColor), Color.blue(curColor), factor);

        return Color.argb(a, r, g, b);
    }

    public static int getColor(int baseColor, float alphaPercent) {
        int alpha = Math.round(Color.alpha(baseColor) * alphaPercent);

        return (baseColor & 0x00FFFFFF) | (alpha << 24);
    }

    public static void saveIconForAppPath(Context context, Bitmap bitmap) {
        File file = new File(context.getFilesDir().getPath());
        if (!file.exists()) {
            AppLog.i("dir:" + file.mkdir());
        }
        bitmapToFile(context.getFilesDir().getPath() + "/crop.png", bitmap);
    }

    public static boolean saveIconForSDPath(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory(), "wx");
        if (!file.exists()) {
            AppLog.i("dir:" + file.mkdir());
        }
        bitmapToFile(file.getPath() + "/" + System.currentTimeMillis() + ".png", bitmap);
        return true;
    }

    private static void bitmapToFile(String path, Bitmap bitmap) {
        File fileIcon = new File(path);
        FileOutputStream fos = null;
        try {
            boolean b = fileIcon.createNewFile();
            if (!b) {
                fileIcon.delete();
                fileIcon.createNewFile();
            }
            fos = new FileOutputStream(fileIcon);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
        } catch (IOException e) {
            AppLog.i("e:" + e.toString());
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap getIconForAppPath(Context context) {
        String path = context.getFilesDir().getPath() + "/crop.png";
        File file = new File(path);
        if (file.exists()) {
            return BitmapFactory.decodeFile(path);
        }
        return null;
    }

    public static void saveData(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getData(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public static void setVisibility(View view, int visibility) {
        if (view.getVisibility() == visibility) {
            return;
        }
        view.setVisibility(visibility);
    }

}
