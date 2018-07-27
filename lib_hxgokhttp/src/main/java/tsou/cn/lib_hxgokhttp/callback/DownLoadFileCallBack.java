package tsou.cn.lib_hxgokhttp.callback;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/7/20 0020.
 * 下载文件
 */

public abstract class DownLoadFileCallBack implements EngineCallBack {
    protected static Handler mHandler = new Handler(Looper.getMainLooper());
    private String mUrl;

    @Override
    public void onBefore(Context context) {

    }

    @Override
    public void onAfter() {

    }

    public DownLoadFileCallBack(String url) {
        this.mUrl = url;
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

    public abstract void onFail(Exception e);

    public abstract void onSuccess(String path);

    @Override
    public void onSuccess(ResponseBody result) throws IOException {

        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        // 储存下载文件的目录
        String savePath = isExistDir("huangxiaoguo");
        try {
            is = result.byteStream();
            long total = result.contentLength();
            final File file = new File(savePath, getNameFromUrl());
            fos = new FileOutputStream(file);
            long sum = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
                sum += len;
                final int progress = (int) (sum * 1.0f / total * 100);
                // 下载中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onProgress(progress);
                    }
                });
            }
            fos.flush();
            // 下载完成
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onSuccess(file.getAbsolutePath());
                }
            });
        } catch (Exception e) {
            onError(e);
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @return
     */
    private String getNameFromUrl() {
        if (mUrl == null) {
            throw new IllegalArgumentException("DownLoadFileCallBack未传入正确的url");
        }
        return mUrl.substring(mUrl.lastIndexOf("/") + 1);
    }

}
