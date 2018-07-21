package tsou.cn.hxgokhttputils;

import android.app.Application;
import android.content.Context;

/**
 * Created by 黄家三少 on 2018/7/20.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }


}
