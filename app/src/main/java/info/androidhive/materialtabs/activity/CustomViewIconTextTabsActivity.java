package info.androidhive.materialtabs.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

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
    private String DETAIL_TAG = "detail_tag";//todo make it public in @string

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
        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException e){
            Log.d("debug","should handle null pointer exception @ CustomView");
        }
        Log.d("debug", "toolbar set");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //view page listener
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        Log.d("debug", "viewPager set");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        super.onTabSelected(tab);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        String title = (String) tab.getText();
                        setTitle(title);
                        List<Fragment> fragments = fragmentManager.getFragments();
                        //Log.d("debug", " fragment size =  " + fragments.size());
                        for(Fragment i : fragments){
                            if(i != null) {
                                if (i.getTag() != DETAIL_TAG) {
                                    fragmentManager.beginTransaction().hide(i).commit();
                                } else {
                                    Fragment myFrag = adapter.getItem("Opportunity");
                                    //Log.d("debug", " fragment selected =  " + myFrag.getTag());
                                    fragmentManager.beginTransaction().show(myFrag).commit();
                                    fragmentManager.beginTransaction().remove(i).commit();
                                }
                            }
                        }
                        if(tab.getText() == "Opportunity"){
                            Fragment myFrag = adapter.getItem("Opportunity");
                            //Log.d("debug", " fragment selected =  " + myFrag.getTag());
                            fragmentManager.beginTransaction().show(myFrag).commit();
                        }else if(tab.getText() == "Event"){
                            Fragment myFrag = adapter.getItem("Event");
                            //Log.d("debug", " fragment selected =  " + myFrag.getTag());
                            fragmentManager.beginTransaction().show(myFrag).commit();
                        }else if(tab.getText() == "Collaborator"){
                            Fragment myFrag = adapter.getItem("Collaborator");
                            //Log.d("debug", " fragment selected =  " + myFrag.getTag());
                            fragmentManager.beginTransaction().show(myFrag).commit();
                        }else if(tab.getText() == "Volunteer"){
                            Fragment myFrag = adapter.getItem("Volunteer");
                            //Log.d("debug", " fragment selected =  " + myFrag.getTag());
                            fragmentManager.beginTransaction().show(myFrag).commit();
                        }
                        //todo clean the unused code
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
     * @param viewPager viewPager area in xml
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

    public void replaceFragments(OneFragment nextFrag, int containerViewID, String tag){//(Class fragmentClass) {
        /*Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        }catch (Exception e) {
            e.printStackTrace();
        }*/
        // Insert the fragment by replacing any existing fragment
        //viewPager.setAdapter(null);


        FragmentManager fragmentManager = getSupportFragmentManager();

        //remove existing detail fragment
        Log.d("debug", " fragment size b/4 remove =  " + fragmentManager.getFragments().size());
        Fragment myFrag2 = fragmentManager.findFragmentByTag(DETAIL_TAG);
        if(myFrag2 != null) {
            Log.d("debug", " fragment detail_tag =  " + DETAIL_TAG);
            Log.d("debug", " fragment detail_tag =  " + myFrag2.getTag());

            fragmentManager.beginTransaction().show(myFrag2).commit();
            fragmentManager.beginTransaction().remove(myFrag2).commit();

            fragmentManager.popBackStack();
        }
        Log.d("debug", " fragment size after remove =  " + fragmentManager.getFragments().size());

        //hide current fragment.
        Fragment myFrag = fragmentManager.findFragmentByTag(tag);
        if(myFrag != null)
            fragmentManager.beginTransaction().hide(myFrag).commit();
        //create detail fragment with DETAIL_TAG as tag
        fragmentManager.beginTransaction().replace(containerViewID, nextFrag, DETAIL_TAG).commit();
    }

}
