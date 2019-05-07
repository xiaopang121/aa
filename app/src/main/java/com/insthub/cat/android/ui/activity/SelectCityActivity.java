package com.insthub.cat.android.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.common.android.fui.activity.BaseActivity;
import com.common.android.futils.ScreenInfo;
import com.insthub.cat.android.R;
import com.insthub.cat.android.constant.ConstantsKeys;
import com.insthub.cat.android.manager.BDLocationManager;
import com.insthub.cat.android.manager.DBManager;
import com.insthub.cat.android.module2.City;
import com.insthub.cat.android.module2.LocateState;
import com.insthub.cat.android.ui.adatper.CityListAdapter;
import com.insthub.cat.android.ui.adatper.ResultListAdapter;
import com.insthub.cat.android.ui.widget.SideLetterBar;
import com.insthub.cat.android.utils.LocationUtils;

import java.util.List;

import butterknife.Bind;

/**
 * User:macbook
 * DATE:2017/10/23 19:47
 * Desc:${DESC}
 */

public class SelectCityActivity extends BaseActivity implements  LocationUtils.CityNameStatus{
    public static final String KEY_PICKED_CITY = "picked_city";

    @Bind(R.id.common_title_bar)
    RelativeLayout commonTitleBar;
    //城市选择
    public static final int REQUEST_CODE_PICK_CITY = 2;

    @Bind(R.id.listview_all_city)
    ListView mListView;


    @Bind(R.id.listview_search_result)
    ListView mResultListView;


    @Bind(R.id.side_letter_bar)
    SideLetterBar mLetterBar;

    @Bind(R.id.et_search)
    EditText searchBox;

    @Bind(R.id.iv_search_clear)
    ImageView clearBtn;
    //    private ImageView backBtn;

    @Bind(R.id.empty_view)
    ViewGroup emptyView;


    @Bind(R.id.tv_letter_overlay)
    TextView overlay;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.bt_right)
    Button btRight;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    private ResultListAdapter mResultAdapter;

    private List<City> mAllCities;

    private CityListAdapter mCityAdapter;

    private DBManager dbManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_select_citys;
    }


    @Override
    protected void bindViewById() {
        super.bindViewById();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void bindData() {
        super.bindData();


        int statubar = ScreenInfo.getStatusBarHeight(getActivity());
        ViewGroup.LayoutParams lp = commonTitleBar.getLayoutParams();
        lp.height = lp.height + statubar;
        commonTitleBar.setPadding(0, statubar, 0, 0);
        commonTitleBar.setLayoutParams(lp);
        commonTitleBar.invalidate();

        ivLeft.setImageResource(R.drawable.ic_arrow_back_white_24dp);

        tvTitle.setText("选择城市");


        dbManager = new DBManager(getActivity());
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();
        mCityAdapter = new CityListAdapter(getContext(), mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                back(name);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
//                mLocationClient.startLocation();
            }
        });
        mResultAdapter = new ResultListAdapter(getActivity(), null);
        mListView.setAdapter(mCityAdapter);
        mLetterBar.setOverlay(overlay);
        mResultListView.setAdapter(mResultAdapter);
        BDLocationManager.getInstance().addCallback(this);

    }


    @Override
    protected void bindEvent() {
        super.bindEvent();
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    finish();
                } else {
                    finishAfterTransition();
                }
            }
        });



        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
            }
        });


        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });


        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });


        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                back(mResultAdapter.getItem(position).getName());
            }
        });

    }

    private void back(String city) {



        Bundle  bundle = new Bundle();
        bundle.putString(ConstantsKeys.KEY_DATA,city);

        Intent intent = new Intent(getContext(),SelectStreatListActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent,200);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BDLocationManager.getInstance().removeCallback(this);
    }

    @Override
    public void detecting() {

    }

    @Override
    public void update(String city, BDLocation location,BDLocation locatio2) {

        mCityAdapter.updateLocateState(LocateState.SUCCESS,locatio2.getCity());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            finish();
        }
    }
}
