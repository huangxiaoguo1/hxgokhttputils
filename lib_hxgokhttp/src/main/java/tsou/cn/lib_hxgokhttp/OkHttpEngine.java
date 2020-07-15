package tsou.cn.lib_hxgokhttp;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import tsou.cn.lib_hxgokhttp.callback.BitmapCallBack;
import tsou.cn.lib_hxgokhttp.callback.DownLoadFileCallBack;
import tsou.cn.lib_hxgokhttp.callback.EngineCallBack;
import tsou.cn.lib_hxgokhttp.interceptor.LogBitmapInterceptor;
import tsou.cn.lib_hxgokhttp.interceptor.LogInterceptor;
import tsou.cn.lib_hxgokhttp.interceptor.NetInterceptor;

/**
 * Created by Administrator on 2018/7/19 0019.
 * 封装OkHttp默认引擎
 */

class OkHttpEngine implements IHttpEngine {


    private static OkHttpClient mOkHttpDefaultClient = new OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(new NetInterceptor())
            .addInterceptor(new LogInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .pingInterval(20, TimeUnit.SECONDS)
            .build();
    private static OkHttpClient mOkHttpDownClient = new OkHttpClient()
            .newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(new NetInterceptor())
            .addInterceptor(new LogBitmapInterceptor())
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .pingInterval(20, TimeUnit.SECONDS)
            .build();

    private static OkHttpClient mOkHttpClient = mOkHttpDefaultClient;

    private static boolean mFlog = false;

    public void setOkHttpClient(EngineCallBack engineCallBack) {
        if (mFlog) {
            return;
        }
        if (engineCallBack instanceof DownLoadFileCallBack || engineCallBack instanceof BitmapCallBack) {
            mOkHttpClient = mOkHttpDownClient;
        } else {
            mOkHttpClient = mOkHttpDefaultClient;
        }

    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void callAll() {
        mOkHttpClient.dispatcher().cancelAll();
    }


    public static void init(OkHttpClient okHttpClient) {
        mFlog = true;
        mOkHttpClient = okHttpClient;
    }

    @Override
    public void get(Context context, String url, Map<String, Object> header,
                    Map<String, Object> params, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        String jointUrl = URLUtil.jointParams(url, params);
        Request.Builder requestBuilder = new Request.Builder()
                .url(jointUrl)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        callBack.onBefore(context);
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onAfter();
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onAfter();
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void post(Context context, String url, Map<String, Object> header, Map<String, Object> params, TypeEnum mediaType,
                     final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        RequestBody requestBody = appendBody(params, mediaType);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .post(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onAfter();
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        callBack.onAfter();
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void put(Context context, String url, Map<String, Object> header, Map<String, Object> params, TypeEnum mediaType, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        RequestBody requestBody = appendBody(params, mediaType);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .put(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onAfter();
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        callBack.onAfter();
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void delete(Context context, String url, Map<String, Object> header, Map<String, Object> params, TypeEnum mediaType, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        RequestBody requestBody = appendBody(params, mediaType);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .delete(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onAfter();
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        callBack.onAfter();
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void post(Context context, String url, Map<String, Object> header, String stringJsonOrXml, TypeEnum media, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        MediaType mediaType = MediaType.parse(getTypeEnum(media));//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, stringJsonOrXml);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .post(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onAfter();
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onAfter();
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void put(Context context, String url, Map<String, Object> header, String stringJsonOrXml, TypeEnum media, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        MediaType mediaType = MediaType.parse(getTypeEnum(media));//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, stringJsonOrXml);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .put(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onAfter();
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onAfter();
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void delete(Context context, String url, Map<String, Object> header, String stringJsonOrXml, TypeEnum media, final EngineCallBack callBack) {
        setOkHttpClient(callBack);
        MediaType mediaType = MediaType.parse(getTypeEnum(media));//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, stringJsonOrXml);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder
                .delete(requestBody)
                .build();
        callBack.onBefore(context);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onAfter();
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onAfter();
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    /**
     * 组装post请求参数body
     *
     * @param params
     * @return
     */
    private RequestBody appendBody(Map<String, Object> params, TypeEnum mediaType) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(getMediaType(mediaType));
        addParams(builder, params);
        return builder.build();
    }

    /**
     * 添加参数
     * 判断是否能上传文件
     */
    private void addParams(MultipartBody.Builder builder, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    builder.addFormDataPart(key, file.getName(),
                            RequestBody.create(MediaType
                                            .parse(guessMineType(file.getAbsolutePath())),
                                    file));
                } else if (value instanceof List) {
                    //代表提交的是List集合
                    try {
                        List<File> fileList = (List<File>) value;
                        for (int i = 0; i < fileList.size(); i++) {
                            //获取文件
                            File file = fileList.get(i);
                            builder.addFormDataPart(key + i, file.getName(),
                                    RequestBody.create(MediaType
                                                    .parse(guessMineType(file.getAbsolutePath())),
                                            file));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    builder.addFormDataPart(key, String.valueOf(value));
                }
            }
        }
    }

    private String guessMineType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private String getTypeEnum(TypeEnum typeEnum) {
        switch (typeEnum) {
            case XML:
                return "application/xml; charset=utf-8";
            case JSON:
                return "application/json; charset=utf-8";
            case URLENCODED:
                return "application/x-www-form-urlencoded;charset=utf-8";
        }
        return "application/json; charset=utf-8";
    }

    private MediaType getMediaType(TypeEnum typeEnum) {
        switch (typeEnum) {
            case MIXED:
                return MultipartBody.MIXED;
            case ALTERNATIVE:
                return MultipartBody.ALTERNATIVE;
            case DIGEST:
                return MultipartBody.DIGEST;
            case PARALLEL:
                return MultipartBody.PARALLEL;
            case FORM:
                return MultipartBody.FORM;
        }
        return MultipartBody.FORM;
    }
}










