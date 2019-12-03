package tsou.cn.lib_hxgokhttp.application;

import android.util.Log;


public class HxgLogUtil {

    private HxgLogUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean isDebug = HxgAppUtils.isApkInDebug(HxgOkHttpContextProvider.get().getApplication());
    private static final String TAG = "hxg_okhttp";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }
}
