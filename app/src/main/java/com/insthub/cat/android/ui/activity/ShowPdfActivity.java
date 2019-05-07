package com.insthub.cat.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.rxanroid.RxBusManager;
import com.common.android.fui.widget.CommonTitleBar;
import com.common.android.futils.ScreenInfo;
import com.common.android.futils.ToastUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.module.AnnexBean;
import com.insthub.cat.android.mvp.contract.MVPContract;
import com.insthub.cat.android.mvp.model.MVPModel;
import com.insthub.cat.android.mvp.presenter.MVPPresenter;

import butterknife.Bind;
import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;


/**
 * PDF
 * Created by linux on 2017/6/28.
 */

public class ShowPdfActivity extends BaseActivity<MVPPresenter, MVPModel> implements MVPContract.View,DownloadFile.Listener{


    @Bind(R.id.common_title_bar)
    CommonTitleBar commonTitleBar;
    @Bind(R.id.pdfViewPager)
    PDFViewPager pdfViewPager;

    @Bind(R.id.pdf_cotent)
    RelativeLayout rllPdfContent;


    private AnnexBean mAnnexBean;

    private RemotePDFViewPager remotePDFViewPager;
    private PDFPagerAdapter mPDFPagerAdapter;


    @Bind(R.id.imageView)
    ImageView image;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pdf;
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
        mAnnexBean = (AnnexBean) getIntent().getExtras().getSerializable(ConstantsKeys.KEY_DATA);
        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();
        commonTitleBar.setTitle(mAnnexBean.getFile_name());
        commonTitleBar.setLeftImageMenu(R.drawable.ic_arrow_back_white_24dp);



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


    }


    @Override
    protected void bindData() {
        super.bindData();

        if(mAnnexBean.getUrl().endsWith("pdf"))
        {

            image.setVisibility(View.GONE);
            remotePDFViewPager = new RemotePDFViewPager(this, mAnnexBean.getUrl(), this);
            remotePDFViewPager.setId(R.id.pdfViewPager);
            showLoadDialog("数据加载中");
        }else {


            pdfViewPager.setVisibility(View.GONE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .fitCenter();
            Glide.with(getContext().getApplicationContext()).asBitmap()
                    .load( mAnnexBean.getUrl())
                    .apply(requestOptions)
                    .into(image);



        }

    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void showSuccess(Object object) {

    }

    @Override
    public void showError(String msg, int code) {

    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        dismissLoadDialog();
        mPDFPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(mPDFPagerAdapter);
        rllPdfContent.removeAllViewsInLayout();
        rllPdfContent.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

    }

    @Override
    public void onFailure(Exception e) {
        dismissLoadDialog();
        ToastUtil.show(getContext(),"数据加载失败");

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}
