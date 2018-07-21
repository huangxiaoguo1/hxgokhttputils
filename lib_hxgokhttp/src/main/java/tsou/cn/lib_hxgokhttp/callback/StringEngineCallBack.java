package tsou.cn.lib_hxgokhttp.callback;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/7/19 0019.
 * 获取String类型数据
 */

public abstract class StringEngineCallBack implements EngineCallBack {

    protected static Handler mHandler=new Handler(Looper.getMainLooper());

    @Override
    public void onProgress(int progress) {

    }

    @Override
    public void onError(final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFail(e);
            }
        });
    }

    @Override
    public void onSuccess(ResponseBody result) throws IOException {
        final String resultJson = result.string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(resultJson);
            }
        });

    }
    public abstract void onSuccess(String resultJson);
    public abstract void onFail(Exception e);
}
