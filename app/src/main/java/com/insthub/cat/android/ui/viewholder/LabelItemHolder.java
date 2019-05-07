package com.insthub.cat.android.ui.viewholder;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.android.fui.adapter.BaseRecyclerViewHolder;
import com.common.android.futils.UIUtil;
import com.insthub.cat.android.R;
import com.insthub.cat.android.module2.DiscoverLabelData;
import com.insthub.cat.android.ui.adatper.LabelAdapter;

import butterknife.Bind;
import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by linux on 2017/12/12.
 */

public class LabelItemHolder extends BaseRecyclerViewHolder {


    @Bind(R.id.item_label1)
    public TextView itemLabel1;
    @Bind(R.id.flow)
    public FlowLayout mFlowLayout;


    private DiscoverLabelData.DataBean.LabelLv1Bean data ;
    private Context ctx ;
    private int position ;
    private LabelAdapter.LabelCallback call;

    public LabelItemHolder(View itemView) {
        super(itemView);
        ctx = itemView.getContext();

    }



    public void setItemData(DiscoverLabelData.DataBean.LabelLv1Bean param, int pos, LabelAdapter.LabelCallback callback)
    {
        data =param;
        position =pos;
        call = callback;
        buildTagList();
    }




    private void buildTagList()
    {

        mFlowLayout.removeAllViews();

        if(data.getLabel_lv2().size()==0)
        {
            mFlowLayout.setVisibility(View.GONE);
        }else
        {
            mFlowLayout.setVisibility(View.VISIBLE);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(UIUtil.dpToPx(ctx.getResources(), 10), 0, UIUtil.dpToPx(ctx.getResources(), 10), 0);
            for (int x=0;x<data.getLabel_lv2().size();x++) {
                TextView textView = buildLabel(ctx,data.getLabel_lv2().get(x).getLable_name());
                textView.setTag(x);
                textView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        int tagItem = (int)arg0.getTag();
                       if(call!=null)
                       {
                           call.onClickEdit(position,tagItem);
                       }
                    }
                });
                if(data.getLabel_lv2().get(x).isSelect())
                {
                    textView.setBackgroundResource(R.drawable.label_bg);
                    textView.setTextColor(ctx.getResources().getColor(R.color.white));
                }else {
                    textView.setBackgroundResource(R.drawable.label_bg_normal);
                    textView.setTextColor(ctx.getResources().getColor(R.color.B_black_70));
                }
                mFlowLayout.addView(textView,lp);
            }
        }
        mFlowLayout.relayoutToCompressAndAlign();
    }


    private TextView buildLabel(Context ctx,String text) {
        TextView textView = new TextView(ctx);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        textView.setPadding(UIUtil.dpToPx(ctx.getResources(), 5),
                (int) UIUtil.dpToPx(ctx.getResources(), 5),
                (int) UIUtil.dpToPx(ctx.getResources(), 10),
                (int) UIUtil.dpToPx(ctx.getResources(), 5));

        return textView;
    }
}
