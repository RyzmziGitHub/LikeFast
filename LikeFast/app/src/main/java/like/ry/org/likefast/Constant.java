package like.ry.org.likefast;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/29
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class Constant {

    public static final int BASE = 7;

    public static final int DEFAULT_REQUEST_CODE = 0x00001 << BASE;

    public class Crop {
        public static final int CROP = 0x00002 << BASE;
        public static final int CODE_CROP = CROP | 1;
        public static final int CODE_CROP_REQUEST = CROP | 2;
    }


}
