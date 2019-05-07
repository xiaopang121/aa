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
import android.support.design.widget.TabLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.common.android.flog.KLog;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.fui.widget.CustomViewPager;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.common.android.futils.UIUtil;
import com.common.android.futils.UriUtil;
import com.google.gson.Gson;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.Constants;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.CacheManager;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.module2.StoreData;
import com.insthub.cat.android.module2.UploadImageInfoData;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;
import com.insthub.cat.android.ui.adatper.CommonFragmentAdatper;
import com.insthub.cat.android.ui.adatper.UploadImageAdapter;
import com.insthub.cat.android.ui.dialog.ActionSheetDialog;
import com.insthub.cat.android.ui.fragment.EmptyFragment.EmpyFragment;
import com.insthub.cat.android.ui.widget.SpacesItemDecoration;
import com.insthub.cat.android.utils.FileUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import cn.lankton.flowlayout.FlowLayout;


/**
 * 小鲸捕手
 * Created by linux on 2017/6/28.
 */

public class XiaojingpushouActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,UploadImageAdapter.Callback {

    public static final int ACTIVITY_ALBUM_REQUESTCODE = 2000;

    public static final int ACTIVITY_CAMERA_REQUESTCODE = 2001;

    public static final int ACTIVITY_MODIFY_PHOTO_REQUESTCODE = 2002;

    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;

    private List<String> tabs = new ArrayList<>();
    public static final int MOVABLE_COUNT = 5;

    private int tabCount;

    @Bind(R.id.tl)
    TabLayout tl;

    @Bind(R.id.customviewpage)
    CustomViewPager vitePager;

    private CommonFragmentAdatper mGuidePagerAdapter;

    private DiscoverLabelData mDiscoverLabelData;


    @Bind(R.id.flowView)
    FlowLayout mFlowLayout;

    @Bind(R.id.bt_sumbit)
    Button btSumbit;


    @Bind(R.id.tv_content)
    EditText etContent;

    @Bind(R.id.tv_detial)
    EditText etDetial;


    @Bind(R.id.rb_select)
    RadioGroup rbSelectType;


    @Bind(R.id.lly_distance)
    LinearLayout llyDistance;

    @Bind(R.id.et_distance)
    EditText etDistance;


    @Bind(R.id.rb_distance_group)
    RadioGroup rgDistance_Group;

    @Bind(R.id.rb_distance_3)
    RadioButton rbDistance3;
    @Bind(R.id.rb_distance_5)
    RadioButton rbDistance5;
    @Bind(R.id.rb_distance_10)
    RadioButton rbDistance10;
    @Bind(R.id.rb_distance_50)
    RadioButton rbDistance50;
    @Bind(R.id.rb_distance_100)
    RadioButton rbDistance100;
    @Bind(R.id.rb_distance_200)
    RadioButton rbDistance200;
    @Bind(R.id.rb_distance_500)
    RadioButton rbDistance500;
    @Bind(R.id.rb_distance_all)
    RadioButton rbDistanceAll;


    RadioButton rbDistanceCurrent;


    @Bind(R.id.rv_image_list)
    public RecyclerView recyclerview;
    private LinkedList<UploadImageInfoData.DataBean> bannerImages = new LinkedList<>();


    private int select = 0;


    private int tagPostion = 0;

    private int type = 0;
    private UploadImageAdapter mUploadImageAdapter;
    private String imagePath = "";
    private Handler mHandler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mUploadImageAdapter.notifyDataSetChanged();
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_xiaojingpushou;
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
        commonTitleBar.setTitle("星客捕手");
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);
        bannerImages.add(new UploadImageInfoData.DataBean(-1));
        GridLayoutManager jxlayoutManager = new GridLayoutManager(getActivity(), 3);
        SpacesItemDecoration jx2layoutManager = new SpacesItemDecoration(UIUtil.dpToPx(getResources(), 5));
        recyclerview.setLayoutManager(jxlayoutManager);
        recyclerview.setHasFixedSize(true);
        mUploadImageAdapter = new UploadImageAdapter(this, bannerImages);
        recyclerview.setAdapter(mUploadImageAdapter);
        recyclerview.addItemDecoration(jx2layoutManager);
        recyclerview.scrollToPosition(0);
        mPresenter.getDiscoverLabel();


    }


    private void initTabLayout() {
        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tl.setTabMode(tabCount <= MOVABLE_COUNT ? TabLayout.MODE_FIXED : TabLayout.MODE_SCROLLABLE);
        //指示条的颜色
        tl.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow_color_pressed));
        tl.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.view_margin_2));
        tl.setupWithViewPager(vitePager);

        //自定义标签布局
        for (int i = 0; i < tabs.size(); i++) {
            TabLayout.Tab tab = tl.newTab();
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tabview_main, tl, false);
            tv.setText(tabs.get(i));
            tab.setCustomView(tv);
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


        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {


                select = tab.getPosition();
                tagPostion = 0;
                buildTagList();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rbDistance3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("3");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance3)
                    {
                        rbDistanceCurrent.setChecked(false);

                        rbDistanceCurrent = rbDistance3;
                    }else
                    {
                        rbDistanceCurrent = rbDistance3;
                    }

                }
            }
        });


        rbDistance5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("5");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance5)
                    {
                        rbDistanceCurrent.setChecked(false);

                        rbDistanceCurrent = rbDistance5;
                    }else
                    {
                        rbDistanceCurrent = rbDistance5;
                    }
                }
            }
        });


        rbDistance10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("10");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance10)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistance10;
                    }else
                    {
                        rbDistanceCurrent = rbDistance10;
                    }
                }
            }
        });




        rbDistance50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("50");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance50)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistance50;
                    }else
                    {
                        rbDistanceCurrent = rbDistance50;
                    }
                }
            }
        });



        rbDistance100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("100");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance100)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistance100;
                    }else
                    {
                        rbDistanceCurrent = rbDistance100;
                    }
                }
            }
        });


        rbDistance200.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("200");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance200)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistance200;
                    }else
                    {
                        rbDistanceCurrent = rbDistance200;
                    }
                }
            }
        });


        rbDistance500.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("500");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistance500)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistance500;
                    }else
                    {
                        rbDistanceCurrent = rbDistance500;
                    }
                }
            }
        });

        rbDistanceAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    etDistance.setText("全国");

                    if(rbDistanceCurrent!=null && rbDistanceCurrent!=rbDistanceAll)
                    {
                        rbDistanceCurrent.setChecked(false);
                        rbDistanceCurrent = rbDistanceAll;
                    }else
                    {
                        rbDistanceCurrent = rbDistanceAll;
                    }
                }
            }
        });

        mUploadImageAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                if (bannerImages.get(position).getType() == -1) {

                    showChangeIcon();
                }

            }
        });
        etDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                switch (etDistance.getText().toString().trim())
                {
                    case "3":
                        rbDistance3.setChecked(true);
                        break;
                    case "5":
                        rbDistance5.setChecked(true);
                        break;
                    case "10":
                        rbDistance10.setChecked(true);
                        break;
                    case "50":
                        rbDistance50.setChecked(true);
                        break;
                    case "100":
                        rbDistance100.setChecked(true);
                        break;
                    case "200":
                        rbDistance200.setChecked(true);
                        break;
                    case "500":
                        rbDistance500.setChecked(true);
                        break;

                    case "全国":
                        rbDistanceAll.setChecked(true);
                        break;
                    default:
                            if(rbDistanceCurrent!=null)
                            {
                                rbDistanceCurrent.setChecked(false);
                                rbDistanceCurrent =null;
                            }
                          break;

                }

            }
        });

//
//        rgDistance_Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i) {
//                    case R.id.rb_distance_3:
//                        etDistance.setText("3");
//                        break;
//                    case R.id.rb_distance_5:
//                        etDistance.setText("5");
//                        break;
//                    case R.id.rb_distance_10:
//                        etDistance.setText("10");
//                        break;
//                    case R.id.rb_distance_50:
//                        etDistance.setText("50");
//                        break;
//                    case R.id.rb_distance_100:
//                        etDistance.setText("100");
//                        break;
//                    case R.id.rb_distance_200:
//                        etDistance.setText("200");
//                        break;
//                    case R.id.rb_distance_500:
//                        etDistance.setText("500");
//                        break;
//                    case R.id.rb_distance_all:
//                        etDistance.setText("0");
//                        break;
//                    default:
//                }
//            }
//        });


        btSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(etContent.getText().toString())) {
                    ToastUtil.show(getActivity(), "请输入关键字查询");
                    return;
                }
                StringBuffer imageBuffer = new StringBuffer();

                for (int x = 0; x < bannerImages.size(); x++) {

                    if (bannerImages.get(x).getType() == -1) {
                        continue;
                    }
                    imageBuffer.append(bannerImages.get(x).getSave_path());
                    if (x != bannerImages.size() - 1) {
                        imageBuffer.append(",");
                    }
                }



                if (type == 0) {
                    if (TextUtils.isEmpty(etDistance.getText().toString()) ) {
                        ToastUtil.show(getActivity(), "请输入查询服务商范围");
                        return;
                    }

                    String distance="0";
                    if(etDistance.getText().toString().trim().contains("全国"))
                    {
                        distance="-1";
                    }else{
                        distance=etDistance.getText().toString().trim();
                    }
                    showLoadDialog("正在提交数据");

                    mPresenter.getStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                            CacheManager.getInstance().getToken().getData().getToken(),
                            "",  "", type,
                            etContent.getText().toString(),
                            etDetial.getText().toString(),
                            imageBuffer.toString(),
                            BDLocationManager.getInstance().getCurLocation().getLongitude(),
                            BDLocationManager.getInstance().getCurLocation().getLatitude()
                            , Double.valueOf(distance.trim()));

                } else {
                    showLoadDialog("正在提交数据");

                    mPresenter.getStore(CacheManager.getInstance().getToken().getData().getUser_id(),
                            CacheManager.getInstance().getToken().getData().getToken(),
                             "",
                            "",
                            type, etContent.getText().toString(), etDetial.getText().toString(),
                            imageBuffer.toString(),
                            BDLocationManager.getInstance().getCurLocation().getLongitude(),
                            BDLocationManager.getInstance().getCurLocation().getLatitude()
                            , 0);
                }


            }
        });


        rbSelectType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_distance:
                        type = 0;
                        llyDistance.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_comment:
                        type = 1;
                        llyDistance.setVisibility(View.GONE);
                        break;
                }
            }
        });


        etDistance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        etDistance.setText(s);
                        etDistance.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    etDistance.setText(s);
                    etDistance.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        etDistance.setText(s.subSequence(0, 1));
                        etDistance.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    protected void bindData() {
        super.bindData();


    }


    private void updateFragment() {


        if (mDiscoverLabelData != null) {
            mGuidePagerAdapter = new CommonFragmentAdatper(getSupportFragmentManager());

            for (int x = 0; x < mDiscoverLabelData.getData().getLabel_lv1().size(); x++) {

                tabs.add(mDiscoverLabelData.getData().getLabel_lv1().get(x).getLable_name());
                mGuidePagerAdapter.addFragment(EmpyFragment.newInstance(mDiscoverLabelData.getData().getLabel_lv1().get(x)), mDiscoverLabelData.getData().getLabel_lv1().get(x).getLable_name());
            }
            tabCount = tabs.size();
            vitePager.setOffscreenPageLimit(3);
            vitePager.setAdapter(mGuidePagerAdapter);
            initTabLayout();
        }


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {
        if (object instanceof DiscoverLabelData) {
            mDiscoverLabelData = (DiscoverLabelData) object;
            updateFragment();
        }


        if (object instanceof StoreData) {
            StoreData mStoreData = (StoreData) object;
            dismissLoadDialog();
            ToastUtil.show(getContext(), mStoreData.getMessage());
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

    private void buildTagList() {
        mFlowLayout.setVisibility(View.GONE);
        mFlowLayout.removeAllViews();
        if (mDiscoverLabelData.getData().getLabel_lv1().get(select).getLabel_lv2().size() == 0) {

        } else {

            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(UIUtil.dpToPx(getResources(), 10), 0, UIUtil.dpToPx(getResources(), 10), 0);
            for (int x = 0; x < mDiscoverLabelData.getData().getLabel_lv1().get(select).getLabel_lv2().size(); x++) {
                TextView textView = buildLabel(mDiscoverLabelData.getData().getLabel_lv1().get(select).getLabel_lv2().get(x).getLable_name());
                textView.setTag(x);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        tagPostion = (int) arg0.getTag();
                        buildTagList();
                    }
                });

                if (x == tagPostion) {
                    textView.setBackgroundResource(R.drawable.label_bg);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.drawable.label_bg_normal);
                    textView.setTextColor(getResources().getColor(R.color.B_black_70));
                }
                mFlowLayout.addView(textView, lp);
            }
        }

    }


    private TextView buildLabel(String text) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setPadding(UIUtil.dpToPx(getResources(), 5),
                (int) UIUtil.dpToPx(getResources(), 5),
                (int) UIUtil.dpToPx(getResources(), 10),
                (int) UIUtil.dpToPx(getResources(), 5));

        return textView;
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
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                                    imageUri = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));
                                } else {
                                    imageUri = Uri.fromFile(new File(imagePath));
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
    public void getPhotoPath() {
        if (!TextUtils.isEmpty(imagePath)) {

            try {
                File file = new File(imagePath);
                file.delete();

            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        }

        imagePath = FileUtil.getHeadPhotoDir() + System.currentTimeMillis() + ".png";
    }



    /**
     * 裁剪图片
     */
    public void cutPhoto(Uri uri, boolean isHeadPic) {




        uploadUserHead(UriUtil.getRealFilePath(getContext(),uri));


//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
//        intent.putExtra("crop", "true");
//        if (isHeadPic) {
//            // aspectX aspectY 是宽高的比例
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            // outputX outputY 是裁剪图片宽高
//            intent.putExtra("outputX", 1000);
//            intent.putExtra("outputY", 1000);
//
//            intent.putExtra("scale", true);
//            //只能设置成false，k920无法返回
//            intent.putExtra("return-data", false);
//
//            Uri tempUrl = null;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                tempUrl = FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath));
//
//            } else {
//                tempUrl = Uri.fromFile(new File(imagePath));
//            }
//
//            List resInfoList = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//            if (resInfoList.size() == 0) {
//                ToastUtil.show(getActivity(), "没有合适的相机应用程序");
//                return;
//            }
//            Iterator resInfoIterator = resInfoList.iterator();
//            while (resInfoIterator.hasNext()) {
//                ResolveInfo resolveInfo = (ResolveInfo) resInfoIterator.next();
//                String packageName = resolveInfo.activityInfo.packageName;
//                grantUriPermission(packageName, tempUrl, Intent.FLAG_GRANT_READ_URI_PERMISSION
//                        | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            }
//
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUrl);
//            intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
//            intent.putExtra("noFaceDetection", true);
//        } else {
//            // 是否保留比例
//            intent.putExtra("scale", "true");
//            intent.putExtra("output", Uri.fromFile(FileUtil.getWallPaperFile()));
//        }
//
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        startActivityForResult(intent, ACTIVITY_MODIFY_PHOTO_REQUESTCODE);
    }


    /**
     * 上传图片
     */
    public void uploadUserHead(String imagePath) {

        showLoadDialog("正在上传数据");
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
                    UploadImageInfoData logo = gson.fromJson(s, UploadImageInfoData.class);
                    bannerImages.removeLast();
                    bannerImages.add(logo.getData());
                    if (bannerImages.size() < 3) {
                        bannerImages.add(new UploadImageInfoData.DataBean(-1));
                    }

                    mHandler.sendEmptyMessage(0);
                    dismissLoadDialog();
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
                        int degree = FileUtil.readPictureDegree(imagePath);
                        Bitmap cameraBitmap = BitmapFactory.decodeFile(imagePath, bitmapOptions);
                        cameraBitmap = FileUtil.rotaingImageView(degree, cameraBitmap);
                        FileUtil.saveCutBitmapForCache(this, cameraBitmap);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            cutPhoto(FileProvider.getUriForFile(getActivity(), "com.insthub.cat.android.provider", new File(imagePath)), true);
                        } else {
                            cutPhoto(Uri.fromFile(new File(imagePath)), true);
                        }
                    }
                    break;
                case ACTIVITY_MODIFY_PHOTO_REQUESTCODE:
                    // NOTICE: 上传头像接口ok
                    uploadUserHead(imagePath);


                    break;
            }

        }

    }


    @Override
    public void delete(int position) {

        bannerImages.remove(position);
        mUploadImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
