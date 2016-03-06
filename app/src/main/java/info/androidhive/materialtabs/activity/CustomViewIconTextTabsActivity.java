package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.fragments.CollaboratorFragment;
import info.androidhive.materialtabs.fragments.EventFragment;
import info.androidhive.materialtabs.fragments.OpportunityFragment;
import info.androidhive.materialtabs.fragments.VolunteerFragment;

public class CustomViewIconTextTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_icon_text_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("debug", "toolbar set");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        Log.d("debug", "viewPager set");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        Log.d("debug", "tabLayout set");
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {
        try{
            TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabOne.setText("Opportunity");
            tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
            tabLayout.getTabAt(0).setCustomView(tabOne);

            TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabTwo.setText("Event");
            tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_call, 0, 0);
            tabLayout.getTabAt(1).setCustomView(tabTwo);

            TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabThree.setText("Collaborator");
            tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(2).setCustomView(tabThree);

            TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabFour.setText("Volunteer");
            tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_contacts, 0, 0);
            tabLayout.getTabAt(3).setCustomView(tabFour);
        }catch (NullPointerException e){
            //todo handle null pointer exc
            Log.d("debug","null pointer @ setupTabIcons");
        }
    }

    /**
     * Adding fragments to ViewPager
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFrag(new OneFragment(), "Opportunity");
        //Log.d("debug", "setting view pager adapter");
        adapter.addFrag(new OpportunityFragment(), "Opportunity");
        //Log.d("debug", "OpportunityFragment set");
        adapter.addFrag(new EventFragment(), "Event");
        //Log.d("debug", "EventFragment() set");
        //change to listfragment
        adapter.addFrag(new CollaboratorFragment(), "Collaborator");
        //Log.d("debug", " CollaboratorFragment() set");
        adapter.addFrag(new VolunteerFragment(), "Volunteer");
        //Log.d("debug", "VolunteerFragment() set");
        viewPager.setAdapter(adapter);
        Log.d("debug", "adapter set");
    }


    /**
     * todo how about
     */

}
