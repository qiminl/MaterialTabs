package info.androidhive.materialtabs.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.util.Log;

import java.util.ArrayList;

/**
 *  ViewPagerAdapter Class help implements ListFragment
 * Created by liuqi on 3/4/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<ListFragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
    private final FragmentManager mFragmentManager;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        mFragmentManager = manager;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public Fragment getItem(String title) {
        int position = mFragmentTitleList.indexOf(title);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(ListFragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        //Log.d("debug", "fragment " + title + " added in ViewPagerAdapter" );
    }

    public int getListPosition(String object){
        int position = 0;
        for (ListFragment i: mFragmentList){
            if (i.getTag() == object){
                position = mFragmentList.indexOf(i);
            }
        }
        return position;
    }

    public String getTag(String title){
        int position = mFragmentTitleList.indexOf(title);
        return mFragmentList.get(position).getTag();
    }



    /**
     * In order for proper adjustment b/t fragments.
     * Override this will be used to destroyed pages.
     * @param object
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        super.getItemPosition(object);
        Log.d("debug", " getItemPosition = " + Integer.toString(super.getItemPosition(object)));
        Log.d("debug", " POSITION_UNCHANGED = "+Integer.toString(POSITION_UNCHANGED));
        Log.d("debug", " POSITION_NONE = "+Integer.toString(POSITION_NONE));
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}