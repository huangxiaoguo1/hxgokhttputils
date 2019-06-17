package tsou.cn.lib_hxgokhttp.interceptor;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tsou.cn.lib_hxgokhttp.NetWorkUtils;

/**
 * Created by 黄家三少 on 2018/7/20.
 * 缓存
 */

public class CacheInterceptor implements Interceptor {
    private Context mContext;
    public CacheInterceptor(Context context) {
        this.mContext=context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetWorkUtils.isNetworkAvailable(mContext)) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.i("huangxiaoguo", "no network");
        }
        Response response = chain.proceed(request);
        if (NetWorkUtils.isNetworkAvailable(mContext)) {
            int maxAge = 0 * 60; // 有网络时 设置缓存超时时间为0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            int maxStale = 60 * 60 * 24; // 无网络时，设置超时为1天
            Log.i("huangxiaoguo", "has maxStale=" + maxStale);
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}