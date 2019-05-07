package com.insthub.cat.android.utils;


import android.support.annotation.NonNull;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.vector.update_app.HttpManager;

import java.io.File;
import java.util.Map;

/**
 * Created by Vector
 * on 2017/6/19 0019.
 */

public class UpdateAppHttpUtil implements HttpManager {
    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {

//        RequestParams
//        HttpUtils http = new HttpUtils();
//        http.send(HttpRequest.HttpMethod.GET, url, params, new RequestCallBack<String>() {
//
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                String s = responseInfo.result;
//                callBack.onResponse(s);
//
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                callBack.onError(s);
//            }
//        });

    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final Callback callBack) {
//        OkHttpUtils.post()
//                .url(url)
//                .params(params)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Response response, Exception e, int id) {
//                        callBack.onError(validateError(e, response));
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        callBack.onResponse(response);
//                    }
//                });

    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final FileCallback callback) {
        callback.onBefore();

        HttpUtils http = new HttpUtils();
        http.download(url, path, true, new RequestCallBack<File>() {
            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                callback.onResponse(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callback.onError(s);
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                callback.onProgress(current, total);
                System.out.println("total:"+total+"---------"+current);
            }
        });



//        OkHttpUtils.get()
//                .url(url)
//                .build()
//                .execute(new FileCallBack(path, fileName) {
//                    @Override
//                    public void inProgress(float progress, long total, int id) {
//                        callback.onProgress(progress, total);
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e, int id) {
//                        callback.onError(validateError(e, response));
//                    }
//
//                    @Override
//                    public void onResponse(File response, int id) {
//                        callback.onResponse(response);
//
//                    }
//
//                    @Override
//                    public void onBefore(Request request, int id) {
//                        super.onBefore(request, id);
//                        callback.onBefore();
//                    }
//                });

    }
}