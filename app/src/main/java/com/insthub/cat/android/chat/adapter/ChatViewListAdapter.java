package com.insthub.cat.android.chat.adapter;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.insthub.cat.android.chat.bean.ChatRecord;

/**
 * 聊天内容界面的ListView适配器
 * 
 * @ClassName: ChatViewListAdapter
 * @author yeliangliang
 * @date 2015-7-31 上午10:44:02
 */
public class ChatViewListAdapter extends BaseAdapter {
	private ArrayList<ChatRecord> mList;
	private Context mContext;
	private LayoutInflater inflater;
	private HolderView holderView;
//	private FaceUtil faceUtil;

	public ChatViewListAdapter(Context context, ArrayList<ChatRecord> list) {
		this.mContext = context;
		this.mList = list;
		inflater = LayoutInflater.from(mContext);
//		faceUtil = FaceUtil.getInstance(mContext);
	}

	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holderView = new HolderView();

			//这一段需要打开判断
//			convertView = inflater.inflate(R.layout.item_chat_list, null);
//			holderView.rl_chat_my = (RelativeLayout) convertView.findViewById(R.id.rl_chat_my);
//			holderView.rl_chat_you = (RelativeLayout) convertView.findViewById(R.id.rl_chat_you);
//			holderView.tv_content_my = (TextView) convertView.findViewById(R.id.tv_chat_my);
//			holderView.tv_content_you = (TextView) convertView.findViewById(R.id.tv_chat_you);
			convertView.setTag(holderView);
		} else {
			holderView = (HolderView) convertView.getTag();
		}
		ChatRecord chatMessage = mList.get(position);
		// 判断是接收还是发送
		if (chatMessage.getSource().equals("you")) {
			// 接收信息
			holderView.rl_chat_my.setVisibility(View.GONE);
			holderView.rl_chat_you.setVisibility(View.VISIBLE);
			//holderView.tv_content_you.setText(faceUtil.matchingString(chatMessage.getContent(),true));
			holderView.tv_content_you.setText(chatMessage.getContent());
		} else if (chatMessage.getSource().equals("my")) {
			// 发送信息
			holderView.rl_chat_my.setVisibility(View.VISIBLE);
			holderView.rl_chat_you.setVisibility(View.GONE);
			//holderView.tv_content_my.setText(faceUtil.matchingString(chatMessage.getContent(),true));
			holderView.tv_content_my.setText(chatMessage.getContent());
		}

		return convertView;
	}

	private class HolderView {
		private RelativeLayout rl_chat_my;// 聊天信息布局发送
		private RelativeLayout rl_chat_you;// 聊天信息布局接收
		private TextView tv_content_my;// 发送的文字消息展示
		private TextView tv_content_you;// 接收的文字消息展示
	}

}
