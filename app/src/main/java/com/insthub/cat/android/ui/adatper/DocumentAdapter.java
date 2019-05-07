package com.insthub.cat.android.ui.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerAdapter;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module.AnnexBean;
import com.insthub.cat.android.module.BidPriceDetialData;
import com.insthub.cat.android.ui.viewholder.DocumentItemHolder;

import java.util.List;

import butterknife.Bind;

/**
 * 文档适配器
 * Created by linux on 2017/12/12.
 */

public class DocumentAdapter extends BaseRecyclerAdapter<DocumentItemHolder, AnnexBean> {




    public DocumentAdapter(Context context, List<AnnexBean> list) {
        super(context, list, R.layout.item_document);


    }


    @Override
    public void onBindViewHolder(DocumentItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.tvDocumentTitle.setText(getDataItem(position).getFile_name());

        String[] urls = getDataItem(position).getUrl().split("/");
        holder.tvDocumentFile.setText(urls[urls.length-1]);


    }

    @Override
    public DocumentItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocumentItemHolder(LayoutInflater.from(getContext()).inflate(getLayoutResource(), null));
    }


}
