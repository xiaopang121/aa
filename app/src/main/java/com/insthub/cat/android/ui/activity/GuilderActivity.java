package com.insthub.cat.android.ui.activity;

import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.fui.widget.CircleNavigator;
import com.insthub.cat.android.R;
import com.insthub.cat.android.ui.adatper.GuidePagerAdapter;
import com.insthub.cat.android.ui.fragment.GuideFragment.FirstCustomPageFragment;
import com.insthub.cat.android.ui.fragment.GuideFragment.SecondCustomPageFragment;
import com.insthub.cat.android.ui.fragment.GuideFragment.ThirdCustomPageFragment;

import java.util.ArrayList;
import butterknife.Bind;

public class GuilderActivity extends BaseActivity {

	@Bind(R.id.rootview)
	public View rootView;

	@Bind(R.id.viewpager)
	public ViewPager viewpager;


	@Bind(R.id.indicator)
	public CircleNavigator mCircleNavigator;

	public ArrayList<Fragment> list = new ArrayList<>();

	public GuidePagerAdapter pagerAdapter;

	public int curPostion;
	private ArgbEvaluator argbEvaluator = new ArgbEvaluator();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setSwipeBackEnable(false);

	}


	@Override
	protected void bindData() {
		super.bindData();

		FirstCustomPageFragment firstCustomPageFragment = new FirstCustomPageFragment();

		SecondCustomPageFragment secondCustomPageFragment = new SecondCustomPageFragment();

		ThirdCustomPageFragment thirdCustomPageFragment = new ThirdCustomPageFragment();
//
		list.add(firstCustomPageFragment);
		list.add(secondCustomPageFragment);
		list.add(thirdCustomPageFragment);

		pagerAdapter = new GuidePagerAdapter(this.getSupportFragmentManager());
		pagerAdapter.addAllItems(list);
		viewpager.setAdapter(pagerAdapter);
		//viewpager.setPageTransformer(true, LMPageTransformer.getPageTransformer(Enums.random(TransitionEffect.class)));
		mCircleNavigator.setEnableDrawTitle(false);
		mCircleNavigator.setViewPager(viewpager);


	}


	@Override
	protected void bindEvent() {
		super.bindEvent();

		mCircleNavigator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				int nextColorPosition = position + 1;
				if (nextColorPosition >= pagerAdapter.getCount()) {
					nextColorPosition %= pagerAdapter.getCount();
				}
				if (position < (pagerAdapter.getCount() - 1)) {
					rootView.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, getPageColor(position), getPageColor(nextColorPosition)));
				} else if ( position == pagerAdapter.getCount() - 1) {
					rootView.setBackgroundColor(getPageColor(position));

				}
			}

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_guilder;
	}


	@ColorInt
	protected int getPageColor(int position) {
		if (position == 0)
			return Color.parseColor("#72d3f6");
		if (position == 1)
			return Color.parseColor("#88d2c8");
		if (position == 2)
			return Color.parseColor("#719de1");
		if (position == 3)
			return ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
		if (position == 4)
			return ContextCompat.getColor(getContext(), android.R.color.holo_purple);
		if (position == 5)
			return ContextCompat.getColor(getContext(), android.R.color.darker_gray);
		return Color.TRANSPARENT;
	}


	public ColorDrawable   getColorDrawable(int position)
	{

		return  new ColorDrawable(getPageColor(position));
	}

}
