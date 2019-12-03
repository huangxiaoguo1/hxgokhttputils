package tsou.cn.lib_hxgokhttp.interceptor;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tsou.cn.lib_hxgokhttp.application.HxgLogUtil;

/**
 * log过滤
 */

public class LogBitmapInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HxgLogUtil.d(String.format("Sending request %s on %s%n%s", request.url(),
                chain.connection(), request.headers()));
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        HxgLogUtil.d(String.format(Locale.getDefault(), "Received response %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, request.headers()));
        return response;
    }
}
