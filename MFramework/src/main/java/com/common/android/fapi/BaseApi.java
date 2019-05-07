package com.common.android.fapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.common.android.flog.KLog;
import com.common.android.futils.CheckSumBuilder;
import com.common.android.futils.DeviceUtils;
import com.common.android.futils.NetWorkUtil;
import com.common.android.futils.SSLSocketFactoryUtils;
import com.common.test.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by macbook on 16/6/12.
 */
public abstract  class BaseApi<T> {


    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;


    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";


    public   T mIConnection;
    private static final String TAG = "Retrofit-Connection";
    private static final String UTF8 = "UTF-8";
    private boolean isDebug=true;

    //配置服务器地址
    protected abstract  String  configServerHttpUrl();
    //配置HTTP操作⌚️
    protected abstract  Class<T>  configServerEvent();

    //是否开启HTTPS
    protected abstract  boolean isOpenHttps();

    //HTTPS证书
    protected abstract  int httpscertificateSource();


    //配置http头
    protected abstract HashMap<String,String> configHeaderKV();


    private static volatile OkHttpClient sOkHttpClient;

    private Context context;



    public BaseApi(Context context)
    {

        this.context = context;
        if (null == mIConnection) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(configServerHttpUrl())
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
            mIConnection = retrofit.create(configServerEvent());
        }




    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (BaseApi.class) {
                Cache cache = new Cache(new File(App.getAppContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {


                    if(isOpenHttps())
                    {
                        try
                        {

                            SSLSocketFactory sslSocketFactory = SSLSocketFactoryUtils.getSSLSocketFactory_Certificate(context,"BKS",httpscertificateSource());

                            sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                                    .connectTimeout(30, TimeUnit.SECONDS)
                                    .sslSocketFactory(sslSocketFactory)
                                    .readTimeout(30, TimeUnit.SECONDS)
                                    .writeTimeout(30, TimeUnit.SECONDS)
                                    .addInterceptor(mRewriteCacheControlInterceptor)
                                    .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                                    .hostnameVerifier(new HostnameVerifier() {
                                        @Override
                                        public boolean verify(String s, SSLSession sslSession) {
                                            return true;
                                        }
                                    })
                                    .addInterceptor(mLoggingInterceptor).build();



                        }catch (Exception e)
                        {
                          e.printStackTrace();
                        }
                    }else
                    {
                        sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .addInterceptor(mRewriteCacheControlInterceptor)
                                .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                                .addInterceptor(mLoggingInterceptor).build();

                    }

                }
            }
        }
        return sOkHttpClient;
    }





    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();


            if (!NetWorkUtil.isNetConnected(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                KLog.d("no network");
            }



          //  Response originalResponse = chain.proceed(request);




            if (NetWorkUtil.isNetConnected(context)) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                String url = request.url().toString();

                Request.Builder builder = request.newBuilder();

                if(!url.contains("getYachtLists"))
                {
                    builder.addHeader("Cache-Control", cacheControl);
                    builder.addHeader("content-type", "application/json;charset=utf-8");
                    builder.addHeader("Accept", "application/json");
                    builder.addHeader("Connection", "Keep-Alive");
                    HashMap<String,String> headkv = configHeaderKV();
                    if(headkv!=null)
                    {
                        Set<String> set = headkv.keySet();
                        Iterator<String> iterator = set.iterator();
                        while (iterator.hasNext())
                        {
                            String key = iterator.next();
                            builder.addHeader(key, headkv.get(key));
                        }

                    }

                }


                Request request2 = builder.build();
                return chain.proceed(request2);

            } else {
                  Response originalResponse = chain.proceed(request);
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            KLog.i(String.format("Sending request: %s on %s%n%s", request.url(), chain.connection(), request.headers(),request.body().toString()));
            String method=request.method();
            if("POST".equals(method)){
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    KLog.i("POST_PARAM:"+sb.toString());
                }
            }

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            KLog.i(String.format(Locale.getDefault(), "Received response: for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            ResponseBody body = response.body();
            BufferedSource source = body.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            Charset charset = Charset.defaultCharset();
            MediaType contentType = body.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String bodyString = buffer.clone().readString(charset);

            KLog.i(bodyString);
            return response;
        }
    };



    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public String getCacheControl() {
        return NetWorkUtil.isNetConnected(context) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }



    public T getConnection()
    {
        return mIConnection;
    }

}
