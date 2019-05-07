package com.insthub.cat.android.ui.activity;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.common.android.fsp.SharedPreferencesUtil;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;

/**
 * 启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setSwipeBackEnable(false);
    }

    @Override
    protected void bindData() {

        // 开启欢迎动画
        startAnimation();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }



    private void startAnimation() {
        final ImageView splashIv = (ImageView) findViewById(R.id.iv_alphe_anim01);

        final float rate = ScreenInfo.getScreenHeight(this)*0.6f/ UIUtil.dpToPx(getResources(),5);
        ValueAnimator animator = ValueAnimator.ofObject(new FloatEvaluator(), 0.1f, 1);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                if (value != 1.0) {
                    splashIv.setScaleX(value);
                    splashIv.setScaleY(value);
                } else {
                    goToActivity();
                }
            }


        });
        animator.start();
    }





    private void goToActivity() {
        overridePendingTransition(0, android.R.anim.fade_out);
        Intent intent =getIntent();
        Uri uri =intent.getData();
        if(intent!=null && uri!=null )
        {


            String path = uri.getPath();


            if(path.contains("storedetail"))
            {

                String lineId = uri.getQueryParameter("store_id");
                if(!TextUtils.isEmpty(lineId))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantsKeys.KEY_DATA_STORE_ID,lineId);
                    startActivity(MainActivity.class,bundle);
                }
            }

            if(path.contains("servicedetail"))
            {
                String service_id = uri.getQueryParameter("service_id");

                if(!TextUtils.isEmpty(service_id))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantsKeys.KEY_DATA_SERVICE_ID,service_id);
                    startActivity(MainActivity.class,bundle);
                }


            }

            if(path.contains("jingjiadetail"))
            {
                String tender_id = uri.getQueryParameter("tender_id");


                if(!TextUtils.isEmpty(tender_id))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantsKeys.KEY_DATA_JJ_TENDER_ID,tender_id);
                    startActivity(MainActivity.class,bundle);
                }
            }

            if(path.contains("zhaobiaodetail"))
            {
                String tender_id = uri.getQueryParameter("tender_id");
                if(!TextUtils.isEmpty(tender_id))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantsKeys.KEY_DATA_ZB_TENDER_ID,tender_id);
                    startActivity(MainActivity.class,bundle);
                }
            }

            if(path.contains("zhengjidetail"))
            {
                String tender_id = uri.getQueryParameter("tender_id");
                if(!TextUtils.isEmpty(tender_id))
                {
                    Bundle bundle = new Bundle();
                    bundle.putString(ConstantsKeys.KEY_DATA_ZJ_TENDER_ID,tender_id);
                    startActivity(MainActivity.class,bundle);
                }
            }



        }else
        {
            startActivity(MainActivity.class);
        }

        defaultFinish();

    }


}
