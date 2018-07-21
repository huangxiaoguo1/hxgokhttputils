package tsou.cn.lib_hxgokhttp;

import android.content.Context;

import java.util.Map;

import tsou.cn.lib_hxgokhttp.callback.EngineCallBack;

/**
 * Created by Administrator on 2018/7/19 0019.
 * 引擎规范
 */

public interface IHttpEngine {
    /**
     * get请求
     */
    void get(Context context, String url, Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack);

    /**
     * post请求
     * 可传递文件
     */
    void post(Context context,String url,Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack);
    /**
     * put请求
     * 可传递文件
     */
    void put(Context context,String url,Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack);
    /**
     * delete请求
     * 可传递文件
     */
    void delete(Context context,String url,Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack);
    /**
     * post请求
     * json
     */
    void post(Context context,String url,Map<String, Object> header, String json, EngineCallBack callBack);
    /**
     * put请求
     * json
     */
    void put(Context context,String url,Map<String, Object> header,String json, EngineCallBack callBack);
    /**
     * delete请求
     * json
     */
    void delete(Context context,String url,Map<String, Object> header,String json, EngineCallBack callBack);


    /**
     * 下载文件
     */
    /**
     * 上传文件
     */
    /**
     * https添加证书
     */
}
