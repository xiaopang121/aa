package com.insthub.cat.android.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.extend.roundedimageview.RoundedImageView;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.widget.StepsView;

import butterknife.Bind;

/**
 * Created by linux on 2017/12/12.
 */

public class DocumentItemHolder extends BaseRecyclerViewHolder {

    @Bind(R.id.iv_document)
    RoundedImageView ivDocument;
    @Bind(R.id.tv_document_title)
   public TextView tvDocumentTitle;
    @Bind(R.id.tv_document_file)
    public TextView tvDocumentFile;

    public DocumentItemHolder(View itemView) {
        super(itemView);

    }
}
