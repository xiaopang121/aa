package com.common.android.fui.adapter;

import android.content.Context;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发者 nriet
 * 2016-10-9 9:53
 * com.common.android.fui.adapter
 */
public class BaseRecyclerAdapter<T extends BaseRecyclerViewHolder,V > extends RecyclerView.Adapter<T> {

    protected static final int BASE_ITEM_TYPE_HEADER = 100000;
    protected static final int BASE_ITEM_TYPE_FOOTER = 200000;
    protected static final int TYPE_NORMAL =30000;
    protected Context context;
    protected  int layoutId;
    public List<V> data;
    protected SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    protected SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();
    public OnItemClickListener onItemClickListener;

    public OnItemLongClickListener onItemLongClickListener;


    public BaseRecyclerAdapter(Context context, List<V> list, int layoutId) {
        this.layoutId = layoutId;
        this.context = context;

        if(list==null)
        {
            data = new ArrayList<>();
        }else
        {
            data = list;
        }

    }

    public void addHeaderView(View view)
    {
        mHeaderViews.put(mHeaderViews.size()+BASE_ITEM_TYPE_HEADER,view);
        notifyDataSetChanged();
    }


    public void addFootView(View view)
    {
        mFootViews.put(mFootViews.size()+BASE_ITEM_TYPE_FOOTER,view);
        notifyDataSetChanged();
    }


    public boolean isHeaderViewPos(int position)
    {
        return position < getHeadersCount();
    }

    public boolean isFooterViewPos(int position)
    {
        return position >= getHeadersCount() + getItemCount();
    }

    public int getHeadersCount()
    {
        return mHeaderViews.size();
    }

    public int getFootersCount()
    {
        return mFootViews.size();
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getDataList().size();
    }


    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.itemView.setTag(position);
        if(onItemClickListener!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,(int) view.getTag());
                }
            });
        }

        if(null != onItemLongClickListener)
        {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(view,(int) view.getTag());
                    return false;
                }
            });
        }

    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }




    public void setOnItemLongClickListener(OnItemLongClickListener listener)
    {
        this.onItemLongClickListener = listener;
    }



    public void setOnItemClickListener(OnItemClickListener listener)
    {
        onItemClickListener=listener;
    }


    /**
     * 添加点击事件
     */
    public interface  OnItemClickListener
    {

        public void onItemClick(View view, int position);
    }


    /**
     * 添加长按事件
     */
    public interface  OnItemLongClickListener

    {
        public void onItemLongClick(View view, int positon);
    }


    public Context getContext()
    {
        return context;
    }


    /**
     * 获取布局
     * @return
     */
    protected int  getLayoutResource()
    {
        return layoutId;
    }


    /**
     * 获取数据对象
     * @param position
     * @return
     */
    public  V getDataItem(int position)
    {
        return data.get(position);
    }


    /**
     * 重置数据
     * @param param
     */
    protected void resetData(List<V> param)
    {
        data.clear();
        data.addAll(param);
    }


    /**
     * 获取数据列表
     * @return
     */
    public List<V> getDataList()
    {
        return data;
    }





}
