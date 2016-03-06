package info.androidhive.materialtabs.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

/**
 *  ViewPagerAdapter Class help implements ListFragment
 * Created by liuqi on 3/4/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<ListFragment> mFragmentList = new ArrayList<>();
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}