package tsou.cn.lib_hxgokhttp.application;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by 黄家三少 on 2018/6/3.
 */

class HxgAppUtils {


    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

}
