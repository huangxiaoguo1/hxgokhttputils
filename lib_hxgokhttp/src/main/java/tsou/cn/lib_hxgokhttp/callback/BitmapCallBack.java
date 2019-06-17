package tsou.cn.lib_hxgokhttp.callback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/7/20 0020.
 * 下载图片
 */

public abstract class BitmapCallBack implements EngineCallBack {
    protected static Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onBefore(Context context) {

    }

    @Override
    public void onAfter() {

    }

    @Override
    public void onProgress(int progress) {

    }

    public abstract void onFail(Exception e);

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
        final InputStream is = result.byteStream();
        final Bitmap bitmap = BitmapFactory.decodeStream(is);
        is.close();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(bitmap);
            }
        });
    }

    public abstract void onSuccess(Bitmap ios);
}
