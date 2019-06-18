package tsou.cn.hxgokhttputils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import tsou.cn.hxgokhttputils.bean.ConsultingListBean;
import tsou.cn.hxgokhttputils.bean.WsdBean;
import tsou.cn.lib_hxgokhttp.HxgHttpUtils;
import tsou.cn.lib_hxgokhttp.callback.BitmapCallBack;
import tsou.cn.lib_hxgokhttp.callback.DefaultDialogCallBack;
import tsou.cn.lib_hxgokhttp.callback.DefaultHttpCallBack;
import tsou.cn.lib_hxgokhttp.callback.DownLoadFileCallBack;
import tsou.cn.lib_hxgokhttp.callback.StringEngineCallBack;
import tsou.cn.lib_hxgokhttp.interceptor.CacheInterceptor;
import tsou.cn.lib_hxgokhttp.interceptor.LogBitmapInterceptor;
import tsou.cn.lib_hxgokhttp.interceptor.LogInterceptor;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * GET请求
     */
    private Button mGet;
    /**
     * POST请求
     */
    private Button mPost;

    /**
     * 下载图片
     */
    private Button mBitmap;
    private ImageView mImage;
    /**
     * 下载文件
     */
    private Button mFile;
    /**
     * 上传文件
     */
    private Button mUpfile;

    @Override
    protected void onDestroy() {
        HxgHttpUtils.callAll();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }

    /**
     * 可以自己传入OkHttpClient并添加自己的配置
     */
    private void init() {
        File httpCacheDir = new File(UIUtils.getContext().getExternalCacheDir(), "xiaoguonet");
        Cache cache = new Cache(httpCacheDir, 10 * 1024 * 1024);
        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
//                .cache(cache)
//                .addNetworkInterceptor(new CacheInterceptor(UIUtils.getContext()))
                 .addInterceptor(new LogInterceptor())
//                .addInterceptor(new LogBitmapInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .pingInterval(20, TimeUnit.SECONDS)
//                .proxy(Proxy.NO_PROXY)
                .build();
//        HxgHttpUtils.init(mOkHttpClient);
    }

    private void initView() {
        mGet = (Button) findViewById(R.id.get);
        mGet.setOnClickListener(this);
        mPost = (Button) findViewById(R.id.post);
        mPost.setOnClickListener(this);
        mBitmap = (Button) findViewById(R.id.bitmap);
        mBitmap.setOnClickListener(this);
        mImage = (ImageView) findViewById(R.id.image);
        mFile = (Button) findViewById(R.id.file);
        mFile.setOnClickListener(this);
        mUpfile = (Button) findViewById(R.id.upfile);
        mUpfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.get:
                Map<String, Object> fields = new ArrayMap<>();
                fields.put("dicFlag", "wsd.grade.type");
                HxgHttpUtils.with(this)
                        .get()
                        .addHeader("dicFlag", "wsd.grade.type")
                        .url("http://101.37.168.221/wsd/dictionary/getMapByDicflag")
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
                break;
            case R.id.post:
                Map<String, Object> field = new ArrayMap<>();
//                field.put("currentPage", "1");
//                field.put("pageSize", "15");
//                field.put("indexFlag", "1");
//                field.put("action", "App/Lawyer/consultXq");
//                field.put("value", "eyJpZCI6IjQxIn0=");
                field.put("action", "App/Lawyer/consult");
                field.put("value", "eyJwIjoiMSIsInR5cGUiOiIxIn0=");
                HxgHttpUtils.with(this)
                        .post()
//                        .url("https://university.1035.mobi/app/course/index.do")
                        .url("http://www.huirongfa.com/App/Index/api")
//                        .addHeader(field)
                        .addParam(field)
                        .execeute(new DefaultHttpCallBack<ConsultingListBean>() {

                            @Override
                            public void onSuccess(ConsultingListBean result) {
                                Log.e("huangxiaoguo", "result==>" + result.toString());

                            }

                            @Override
                            public void onFail(Exception e) {
                                e.printStackTrace();
                                UIUtils.showToast("服务器有误");
                            }
                        });
                break;
            case R.id.bitmap:
                HxgHttpUtils.with(this)
                        .url("http://k.zol-img.com.cn/sjbbs/7692/a7691515_s.jpg")
                        .get()
                        .execeute(new BitmapCallBack() {

                            @Override
                            public void onFail(Exception e) {

                            }

                            @Override
                            public void onSuccess(Bitmap bitmap) {
                                mImage.setImageBitmap(bitmap);
                            }
                        });
                break;
            case R.id.file:
                //使用前打开手机读取权限
                String url = "https://qingtian.1035.mobi/shopPic/upload/ppt/7.pptx";
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
                break;
            case R.id.upfile:
                File file = new File("/storage/emulated/0/huangxiaoguo/7.pptx");
                HxgHttpUtils.with(this)
                        .url("http://101.37.168.221/wsd/fileoss/saveFile")
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
                break;
        }
    }

}

