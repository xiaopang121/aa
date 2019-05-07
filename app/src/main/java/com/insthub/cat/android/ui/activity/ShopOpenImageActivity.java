package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.module2.UpdateStoreData;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.utils.FileUtil;
import com.insthub.cat.android.utils.PhotoUtil;
import com.insthub.cat.android.utils.ScreenUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;


/**
 * 开店---填写信息
 * Created by linux on 2017/6/28.
 */

public class ShopOpenImageActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {


    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;
    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    @Bind(R.id.iv_image1)
    ImageView ivImage1;
    @Bind(R.id.iv_image2)
    ImageView ivImage2;
    @Bind(R.id.iv_image3)
    ImageView ivImage3;
    @Bind(R.id.iv_image4)
    ImageView ivImage4;

    @Bind(R.id.iv_image5)
    ImageView ivImage5;

    @Bind(R.id.iv_clear1)
    ImageView ivClear1;
    @Bind(R.id.iv_clear2)
    ImageView ivClear2;
    @Bind(R.id.iv_clear3)
    ImageView ivClear3;

    @Bind(R.id.iv_clear4)
    ImageView ivClear4;


    @Bind(R.id.iv_clear5)
    ImageView ivClear5;


    ArrayList<ImageView> imageViews = new ArrayList<>();
    private int item;
    private String imagePath="";

    private boolean isAdd=false;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            dismissLoadDialog();
            refreshvIEW();


        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_shop_image;
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
        commonTitleBar.setTitle("添加照片");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        commonTitleBar.setRighButtonTitle(R.string.title_finish);
        imageViews.add(ivImage1);
        imageViews.add(ivImage2);
        imageViews.add(ivImage3);
        imageViews.add(ivImage4);
        imageViews.add(ivImage5);
        ArrayList<String> images = getIntent().getStringArrayListExtra("images");
        ArrayList<String> fullimages = getIntent().getStringArrayListExtra("fullimages");
        for(int x=0;x<images.size();x++)
        {
            imageViews.get(x).setTag(R.id.image_data,new UploadImageInfoData.DataBean(images.get(x),fullimages.get(x)));
            RequestOptions requestOptions4 = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_add_grey_600)
                    .error(R.drawable.ic_add_grey_600);
            Glide.with(getContext()).asBitmap()
                    .load(fullimages.get(x))
                    .apply(requestOptions4)
                    .into(imageViews.get(x));
        }

        refreshvIEW();
    }

    @Override
    protected void bindEvent() {
        super.bindEvent();

        commonTitleBar.ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item = 0;
                showChangeIcon();
            }
        });

        ivImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = 1;
                showChangeIcon();
            }
        });


        ivImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = 2;
                showChangeIcon();
            }
        });

        ivImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = 3;
                showChangeIcon();
            }
        });

        ivImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = 4;
                showChangeIcon();
            }
        });


        ivClear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage1.setTag(R.id.image_data,null);
                ivImage1.setImageResource(R.drawable.ic_add_grey_600);

                refreshvIEW();

            }
        });

        ivClear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage2.setTag(R.id.image_data,null);
                ivImage2.setImageResource(R.drawable.ic_add_grey_600);
                refreshvIEW();
            }
        });

        ivClear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage3.setTag(R.id.image_data,null);
                ivImage3.setImageResource(R.drawable.ic_add_grey_600);
                refreshvIEW();
            }
        });

        ivClear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage4.setTag(R.id.image_data,null);
                ivImage4.setImageResource(R.drawable.ic_add_grey_600);
                refreshvIEW();
            }
        });

        ivClear5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImage5.setTag(R.id.image_data,null);
                ivImage5.setImageResource(R.drawable.ic_add_grey_600);
                refreshvIEW();
            }
        });


        commonTitleBar.btRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ArrayList<String> imagefullPath = new ArrayList<>();
                ArrayList<String> imagePath = new ArrayList<>();
                for (int x=0;x<imageViews.size();x++)
                {

                    if(imageViews.get(x).getTag(R.id.image_data)!=null)
                    {
                        UploadImageInfoData.DataBean data = (UploadImageInfoData.DataBean)imageViews.get(x).getTag(R.id.image_data);
                        imagefullPath.add(data.getFull_path());
                        imagePath.add(data.getSave_path());

                    }

                }

                if(imagePath.size()<3){
                    ToastUtil.show(getActivity(),"最少需要添加3张图");
                    return ;
                }

                Intent intent = new Intent();
                intent.putExtra("images",imagePath);
                intent.putExtra("fullimages",imagefullPath);
                setResult(Activity.RESULT_OK,intent);
                finish();


            }
        });

    }





    private void refreshvIEW()
    {


        ivClear1.setVisibility(View.GONE);
        ivClear2.setVisibility(View.GONE);
        ivClear3.setVisibility(View.GONE);
        ivClear4.setVisibility(View.GONE);
        ivClear5.setVisibility(View.GONE);

        for(int x=0;x<imageViews.size();x++)
        {

            if(imageViews.get(x).getTag(R.id.image_data)!=null)
            {
                switch (x)
                {
                    case 0:
                        ivClear1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        ivClear2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ivClear3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ivClear4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        ivClear5.setVisibility(View.VISIBLE);
                        break;
                }

            }
        }




    }



    @Override
    protected void bindData() {
        super.bindData();


    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {


        dismissLoadDialog();

        if (object instanceof UpdateStoreData) {

            setResult(Activity.RESULT_OK);
            finish();
        }

    }

    @Override
    public void showError(String msg, int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(), msg);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }

    private void showChangeIcon() {

        getPhotoPath();
        new ActionSheetDialog(getActivity())
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
                                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
                                {

                                    imageUri = FileProvider.getUriForFile(getActivity(),  "com.insthub.cat.android.provider", new File(imagePath));
                                }else
                                {
                                    imageUri = Uri.fromFile( new File(imagePath));
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
                                    KLog.i("packName:"+packageName);
                                    grantUriPermission(packageName, imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                                }
                                KLog.i("url:"+imageUri.toString());

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
        params.addBodyParameter("content_type", "banner");
        params.addBodyParameter("file", new File(imagePath), "image/png");

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST, Constants.SERVICE_URL_PREFEX + "upload/save.do", params, new RequestCallBack<String>() {

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String s = responseInfo.result;
                KLog.i("content", s);
                //headImage
                try {
                    Gson gson = new Gson();
                    UploadImageInfoData  mUploadImageInfoData1 = gson.fromJson(s, UploadImageInfoData.class);
                    imageViews.get(item).setTag(R.id.image_data,mUploadImageInfoData1.getData());
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

        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode) {
                case ACTIVITY_ALBUM_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {
                        //cutPhoto(data.getData(), true);

                        cutPhoto1(getPath(data.getData()));


                    }
                    break;
                case ACTIVITY_CAMERA_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {

                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmapOptions.inSampleSize = 2;
                        int degree = FileUtil.readPictureDegree(imagePath);
                        Bitmap cameraBitmap = BitmapFactory.decodeFile(imagePath, bitmapOptions);
                        cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                        FileUtil.saveCutBitmapForCache(this, cameraBitmap);
//                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
//                        {
//                            cutPhoto(FileProvider.getUriForFile(getActivity(),  "com.insthub.cat.android.provider", new File(imagePath)), true);
//                        }else
//                        {
//                            cutPhoto(Uri.fromFile( new File(imagePath)), true);
//                        }

                        cutPhoto2(imagePath);



                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok
                    imagePath = data.getStringExtra("path");

                    RequestOptions requestOptions = new RequestOptions()
                            .centerCrop()
                            .placeholder(R.drawable.ic_add_grey_600)
                            .error(R.drawable.ic_add_grey_600);


                    Glide.with(getContext().getApplicationContext()).asBitmap()
                            .load(imagePath)
                            .apply(requestOptions)
                            .into(imageViews.get(item));

                    showLoadDialog("正在上传头像");
                    uploadUserHead(imagePath);




                    break;
            }
        }else
        {
            dismissLoadDialog();
        }

    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public void cutPhoto1(String  path) {

        KLog.i("path："+path);

        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", path);
        startActivityForResult(intent,ACTIVITY_MODIFY_PHOTO_REQUESTCODE);

    }


    public void cutPhoto2(String  path) {

        KLog.i("path："+path);

        Intent intent = new Intent(this, PhotoClipActivity.class);
        intent.putExtra("path", imagePath);
        startActivityForResult(intent,ACTIVITY_MODIFY_PHOTO_REQUESTCODE);

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
            intent.putExtra("aspectX", 10);
            intent.putExtra("aspectY", 7);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", ScreenUtils.getScreenWidth(getActivity()));
            intent.putExtra("outputY", ScreenUtils.getScreenWidth(getActivity())/2);

            intent.putExtra("scale", true);
            //只能设置成false，k920无法返回
            intent.putExtra("return-data", false);

            Uri tempUrl=null;
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
            {
                tempUrl=FileProvider.getUriForFile(getActivity(),  "com.insthub.cat.android.provider", new File(imagePath));

            }else
            {
                tempUrl = Uri.fromFile(new File(imagePath));
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

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(intent, ACTIVITY_MODIFY_PHOTO_REQUESTCODE);
    }



    public void  getPhotoPath()
    {

        if(!TextUtils.isEmpty(imagePath))
        {

            try
            {
                File file = new File(imagePath);
                file.delete();

            }catch (RuntimeException e)
            {
                e.printStackTrace();
            }

        }

        imagePath = FileUtil.getHeadPhotoDir()+System.currentTimeMillis()+".png";
    }

}
