package like.ry.org.likefast.util;

import java.util.logging.Logger;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/15
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class AppLog {

    public static void i(String i) {
        Logger.getGlobal().info(i);
    }

    public static void i(String TAG,String i){
        Logger.getLogger(TAG).info(i);
    }

}
