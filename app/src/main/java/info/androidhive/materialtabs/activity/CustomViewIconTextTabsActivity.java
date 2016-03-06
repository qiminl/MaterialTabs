package info.androidhive.materialtabs.activity;

import android.app.FragmentTransaction;
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
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.Comment;
import info.androidhive.materialtabs.DBHandler;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.fragments.CollaboratorFragment;
import info.androidhive.materialtabs.fragments.EventFragment;
import info.androidhive.materialtabs.fragments.OneFragment;
import info.androidhive.materialtabs.fragments.OpportunityFragment;
import info.androidhive.materialtabs.fragments.VolunteerFragment;

public class CustomViewIconTextTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_icon_text_tabs);


        //temp DB test
        //todo should be moving to fragment
        DBHandler myDiaryDBHandler = new DBHandler(this);
        //Log.d(DEBUG, "try add Diary");
        myDiaryDBHandler.addComment(new Comment("1","1","1", "none", "none", "wow wow wow wow"));
        myDiaryDBHandler.addComment(new Comment("2","1","1","none","none","la la la la"));
        myDiaryDBHandler.addComment(new Comment("3", "1", "1", "none", "none", "wow wow wow wow"));
        myDiaryDBHandler.addComment(new Comment("4", "1", "1", "none", "none", "la la la la"));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("debug", "toolbar set");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        Log.d("debug", "viewPager set");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                         /*List<Fragment> fragments = fragmentManager.getFragments();
                        if(fragments != null){
                            for (Fragment f : fragments) {
                                fragmentManager.beginTransaction().remove(f).commitAllowingStateLoss();
                            }
                        }*/
                        super.onTabSelected(tab);

                        if(tab.getText() == "Opportunity"){
                            Log.d("debug","Tab Opportunity clicked");
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container, new OpportunityFragment()).commit();
                        }else if(tab.getText() == "Event"){
                            Log.d("debug","Tab Event clicked");
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container,new EventFragment() ).commit();
                        }else if(tab.getText() == "Collaborator"){
                            Log.d("debug","Tab Collaborator clicked");
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container,new CollaboratorFragment()).commit();
                        }else if(tab.getText() == "Volunteer"){
                            Log.d("debug","Tab Volunteer clicked");
                            fragmentManager.beginTransaction()
                                    .replace(R.id.container,new VolunteerFragment() ).commit();
                        }
                        String title = (String) tab.getText();
                        setTitle(title);
                    }
                });
        setupTabIcons();
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
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //adapter.addFrag(new OneFragment(), "Opportunity");
        //Log.d("debug", "setting view pager adapter");
        //OneFragment nextFrag= new OneFragment().newInstance("object 1");
        adapter.addFrag(new OpportunityFragment(), "Opportunity");
        //Log.d("debug", "OpportunityFragment set");
        adapter.addFrag(new EventFragment(), "Event");
        //Log.d("debug", "EventFragment() set");
        //change to listfragment
        adapter.addFrag(new CollaboratorFragment(), "Collaborator");
        //Log.d("debug", " CollaboratorFragment() set");
        adapter.addFrag(new VolunteerFragment(), "Volunteer");
        //Log.d("debug", "VolunteerFragment() set");
        //adapter.addFrag(new OneFragment().newInstance("temp"), "details");
        viewPager.setAdapter(adapter);
        Log.d("debug", "adapter set");
    }


    /**
     * todo how about
     */

    public void replaceFragments(OneFragment nextFrag, int containerViewID){//(Class fragmentClass) {
        /*Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }*/
        // Insert the fragment by replacing any existing fragment
        //viewPager.setAdapter(null);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(containerViewID, nextFrag).commit();
    }

}
