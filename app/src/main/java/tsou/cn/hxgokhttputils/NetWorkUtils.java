package tsou.cn.hxgokhttputils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 黄家三少 on 2018/7/20.
 */

public class NetWorkUtils {
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) UIUtils.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
