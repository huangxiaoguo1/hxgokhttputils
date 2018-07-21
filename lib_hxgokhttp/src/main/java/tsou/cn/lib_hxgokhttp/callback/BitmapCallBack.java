package tsou.cn.lib_hxgokhttp.callback;

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
        InputStream is = result.byteStream();
        //使用 BitmapFactory 的 decodeStream 将图片的输入流直接转换为 Bitmap
        final Bitmap bitmap = BitmapFactory.decodeStream(is);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(bitmap);
            }
        });
        is.close();
    }
    public abstract void onSuccess(Bitmap bitmap);
}
