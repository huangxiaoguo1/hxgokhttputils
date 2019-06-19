# hxgokhttputils

#### 引入方法 

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
	    implementation 'com.github.huangxiaoguo1:hxgokhttputils:1.2.2'
}


```

#### 引擎介绍

    默认使用的是Okhttp引擎，当然这里我们也可以自己配置Okhttp,默认日志拦截器LogInterceptor，默认不许使用代理、绕过证书认证
   
###### get请求
```
简单范例

                Map<String, Object> fields = new ArrayMap<>();
                fields.put("dicFlag", "wsd.grade.type");
                HxgHttpUtils.with(this)
                        .get()
                        .addHeader("dicFlag", "wsd.grade.type")
                        .url("http://********/wsd/dictionary/getMapByDicflag")
                        .addParam(fields)
                        .execeute(new DefaultDialogCallBack<WsdBean>() {

                            @Override
                            public void onHxgSuccess(WsdBean result) {
                                Log.e("huangxiaoguo", "result==>" + result.toString());
                            }

                            @Override
                            public void onHxgFail(Exception e) {

                            }
                        });

```
###### post请求

简单范例

```

                Map<String, Object> field = new ArrayMap<>();
                field.put("currentPage", "1");
                field.put("pageSize", "15");
                field.put("indexFlag", "1");
                HxgHttpUtils.with(this)
                        .post()
                        .url("https://*****.1035.mobi/app/course/index.do")
                        .addHeader(field)
                        .addParam(field)
                        .execeute(new DefaultHttpCallBack<StudyBean>() {

                            @Override
                            public void onSuccess(StudyBean result) {
                                Log.e("huangxiaoguo", "result==>" + result.toString());

                            }

                            @Override
                            public void onFail(Exception e) {

                            }
                        });
     这里header 请求头，请自己配置 
```

###### 下载图片（bitmap）

```
            HxgHttpUtils.with(this)
                        .url("http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg")
                        .get()
                        .execeute(new BitmapCallBack() {
                            @Override
                            public void onSuccess(Bitmap bitmap) {
                                mImage.setImageBitmap(bitmap);
                            }

                            @Override
                            public void onFail(Exception e) {

                            }
                        });

```
 ######  下载文件
 
 
 ```
                //使用前打开手机读取权限
                 String url = "https://******.1035.mobi/shopPic/upload/ppt/7.pptx";
                 HxgHttpUtils.with(this)
                         .url(url)
                         .get()
                         .execeute(new DownLoadFileCallBack(url) {
                             @Override
                             public void onSuccess(String path) {
                                 Log.e("huangxiaoguo", "下载完成==》" + path);
                             }
 
                             @Override
                             public void onFail(Exception e) {
                                 e.printStackTrace();
                                 Log.e("huangxiaoguo", "下载失败");
                             }
 
                             @Override
                             public void onProgress(int progress) {
                                 Log.e("huangxiaoguo", "当前进度==》" + progress);
                             }
                         });
 ```
    
###### 上传文件

```
                File file = new File("/storage/emulated/0/huangxiaoguo/7.pptx");
                HxgHttpUtils.with(this)
                        .url("http://******/wsd/fileoss/saveFile")
                        .post()
                        .addParam("myfiles", file)
                        .execeute(new StringEngineCallBack() {
                            @Override
                            public void onSuccess(String resultJson) {
                                Log.e("huangxiaoguo", "上传文件=》" + resultJson);
                            }

                            @Override
                            public void onFail(Exception e) {
                                e.printStackTrace();
                                Log.e("huangxiaoguo", "上传文件失败");
                            }
                        });
```

### 传入自己的Okhttp配置



```
    /**
     * 可以自己传入OkHttpClient并添加自己的配置
     */
    private void init() {
        File httpCacheDir = new File(UIUtils.getContext().getExternalCacheDir(), "xiaoguonet");
        Cache cache = new Cache(httpCacheDir, 10 * 1024 * 1024);
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addNetworkInterceptor(new CacheInterceptor())
             // .addInterceptor(new LogInterceptor())
                .addInterceptor(new LogBitmapInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .pingInterval(20, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY)
                .build();
        HxgHttpUtils.init(mOkHttpClient);
    }

```
### 默认Dialog

    详细参考
    DefaultDialogCallBack, DialogStringCallBack

### 初始化其他的引擎

```
HxgHttpUtils.init(IHttpEngine httpEngine);
```
### 每次请求都可以自带引擎

```
HxgHttpUtils.exchangeEngine(IHttpEngine httpEngine);
```

### 取消网络请求

```
    HxgHttpUtils.callAll();
```

### 方法简介

###### with(Context context)
    
    传入上下文
    
###### post()

    post请求
    
###### get()

    get请求
    
###### put()

    put请求
    
###### delete()

    delete请求
    
###### addParam(String key, Object value)

    一个一个添加参数
    
###### addParam(Map<String, Object> params)

    添加参数集合
 
###### addHeader(String key, Object value)

    添加请求头
  
###### addHeader(Map<String, Object> params)

    添加请求头
    
###### addJson(String json)

    json方式提交 对get请求无效
    
###### execeute(EngineCallBack callBack)

    添加回调
    
###### execeute()

    空回调
    
###### cancelTag(Object tag)

    取消网络请求（tag为with(Context context)传入的上下文）
    
###### callAll()

    取消网络请求
    
