package tsou.cn.lib_hxgokhttp.callback;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/7/20 0020.
 */

public interface EngineCallBack {

    //错误方法
    void onError(Exception e);
    void onProgress(int progress);
    //成功方法
    void onSuccess(ResponseBody result) throws IOException;

    EngineCallBack DEFUALT_CALL_BACK = new EngineCallBack() {

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
