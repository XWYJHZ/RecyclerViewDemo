package sxd.learn.android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Jackie
 * 2016/8/10.
 */
public class Util {
    private static Toast toast;

    public static void showToast(Context context, String content){
        if (toast == null) {
            toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(content);
        }
        toast.show();
    }
}
