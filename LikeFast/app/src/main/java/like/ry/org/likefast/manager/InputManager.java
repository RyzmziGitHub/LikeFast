package like.ry.org.likefast.manager;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/30
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class InputManager {

    private Context context;
    public static InputManager inputManager;
    private InputMethodManager imm;

    private InputManager(Context context) {
        this.context = context;
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static InputManager getInstances(Context context) {
        if (inputManager == null) {
            inputManager = new InputManager(context);
        }
        return inputManager;
    }

    public void totleShowSoftInput() {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void showSoftInput(View view) {
        imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
    }

    public void hideSoftInput(EditText et) {
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public boolean hideKeyboard(View v, EditText et) {
        if (imm.isActive(et)) {
            v.requestFocus();
            imm.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            return true;
        }
        return false;
    }

    public void setFocus(View view) {
        view.setFocusable(true);
        view.requestFocus();
        view.setFocusableInTouchMode(true);
    }

    public void hideALlSoftInput() {
        View view = ((Activity) context).getWindow().peekDecorView();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean isActive(View view) {
        return imm.isActive(view);
    }
}
