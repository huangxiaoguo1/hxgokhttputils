package tsou.cn.hxgokhttputils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

/**
 * Created by 黄家三少 on 2018/6/3.
 */

public class UIUtils {
    private static Toast toast;

    /**
     * 静态吐司
     *
     * @param context
     * @param text
     */
    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    /**
     * 不需要上下文对象的  静态toast
     */
    public static void showToast(String text) {
        showToast(getContext(), text);
    }


    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取上下文对象
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getInstance();
    }

}
