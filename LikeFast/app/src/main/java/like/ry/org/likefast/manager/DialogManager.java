package like.ry.org.likefast.manager;
import android.content.Context;
import android.view.Gravity;

import like.ry.org.likefast.widget.dialog.DialogPlus;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/27
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class DialogManager {

    private static DialogManager ourInstance = new DialogManager();

    private DialogPlus dialogPlus;

    public static DialogManager getInstance() {
        return ourInstance;
    }

    private DialogManager() {
    }

    public DialogManager create(Context context,android.widget.BaseAdapter baseAdapter) {
        dialogPlus = DialogPlus.newDialog(context).setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setAdapter(baseAdapter).create();
        return ourInstance;
    }

    public void show(){
        dialogPlus.show();
    }

    public void hide(){
        dialogPlus.dismiss();
    }
}
