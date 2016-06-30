package like.ry.org.likefast.manager;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import java.lang.reflect.Field;
import java.util.List;

/**
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 * AUTHOR BY YB
 * DATE 16/6/24
 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
 */
public class TaskManager {

    private static TaskManager instance;
    private final Context context;

    public static TaskManager getInstance(Context context){
        if(instance == null){
            instance = new TaskManager(context);
        }
        return instance;
    }

    private TaskManager(Context context) {
        this.context = context;
    }

    private String[] get() {
        if (Build.VERSION.SDK_INT < 21)
            return getPreLollipop();
        else
            return getLollipop();
    }

    public String getTopPackage(){
        return get()[0];
    }

    public boolean isTop(){
        return getTopPackage().equals(context.getPackageName());
    }

    private String[] getPreLollipop() {
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> tasks =
                activityManager().getRunningTasks(1);
        ActivityManager.RunningTaskInfo currentTask = tasks.get(0);
        ComponentName currentActivity = currentTask.topActivity;
        return new String[] { currentActivity.getPackageName() };
    }

    private String[] getLollipop() {
        final int PROCESS_STATE_TOP = 2;

        try {
            Field processStateField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");

            List<ActivityManager.RunningAppProcessInfo> processes =
                    activityManager().getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo process : processes) {
                if (
                    // Filters out most non-activity processes
                        process.importance <= ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                                &&
                                // Filters out processes that are just being
                                // _used_ by the process with the activity
                                process.importanceReasonCode == 0
                        ) {
                    int state = processStateField.getInt(process);

                    if (state == PROCESS_STATE_TOP)
                        /*
                         If multiple candidate processes can get here,
                         it's most likely that apps are being switched.
                         The first one provided by the OS seems to be
                         the one being switched to, so we stop here.
                         */
                        return process.pkgList;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return new String[] { };
    }

    private ActivityManager activityManager() {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

}
