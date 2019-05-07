package com.common.android.fui.adapter.test;

import android.content.Context;
import android.view.View;


import com.common.android.fui.adapter.BaseViewHolder;
import com.common.android.fui.adapter.BasicListAdapter;

import java.util.List;

/**
 * Created by macbook on 16/6/2.
 */
public class TestAdapter extends BasicListAdapter<TestAdapter.TestViewHolder,TestAdapter.TestBean> {


    public TestAdapter(Context context, List<TestBean> list, int layoutId) {
        super(context, list, layoutId);
    }

    public class  TestViewHolder extends BaseViewHolder
    {
        public TestViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class  TestBean{};

    @Override
    protected void setViewHolder(TestViewHolder holder, int position, TestBean bean) {

    }

    @Override
    protected TestViewHolder createNewHolder(View container) {
        return new TestViewHolder(container);
    }
}
