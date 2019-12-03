package tsou.cn.lib_hxgokhttp.application;

import android.app.Application;
import android.content.Context;

public class HxgOkHttpContextProvider {
    private static volatile HxgOkHttpContextProvider instance;
    private Context mContext;

    private HxgOkHttpContextProvider(Context context) {
        mContext = context;
    }

    /**
     * 获取实例
     */
    public static HxgOkHttpContextProvider get() {
        if (instance == null) {
            synchronized (HxgOkHttpContextProvider.class) {
                if (instance == null) {
                    Context context = OkHttpApplicationContentProvider.mContext;
                    if (context == null) {
                        throw new IllegalStateException("context == null");
                    }
                    instance = new HxgOkHttpContextProvider(context);
                }
            }
        }
        return instance;
    }

    public Context getContext() {
        return mContext;
    }

    public Application getApplication() {
        return (Application) mContext.getApplicationContext();
    }
}
