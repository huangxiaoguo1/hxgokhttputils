package tsou.cn.lib_hxgokhttp.callback;

import com.google.gson.Gson;

import tsou.cn.lib_hxgokhttp.URLUtil;

/**
 * Created by Administrator on 2018/7/20 0020.
 * 基于Gson的自动转换
 */

public abstract class DefaultHttpCallBack<T> extends StringEngineCallBack {

    /**
     * 返回可操作的对象
     *
     * @param result
     */
    public abstract void onSuccess(T result);



    @Override
    public void onProgress(int progress) {

    }
    @Override
    public void onSuccess(String resultJson) {
        Gson gson = new Gson();
        T objResult = (T) gson.fromJson(resultJson, URLUtil.analysisClazzInfo(this));
        onSuccess(objResult);
    }
}
