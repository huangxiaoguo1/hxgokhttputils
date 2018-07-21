package tsou.cn.lib_hxgokhttp;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.Proxy;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import tsou.cn.lib_hxgokhttp.callback.EngineCallBack;
import tsou.cn.lib_hxgokhttp.interceptor.LogInterceptor;

/**
 * Created by Administrator on 2018/7/19 0019.
 * 封装OkHttp默认引擎
 */

class OkHttpEngine implements IHttpEngine {
    private static OkHttpClient mOkHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(new LogInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .pingInterval(20, TimeUnit.SECONDS)
            .proxy(Proxy.NO_PROXY)
            .build();

    public static void callAll(){
        mOkHttpClient.dispatcher().cancelAll();
    }
    public static void init(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    @Override
    public void get(Context context, String url, Map<String, Object> header,
                    Map<String, Object> params, final EngineCallBack callBack) {
        String jointUrl = URLUtil.jointParams(url, params);
        Request.Builder requestBuilder = new Request.Builder()
                .url(jointUrl)
                .tag(context);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, Object> entry : header.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = requestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void post(Context context, String url, Map<String, Object> header, Map<String, Object> params,
                     final EngineCallBack callBack) {
        RequestBody requestBody = appendBody(params);
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
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void put(Context context, String url, Map<String, Object> header, Map<String, Object> params, final EngineCallBack callBack) {
        RequestBody requestBody = appendBody(params);
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
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void delete(Context context, String url, Map<String, Object> header, Map<String, Object> params, final EngineCallBack callBack) {
        RequestBody requestBody = appendBody(params);
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
        mOkHttpClient.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody responseBody = response.body();
                        callBack.onSuccess(responseBody);
                    }
                }
        );
    }

    @Override
    public void post(Context context, String url, Map<String, Object> header, String json, final EngineCallBack callBack) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, json);
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
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void put(Context context, String url, Map<String, Object> header, String json, final EngineCallBack callBack) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, json);
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
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                callBack.onSuccess(responseBody);
            }
        });
    }

    @Override
    public void delete(Context context, String url, Map<String, Object> header, String json, final EngineCallBack callBack) {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");//"类型,字节码"
        RequestBody requestBody = RequestBody.create(mediaType, json);
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
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onError(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
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
    private RequestBody appendBody(Map<String, Object> params) {
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
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


}










