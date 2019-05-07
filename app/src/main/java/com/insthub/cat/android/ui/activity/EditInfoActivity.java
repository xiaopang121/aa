package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.extend.roundedimageview.RoundedImageView;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.helper.GlideCircleTransform;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module.UpdateUserInfoData;
import com.insthub.cat.android.module.UserInfoData;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;


/**
 * 账户信息
 * Created by linux on 2017/6/28.
 */

public class EditInfoActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;


    @Bind(R.id.detail_head)
    RoundedImageView detailHead;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.bt_right)
    Button btRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;
    @Bind(R.id.tv_phone)
    EditText tvPhone;
    @Bind(R.id.tv_name)
    EditText tvName;
    @Bind(R.id.tv_hangye)
    EditText tvHangye;
    @Bind(R.id.tv_zhiwei)
    EditText tvZhiwei;
    @Bind(R.id.tv_bumen)
    EditText tvBumen;
    @Bind(R.id.tv_email)
    EditText tvEmail;


    @Bind(R.id.rb_sex)
    RadioGroup rgSex;
    @Bind(R.id.rb_man)
    RadioButton rbMan;
    @Bind(R.id.rb_femail)
    RadioButton rbFemail;


    private int sex=1;
    private UploadImageInfoData mUploadHeadInfoData;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            dismissLoadDialog();

            if(mUploadHeadInfoData!=null)
            {
//                Glide.with(getActivity())
//                        .load(mUploadHeadInfoData.getData().getFull_path())
//                        // .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .transform(new GlideCircleTransform(getActivity()))
//                        .error(R.drawable.ic_default_head)
//                        .placeholder(R.drawable.ic_default_head)
//                        .into(detailHead);
//


                RequestOptions requestOptions = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_default_head)
                        .error(R.drawable.ic_default_head)
                        ;
                Glide.with(getContext().getApplicationContext()).asBitmap()
                        .load(mUploadHeadInfoData.getData().getFull_path())
                        .apply(requestOptions)
                        .into(detailHead);
            }

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected int bindColorPrimary() {
        return 0;
    }


    @Override
    protected void bindPresenter() {
        super.bindPresenter();
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void bindViewById() {
        super.bindViewById();
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();

    }

    @Override
    protected void bindEvent() {
        super.bindEvent();
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //保存
        btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(tvName.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入姓名");
                    return;
                }

                if(TextUtils.isEmpty(tvHangye.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入行业");
                    return;
                }


                if(TextUtils.isEmpty(tvZhiwei.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入职位");
                    return;
                }

                if(TextUtils.isEmpty(tvBumen.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入部门");
                    return;
                }


                if(TextUtils.isEmpty(tvEmail.getText().toString()))
                {

                    ToastUtil.show(getActivity(),"请输入邮箱");
                    return;
                }


                showLoadDialog("正在提交数据");

                mPresenter.modifyUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),
                        CacheManager.getInstance().getToken().getData().getToken(),
                        mUploadHeadInfoData==null ?  CacheManager.getInstance().getUserInfo().getData().getHead_image():mUploadHeadInfoData.getData().getSave_path(),
                        tvEmail.getText().toString(),
                        tvName.getText().toString(),
                        String.valueOf(sex),
                        tvZhiwei.getText().toString(),
                        tvBumen.getText().toString(),
                        tvHangye.getText().toString());

            }
        });



        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                switch (i)
                {
                    case R.id.rb_man:
                        sex =1;
                        break;
                    case R.id.rb_femail:
                        sex =0;
                        break;
                }
            }
        });


        detailHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeIcon();
            }
        });

    }


    private void showChangeIcon() {

        new ActionSheetDialog(this)
                .builder()
                .setTitle("选择获取图片的方式")
                .setCancelable(false)
                .setCanceledOnTouchOutside(true)
                .addSheetItem("相机", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {


                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机

                                Uri imageUri = null;
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                    imageUri = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", FileUtil.getHeadPhotoFileRaw());
                                } else {
                                    imageUri = Uri.fromFile(FileUtil.getHeadPhotoFileRaw());
                                }
                                List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                                if (resInfoList.size() == 0) {
                                    ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                                    return;
                                }
                                Iterator resInfoIterator = resInfoList.iterator();
                                while (resInfoIterator.hasNext()) {
                                    ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                                    String packageName = resolveInfo.activityInfo.packageName;
                                    KLog.i("packName:" + packageName);
                                    grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                KLog.i("url:" + imageUri.toString());

                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                startActivityForResult(intent, ACTIVITY_CAMERA_REQUESTCODE);


                            }
                        })
                .addSheetItem("图库", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                Intent i = new Intent(Intent.ACTION_PICK, null);// 调用android的图库
                                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                startActivityForResult(i, ACTIVITY_ALBUM_REQUESTCODE);

                            }
                        }).show();

        //          }
        //      });
    }


    /**
     * 上传图片
     */
    public void uploadUserHead(String imagePath) {

        RequestParams params = new RequestParams();
        params.addHeader("Connection", "Keep-Alive");
        // params.addHeader("Content-Type", "multipart/form-data;charset=utf-8");
        params.addBodyParameter("content_type", "head");
        params.addBodyParameter("file",new File(imagePath),"image/png");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, Constants.SERVICE_URL_PREFEX + "upload/save.do", params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                KLog.i("content", s);
                //headImage
                try {
                    Gson gson = new Gson();
                     mUploadHeadInfoData = gson.fromJson(s, UploadImageInfoData.class);
                    mHandler.sendEmptyMessage(0);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {

                dismissLoadDialog();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ACTIVITY_ALBUM_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {
                        cutPhoto(data.getData(), true);
                    }
                    break;
                case ACTIVITY_CAMERA_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {

                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmapOptions.inSampleSize = 2;
                        int degree = FileUtil.readPictureDegree(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW);
                        Bitmap cameraBitmap = BitmapFactory.decodeFile(FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_RAW, bitmapOptions);
                        cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                        FileUtil.saveCutBitmapForCache(this, cameraBitmap);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            cutPhoto(FileProvider.getUriForFile(getApplicationContext(), "com.insthub.cat.android.provider", FileUtil.getHeadPhotoFileRaw()), true);
                        } else {
                            cutPhoto(Uri.fromFile(FileUtil.getHeadPhotoFileRaw()), true);
                        }

                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok
                    String coverPath = FileUtil.getHeadPhotoDir() + FileUtil.HEADPHOTO_NAME_TEMP;

//                    Glide.with(getActivity())
//                            .load(coverPath)
//                            .transform(new GlideCircleTransform(getActivity()))
//                            .error(R.drawable.ic_default_head)
//                            .placeholder(R.drawable.ic_default_head)
//                            .into(detailHead);
//

                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.ic_default_head)
                            .error(R.drawable.ic_default_head)
                            ;
                    Glide.with(getContext().getApplicationContext()).asBitmap()
                            .load(coverPath)
                            .apply(requestOptions)
                            .into(detailHead);


                    showLoadDialog("正在上传头像");
                    // mPresenter.uploadUserHead(coverPath);
                    uploadUserHead(coverPath);

                    break;
            }
        } else {
            dismissLoadDialog();
        }

    }


    /**
     * 裁剪图片
     */
    public void cutPhoto(Uri uri, boolean isHeadPic) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        if (isHeadPic) {
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 200);
            intent.putExtra("outputY", 200);

            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);

            Uri tempUrl = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tempUrl = FileProvider.getUriForFile(getApplicationContext(), "com.insthub.cat.android.provider", FileUtil.getHeadPhotoFileTemp());

            } else {
                tempUrl = Uri.fromFile(FileUtil.getHeadPhotoFileTemp());
            }

            List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resInfoList.size() == 0) {
                ToastUtil.show(getActivity(), "没有合适的相机应用程序");
                return;
            }
            Iterator resInfoIterator = resInfoList.iterator();
            while (resInfoIterator.hasNext()) {
                ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, tempUrl, Intent.FLAG_GRANT_READ_URI_PERMISSION
                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }

            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUrl);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
            intent.putExtra("noFaceDetection", true);
        } else {
            // 是否保留比例
            intent.putExtra("scale", "true");
            intent.putExtra("output", Uri.fromFile(FileUtil.getWallPaperFile()));
        }

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, ACTIVITY_MODIFY_PHOTO_REQUESTCODE);
    }

    @Override
    protected void bindData() {
        super.bindData();


        //设置头像
        if (!TextUtils.isEmpty(CacheManager.getInstance().getUserInfo().getData().getHead_image())) {
//            Glide.with(getActivity())
//                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
//                    // .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .transform(new GlideCircleTransform(getActivity()))
//                    .error(R.drawable.ic_default_head)
//                    .placeholder(R.drawable.ic_default_head)
//                    .into(detailHead);


            RequestOptions requestOptions = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_head)
                    .error(R.drawable.ic_default_head)
                    ;
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load(CacheManager.getInstance().getUserInfo().getData().getHead_image())
                    .apply(requestOptions)
                    .into(detailHead);

        } else {
            detailHead.setImageResource(R.drawable.ic_default_head);
        }


        tvPhone.setText(CacheManager.getInstance().getUserInfo().getData().getPhone());
//
        tvName.setText(CacheManager.getInstance().getUserInfo().getData().getUser_name());

        tvHangye.setText(CacheManager.getInstance().getUserInfo().getData().getTrade());

        tvZhiwei.setText(CacheManager.getInstance().getUserInfo().getData().getPosition());

        tvBumen.setText(CacheManager.getInstance().getUserInfo().getData().getDep());

        tvEmail.setText(CacheManager.getInstance().getUserInfo().getData().getEmail());


        if("0".equals(CacheManager.getInstance().getUserInfo().getData().getSex()))
        {
            rgSex.check(R.id.rb_femail);
        }else if("1".equals(CacheManager.getInstance().getUserInfo().getData().getSex()))
        {
            rgSex.check(R.id.rb_man);
        }

//



    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showSuccess(Object object) {



        if (object instanceof UpdateUserInfoData) {

            mPresenter.getUserInfo(CacheManager.getInstance().getToken().getData().getUser_id(),CacheManager.getInstance().getToken().getData().getToken());
        }


        if (object instanceof UserInfoData) {

            dismissLoadDialog();
            UserInfoData data = (UserInfoData) object;
            CacheManager.getInstance().setUserInfo(data);
            setResult(Activity.RESULT_OK);
            finish();

        }


    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getActivity(), msg);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


}
