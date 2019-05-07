package com.insthub.cat.android.ui.adatper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * User:macbook
 * DATE:16/10/19 09:48
 * Desc:${DESC}
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {

    public ArrayList<Fragment>  mItemInfos = new ArrayList<>();
    public FragmentManager fm;
    private String[] titles ;
    public GuidePagerAdapter(FragmentManager fm)
    {
        super(fm);
        this.fm = fm;
    }
    public GuidePagerAdapter(FragmentManager fm,String[] titles)
    {
        super(fm);
        this.fm = fm;
        this.titles = titles;
    }

    /**
     *
     * @param citys
     */
    public void addAllItems(List<Fragment> citys)
    {
        mItemInfos.clear();
        mItemInfos.addAll(citys);
        notifyDataSetChanged();

    }

    @Override
    public CharSequence getPageTitle(int position) {


        if(titles==null)
        {
            return "";
        }


        return titles[position];
    }

    @Override
    public int getCount() {
        return mItemInfos.size();
    }

    @Override
    public Fragment getItem(int position) {


        return mItemInfos.get(position);
    }


    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
        Fragment fragment = (Fragment) object;
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }


    @Override
    public Object instantiateItem(View container, int position) {
        if(mItemInfos.size()>position)
        {
            return getItem(position);
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
