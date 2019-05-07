package com.insthub.cat.android.chat.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;

/**
 * 自定义聊天输入框
 * 
 * @ClassName: LoginEditext
 * @author yeliangliang
 * @date 2015-7-22 下午3:13:51
 */
public class ChatEditext extends android.support.v7.widget.AppCompatEditText implements OnTouchListener, OnFocusChangeListener {
	private boolean tag = false;// face图标打开标识

	// 图标点击监听器
	public interface FaceIconOnClickListener {
		// isFace 点击是是否为表情图标
		void clickFace(boolean isFace);
	}

	/**
	 * 设置图标点击监听器
	 * 
	 * @param listener
	 * @author yeliangliang
	 * @date 2015-7-31 下午6:09:44
	 * @version V1.0
	 * @return void
	 */
	public void setFaceIconOnClickListener(FaceIconOnClickListener listener) {
		this.listener = listener;
	}

	private Drawable xD;
	private FaceIconOnClickListener listener;

	public ChatEditext(Context context) {
		super(context);

	}

	public ChatEditext(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public ChatEditext(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	@Override
	public void setOnTouchListener(OnTouchListener l) {
		this.l = l;
	}

	@Override
	public void setOnFocusChangeListener(OnFocusChangeListener f) {
		this.f = f;
	}

	private OnTouchListener l;
	private OnFocusChangeListener f;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD
					.getIntrinsicWidth());
			if (tappedX) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					if (!tag) {// 弹出了表情选择框

					} else {// 关闭了表情选择框

					}
					if (listener != null) {
						listener.clickFace(true);
					}
				}
				return true;
			} else {
				listener.clickFace(false);

				return false;
			}
		}
		if (l != null) {
			return l.onTouch(v, event);
		}
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (!hasFocus) {
			// 失去焦点
			// 关闭键盘
			((ChatEditext) v).setInputType(InputType.TYPE_NULL); // 关闭软键盘
		}
	}



	
	

}
