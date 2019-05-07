package com.insthub.cat.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.insthub.cat.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Belong to the Project —— MyPayUI Created by WangJ on 2015/11/25 15:39.
 */
public class PasswordView extends RelativeLayout implements
		View.OnClickListener {
	Context context;

	private String strPassword; // 输入的密码
	private GridView gridView; // 用GrideView布局键盘，其实并不是真正的键盘，只是模拟键盘的功能
	private ArrayList<Map<String, String>> valueList; // 有人可能有疑问，为何这里不用数组了？
														// 因为要用Adapter中适配，用数组不能往adapter中填充
	private int currentIndex = -1; // 用于记录当前输入密码格位置

	private OnInputCallback mOnInputCallback;

	public PasswordView(Context context) {
		this(context, null);
	}

	public PasswordView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		View view = View.inflate(context, R.layout.layout_popup_bottom, null);

		valueList = new ArrayList<Map<String, String>>();

		gridView = (GridView) view.findViewById(R.id.gv_keybord);

		setView();

		addView(view); // 必须要，不然不显示控件
	}

	@Override
	public void onClick(View v) {
	}

	private void setView() {
		/* 初始化按钮上应该显示的数字 */
		for (int i = 1; i < 13; i++) {
			Map<String, String> map = new HashMap<String, String>();
			if (i < 10) {
				map.put("name", String.valueOf(i));
			} else if (i == 10) {
				map.put("name", "");
			} else if (i == 12) {
				map.put("name", "<<-");
			} else if (i == 11) {
				map.put("name", String.valueOf(0));
			}
			valueList.add(map);
		}

		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position < 11 && position != 9) { // 点击0~9按钮
					if (currentIndex >= -1 && currentIndex < 7) { // 判断输入位置————要小心数组越界


						if(mOnInputCallback!=null)
						{
							mOnInputCallback.input(++currentIndex,valueList.get(position)
									.get("name"));
						}
					}
				} else {
					if (position == 11) { // 点击退格键

						if (currentIndex - 1 >= -1) { // 判断是否删除完毕————要小心数组越界
							mOnInputCallback.input(currentIndex--,"");
						}
					}
				}
			}
		});
	}





	public void setOnnputCallback(final OnInputCallback pass) {

		this.mOnInputCallback = pass;
	}

	/* 获取输入的密码 */
	public String getStrPassword() {
		return strPassword;
	}


	// GrideView的适配器
	BaseAdapter adapter = new BaseAdapter() {
		@Override
		public int getCount() {
			return valueList.size();
		}

		@Override
		public Object getItem(int position) {
			return valueList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View.inflate(context, R.layout.item_gride, null);
				viewHolder = new ViewHolder();
				viewHolder.btnKey = (TextView) convertView
						.findViewById(R.id.btn_keys);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.btnKey.setText(valueList.get(position).get("name"));
			if (position == 9) {
				viewHolder.btnKey
						.setBackgroundResource(R.drawable.selector_key_del);
				viewHolder.btnKey.setEnabled(false);
			}
			if (position == 11) {
				viewHolder.btnKey
						.setBackgroundResource(R.drawable.selector_key_del);
			}

			return convertView;
		}
	};

	/**
	 * 存放控件
	 */
	public final class ViewHolder {
		public TextView btnKey;
	}
}
