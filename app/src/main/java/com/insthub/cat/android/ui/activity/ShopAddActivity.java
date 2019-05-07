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
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.MyStoreData;
import com.insthub.cat.android.module2.StoreDetialData;
import com.insthub.cat.android.module2.UpdateStoreData;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;


/**
 * 开店---填写信息
 * Created by linux on 2017/6/28.
 */

public class ShopAddActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View {

    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;
    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.iv_upload_img)
    ImageView ivUploadImg;

    @Bind(R.id.et_shop_name)
    EditText etShopName;

    @Bind(R.id.tv_service_type)
    TextView tvServiceType;

    @Bind(R.id.rll_select_category)
    LinearLayout rllSelectCategory;

    @Bind(R.id.et_service_price)
    EditText etServicePrice;

    @Bind(R.id.et_shop_goodness)
    TextView etShopGoodness;

    @Bind(R.id.lly_goodness)
    LinearLayout llyGoodness;

    @Bind(R.id.et_shop_address)
    EditText etShopAddress;
    @Bind(R.id.et_shop_contact)
    EditText etShopContact;
    @Bind(R.id.et_shop_phone)
    EditText etShopPhone;
    @Bind(R.id.et_shop_bankname)
    EditText etShopBankname;
    @Bind(R.id.et_shop_bankcard)
    EditText etShopBankcard;

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


    ArrayList<ImageView> imageViews = new ArrayList<>();
    private ArrayList<UploadImageInfoData.DataBean> bannerImages = new ArrayList<>();



    @Bind(R.id.ex_image1)
    ImageView exImage1;
    @Bind(R.id.ex_image2)
    ImageView exImage2;
    @Bind(R.id.ex_image3)
    ImageView exImage3;
    @Bind(R.id.ex_image4)
    ImageView exImage4;
    @Bind(R.id.ex_image5)
    ImageView exImage5;

    ArrayList<ImageView> eximageViews = new ArrayList<>();
    private ArrayList<UploadImageInfoData.DataBean> exImageList = new ArrayList<>();


    @Bind(R.id.bt_preview)
    Button btPreview;


    @Bind(R.id.bt_publish)
    Button btPublish;

    private MyStoreData.DataBean dataBean;
    private DiscoverLabelData.DataBean.LabelLv1Bean  tagList;
    //头像路径
    private UploadImageInfoData logo;
    private String imagePath="";


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
        return R.layout.activity_shop_step1;
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
        commonTitleBar.setTitle(R.string.title_shop_step1);
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        imageViews.add(ivImage1);
        imageViews.add(ivImage2);
        imageViews.add(ivImage3);
        imageViews.add(ivImage4);
        imageViews.add(ivImage5);



        eximageViews.add(exImage1);
        eximageViews.add(exImage2);
        eximageViews.add(exImage3);
        eximageViews.add(exImage4);
        eximageViews.add(exImage5);


        try
        {
            dataBean = (MyStoreData.DataBean)getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);

        }catch (RuntimeException e)
        {
            dataBean = null;
        }



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


        ivUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeIcon();
            }
        });


        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startImage();
            }
        });

        ivImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startImage();
            }
        });

        ivImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startImage();
            }
        });

        ivImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startImage();
            }
        });
        ivImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startImage();
            }
        });









        exImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startexImage();
            }
        });


        exImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startexImage();
            }
        });


        exImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startexImage();
            }
        });


        exImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startexImage();
            }
        });


        exImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startexImage();
            }
        });







        rllSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(  ShopCategoryListActivity.class,100);
            }
        });



        //优势
        llyGoodness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(),ShopGoodNessActvity.class);
                intent.putExtra("content",etShopGoodness.getText().toString());
                startActivityForResult(intent,200);
            }
        });



        //店铺预览
        btPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StoreDetialData.DataBean deital = new StoreDetialData.DataBean();



                ArrayList<String> bannerList  = new ArrayList<>();

                if(bannerImages.size()>0)
                {
                    for(int x=0;x<bannerImages.size();x++)
                    {
                        bannerList.add(bannerImages.get(x).getFull_path());
                    }
                }

                deital.setBanner_list(bannerList);



                ArrayList<String> otherList  = new ArrayList<>();

                if(exImageList.size()>0)
                {
                    for(int x=0;x<exImageList.size();x++)
                    {
                        otherList.add(exImageList.get(x).getFull_path());
                    }
                }
                deital.setImage_list(otherList);


                deital.setAddress(etShopAddress.getText().toString());
                deital.setService_name(tvServiceType.getText().toString());

                if(!TextUtils.isEmpty(etServicePrice.getText().toString()))
                {
                    deital.setPrice(Float.valueOf(etServicePrice.getText().toString()));
                }

                deital.setService_advantage(etShopGoodness.getText().toString());
                deital.setStore_name(etShopName.getText().toString());


                StoreDetialData data = new StoreDetialData();

                data.setData(deital);

                Bundle bundle = new Bundle();
                bundle.putSerializable(ConstantsKeys.KEY_DATA,data);
                startActivity(ShopPreviewActivity.class,bundle);
            }
        });



        btPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(logo==null)
                {
                    ToastUtil.show(getContext(),"请设置头像");
                    return ;
                }

                if(TextUtils.isEmpty(etShopName.getText().toString()))
                {
                    ToastUtil.show(getContext(),"请输入店铺名字");
                    return ;
                }

                if(TextUtils.isEmpty(tvServiceType.getText().toString()))
                {
                    ToastUtil.show(getContext(),"请选择服务类型");
                    return ;
                }

                if(TextUtils.isEmpty(etServicePrice.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入服务价格");
                    return ;
                }

                if(TextUtils.isEmpty(etShopGoodness.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入服务优势");
                    return ;
                }


                if(TextUtils.isEmpty(etShopAddress.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入详细地址");
                    return ;
                }

                if(TextUtils.isEmpty(etShopContact.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入联系人");
                    return ;
                }


                if(TextUtils.isEmpty(etShopPhone.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入手机号码或者固定电话");
                    return ;
                }

                if(TextUtils.isEmpty(etShopBankname.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入开户行");
                    return ;
                }


                if(TextUtils.isEmpty(etShopBankcard.getText().toString()))
                {

                    ToastUtil.show(getContext(),"请输入银行卡号");
                    return ;
                }


                if(bannerImages.isEmpty())
                {
                    ToastUtil.show(getContext(),"请上传店铺详情图片");
                    return ;
                }



                if(tagList==null)
                {
                    ToastUtil.show(getContext(),"请选择服务类型");
                    return ;
                }


                //设置标签
                StringBuffer tagId =new StringBuffer();
                StringBuffer serviceContent = new StringBuffer();
                String childTags="";
                String childContent="";
                if(tagList!=null)
                {

                    for (int x=0;x<tagList.getLabel_lv2().size();x++)
                    {
                        if(tagList.getLabel_lv2().get(x).isSelect())
                        {
                            tagId.append(tagList.getLabel_lv2().get(x).getLabel_id());
                            tagId.append(",");

                            serviceContent.append(tagList.getLabel_lv2().get(x).getLable_name());
                            serviceContent.append(",");
                        }
                    }
                    childTags = tagId.toString().substring(0,tagId.length()-1);
                    childContent = serviceContent.toString().substring(0,tagId.length()-1);
                }



                //图片
                StringBuffer sbBanner = new StringBuffer();
                if(bannerImages.size()>0)
                {
                    for(int x = 0; x< bannerImages.size(); x++)
                    {
                        sbBanner.append(bannerImages.get(x).getSave_path());
                        if(x!= bannerImages.size()-1)
                        {
                            sbBanner.append(",");
                        }
                    }
                }


                //图片
                StringBuffer sbDetiaoImage = new StringBuffer();
                if(exImageList.size()>0)
                {
                    for(int x = 0; x< exImageList.size(); x++)
                    {
                        sbDetiaoImage.append(exImageList.get(x).getSave_path());
                        if(x!= exImageList.size()-1)
                        {
                            sbDetiaoImage.append(",");
                        }
                    }
                }




                showLoadDialog("正在保存数据");

                mPresenter.saveStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                         CacheManager.getInstance().getToken().getData().getToken(),
                         logo.getData().getSave_path(),
                         etShopName.getText().toString(),
                         childContent,
                        etServicePrice.getText().toString(),
                        etShopGoodness.getText().toString(),
                        etShopAddress.getText().toString(),
                        etShopPhone.getText().toString(),
                        etShopContact.getText().toString(),
                        etShopBankname.getText().toString(),
                        etShopBankcard.getText().toString(),
                        sbBanner.toString(),
                        sbDetiaoImage.toString(),
                                "",
                        BDLocationManager.getInstance().getCurLocation().getLongitude(),
                        BDLocationManager.getInstance().getCurLocation().getLatitude(),
                        String.valueOf(tagList.getLabel_id()),
                          childTags
                        );

            }
        });

    }


    @Override
    protected void bindData() {
        super.bindData();


    }


    @Override
    public void onResume() {
        super.onResume();


    }


    public void startImage()
    {
        ArrayList<String> fullimagePath = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();
        for (int x = 0; x< bannerImages.size(); x++)
        {
            fullimagePath.add(bannerImages.get(x).getFull_path());
            imagePath.add(bannerImages.get(x).getSave_path());
        }
        Intent intent= new Intent(getActivity(),ShopOpenImageActivity.class);
        intent.putExtra("images",imagePath);
        intent.putExtra("fullimages",fullimagePath);
        startActivityForResult(intent,300);
    }


    public void startexImage()
    {
        ArrayList<String> fullimagePath = new ArrayList<>();
        ArrayList<String> imagePath = new ArrayList<>();
        for (int x = 0; x< exImageList.size(); x++)
        {
            fullimagePath.add(exImageList.get(x).getFull_path());
            imagePath.add(exImageList.get(x).getSave_path());
        }
        Intent intent= new Intent(getActivity(),ShopOpenImageActivity.class);
        intent.putExtra("images",imagePath);
        intent.putExtra("fullimages",fullimagePath);
        startActivityForResult(intent,400);
    }



    @Override
    public void showSuccess(Object object) {


        dismissLoadDialog();

        if(object instanceof UpdateStoreData)
        {
             if(dataBean ==null)
             {
                 ToastUtil.show(getContext(),"数据发布成功");
             }else
             {
                 ToastUtil.show(getContext(),"数据更新成功");
             }

             setResult(Activity.RESULT_OK);
            finish();
        }

    }

    @Override
    public void showError(String msg,int code) {
        dismissLoadDialog();
        ToastUtil.show(getContext(),msg);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBusManager.getInstance().unSubscribe(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case 100://服务类型

                    tagList = (DiscoverLabelData.DataBean.LabelLv1Bean)data.getExtras().getSerializable(ConstantsKeys.KEY_DATA);

                    StringBuffer tagBuffer = new StringBuffer();
                    for(int x=0;x<tagList.getLabel_lv2().size();x++)
                    {
                        if(tagList.getLabel_lv2().get(x).isSelect())
                        {
                            tagBuffer.append(tagList.getLabel_lv2().get(x).getLable_name()).append(",");
                        }
                    }

                    String tagContent = tagBuffer.substring(0,tagBuffer.length()-1);
                    tvServiceType.setText(tagContent);



                    break;
                case 200: //服务优势
                    String content = data.getExtras().getString("content");
                    etShopGoodness.setText(content);
                    break;
                case 300: //banner

                    //bannerImages =(ArrayList<UploadImageInfoData>)getIntent().getExtras().getSerializable("images");
                    ArrayList<String>fullimages = data.getStringArrayListExtra("fullimages");
                    ArrayList<String>imagepath = data.getStringArrayListExtra("images");
                    bannerImages.clear();

                    for (int x=0;x<imagepath.size();x++)
                    bannerImages.add(new UploadImageInfoData.DataBean(imagepath.get(x),fullimages.get(x)));
                    refreshImages();
                    break;

                case 400: //图片

                    ArrayList<String>detialimages = data.getStringArrayListExtra("fullimages");
                    ArrayList<String>detialimagepath = data.getStringArrayListExtra("images");
                    exImageList.clear();

                    for (int x=0;x<detialimagepath.size();x++)
                        exImageList.add(new UploadImageInfoData.DataBean(detialimagepath.get(x),detialimages.get(x)));
                    refreshDetialImages();
                    break;

                case ACTIVITY_ALBUM_REQUESTCODE:
                    if (resultCode == Activity.RESULT_OK) {
                        cutPhoto(data.getData(), true);
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
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
                        {
                            cutPhoto(FileProvider.getUriForFile(getActivity(),  "com.insthub.cat.android.provider", new File(imagePath)), true);
                        }else
                        {
                            cutPhoto(Uri.fromFile( new File(imagePath)), true);
                        }

                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok


                    RequestOptions requestOptions = new RequestOptions()
                            .circleCrop()
                            .placeholder(R.drawable.defaulthead)
                            .error(R.drawable.defaulthead);


                    Glide.with(getContext().getApplicationContext()).asBitmap()
                            .load(imagePath)
                            .apply(requestOptions)
                            .into(ivUploadImg);

                    showLoadDialog("正在上传头像");
                    uploadUserHead(imagePath);




                    break;
            }
        }
    }




    public void refreshImages()
    {

          for(int x=0;x<imageViews.size();x++)
          {
              if(x<= bannerImages.size()-1)
              {
                  RequestOptions requestOptions2 = new RequestOptions()
                          .centerCrop()
                          .placeholder(R.drawable.ic_add_grey_600)
                          .error(R.drawable.ic_add_grey_600)
                          ;
                  Glide.with(getContext()).asBitmap()
                          .load(bannerImages.get(x).getFull_path())
                          .apply(requestOptions2)
                          .into(imageViews.get(x));

              }else
              {
                  imageViews.get(x).setImageResource(R.drawable.ic_add_grey_600);
              }
          }
    }



    public void refreshDetialImages()
    {

        for(int x=0;x<eximageViews.size();x++)
        {
            if(x<= exImageList.size()-1)
            {
                RequestOptions requestOptions2 = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_add_grey_600)
                        .error(R.drawable.ic_add_grey_600)
                        ;
                Glide.with(getContext()).asBitmap()
                        .load(exImageList.get(x).getFull_path())
                        .apply(requestOptions2)
                        .into(eximageViews.get(x));

            }else
            {
                eximageViews.get(x).setImageResource(R.drawable.ic_add_grey_600);
            }
        }
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





    /**
     * 上传图片
     */
    public void uploadUserHead(String imagePath) {

        RequestParams params = new RequestParams();
        params.addHeader("Connection", "Keep-Alive");
        // params.addHeader("Content-Type", "multipart/form-data;charset=utf-8");
        params.addBodyParameter("content_type", "head");
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
                    logo = gson.fromJson(s, UploadImageInfoData.class);
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


    /**
     * 更新用户信息
     */
    private void refreshvIEW()
    {


        if(logo!=null)
        {
            RequestOptions requestOptions2 = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.defaulthead)
                    .error(R.drawable.defaulthead)
                    ;
            Glide.with(getContext()).asBitmap()
                    .load(logo.getData().getFull_path())
                    .apply(requestOptions2)
                    .into(ivUploadImg);

        }
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
