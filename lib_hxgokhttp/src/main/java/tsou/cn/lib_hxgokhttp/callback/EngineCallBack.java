package tsou.cn.lib_hxgokhttp.callback;

import android.content.Context;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/7/20 0020.
 */

public interface EngineCallBack {
    //在请求之前
    void onBefore(Context context);

    //在请求结束之后
    void onAfter();

    //错误方法
    void onError(Exception e);

    //进度
    void onProgress(int progress);

    //成功方法
    void onSuccess(ResponseBody result) throws IOException;

    EngineCallBack DEFUALT_CALL_BACK = new EngineCallBack() {


        @Override
        public void onBefore(Context context) {

        }

        @Override
        public void onAfter() {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onProgress(int progress) {

        }

        public void onSuccess(ResponseBody result) throws IOException {

        }

    };
}
