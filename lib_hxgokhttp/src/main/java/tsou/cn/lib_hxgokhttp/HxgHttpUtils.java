package tsou.cn.lib_hxgokhttp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import tsou.cn.lib_hxgokhttp.callback.EngineCallBack;

/**
 * Created by Administrator on 2018/7/19 0019.
 */

public class HxgHttpUtils {

    private String mUrl;
    private int mType;

    private static final int POST_TYPE = 0x0001;
    private static final int GET_TYPE = 0x0011;
    private static final int PUT_TYPE = 0x0111;
    private static final int DELETE_TYPE = 0x1111;
    private volatile Map<String, Object> mParams;
    private volatile Map<String, Object> mHeader;
    private static String stringJson;
    private Context mContext;

    @SuppressLint("NewApi")
    private HxgHttpUtils(Context context) {
        this.mContext = context;
        mParams = new ArrayMap<>();
        mHeader = new ArrayMap<>();
        stringJson = "";
    }

    /**
     * Url
     *
     * @return
     */
    public HxgHttpUtils url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 创建对象
     *
     * @param context
     * @return
     */
    public static HxgHttpUtils with(Context context) {
        return new HxgHttpUtils(context);
    }

    /**
     * POST请求
     *
     * @return
     */
    public HxgHttpUtils post() {
        this.mType = POST_TYPE;
        return this;
    }

    /**
     * GET请求
     *
     * @return
     */
    public HxgHttpUtils get() {
        this.mType = GET_TYPE;
        return this;
    }

    /**
     * PUT请求
     *
     * @return
     */
    public HxgHttpUtils put() {
        this.mType = PUT_TYPE;
        return this;
    }

    /**
     * DELETE请求
     *
     * @return
     */
    public HxgHttpUtils delete() {
        this.mType = DELETE_TYPE;
        return this;
    }

    /**
     * 一个一个添加参数
     *
     * @param key
     * @param value
     * @return
     */
    public HxgHttpUtils addParam(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    /**
     * 添加参数集合
     *
     * @param params
     * @return
     */
    public HxgHttpUtils addParam(Map<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    /**
     * 添加请求头
     *
     * @param key
     * @param value
     * @return
     */
    public HxgHttpUtils addHeader(String key, Object value) {
        mHeader.put(key, value);
        return this;
    }

    /**
     * 添加请求头
     *
     * @param params
     * @return
     */
    public HxgHttpUtils addHeader(Map<String, Object> params) {
        mHeader.putAll(params);
        return this;
    }

    /**
     * json方式提交
     *
     * @param json
     * @return
     */
    public HxgHttpUtils addJson(String json) {
        stringJson = json;
        return this;
    }

    /**
     * 添加回调 执行
     *
     * @return
     */
    public void execeute(EngineCallBack callBack) {
        if (callBack == null) {
            callBack = EngineCallBack.DEFUALT_CALL_BACK;
        }

        if (mType == POST_TYPE) {
            if (mParams.isEmpty()) {
                post(mUrl, mHeader, stringJson, callBack);
                return;
            }
            post(mUrl, mHeader, mParams, callBack);
        }
        if (mType == GET_TYPE) {
            get(mUrl, mHeader, mParams, callBack);
        }
        if (mType == PUT_TYPE) {
            if (mParams.isEmpty()) {
                put(mUrl, mHeader, stringJson, callBack);
                return;
            }
            put(mUrl, mHeader, mParams, callBack);
        }
        if (mType == DELETE_TYPE) {
            if (mParams.isEmpty()) {
                delete(mUrl, mHeader, stringJson, callBack);
                return;
            }
            delete(mUrl, mHeader, mParams, callBack);
        }
    }

    public void execeute() {
        execeute(null);
    }

    /**
     * 取消网络请求
     */
    public static void callAll(){
        OkHttpEngine.callAll();
    }

    /**
     * 默认Okhttp引擎
     */
    private static IHttpEngine mHttpEngine = new OkHttpEngine();

    //初始化其他的引擎
    public static void init(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
    }

    //也可以传入自己的OkHttp配置
    public static void init(OkHttpClient okHttpClient){
        OkHttpEngine.init(okHttpClient);
    }
    /**
     * 每次可以自带引擎
     *
     * @param httpEngine
     */
    public HxgHttpUtils exchangeEngine(IHttpEngine httpEngine) {
        mHttpEngine = httpEngine;
        return this;
    }

    private void get(String url, Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.get(mContext, url, header, params, callBack);
    }

    private void post(String url, Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.post(mContext, url, header, params, callBack);
    }

    private void put(String url, Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.put(mContext, url, header, params, callBack);
    }

    private void delete(String url, Map<String, Object> header, Map<String, Object> params, EngineCallBack callBack) {
        mHttpEngine.delete(mContext, url, header, params, callBack);
    }

    private void post(String url, Map<String, Object> header, String json, EngineCallBack callBack) {
        mHttpEngine.post(mContext, url, header, json, callBack);
    }

    private void put(String url, Map<String, Object> header, String json, EngineCallBack callBack) {
        mHttpEngine.put(mContext, url, header, json, callBack);
    }

    private void delete(String url, Map<String, Object> header, String json, EngineCallBack callBack) {
        mHttpEngine.delete(mContext, url, header, json, callBack);
    }
}























