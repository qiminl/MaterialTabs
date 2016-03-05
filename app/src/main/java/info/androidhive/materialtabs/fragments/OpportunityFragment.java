package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.activity.ViewPagerAdapter;

/**
 * Created by liuqi on 3/4/2016.
 */
public class OpportunityFragment extends ListFragment implements AdapterView.OnItemClickListener{

    private ViewPager viewPager;
    private TextView textView;
    private ArrayList<String> list = new ArrayList<>();

    public OpportunityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo make it as String/dynamic
        getActivity().setTitle("Opportunity");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Log.d("debug", "OpportunityFragment view creating");
        Log.d("debug", " collaborator onCreateView");
        list.clear();
        list.add("Company 1");        list.add("Company 2");        list.add("Company 3");
        list.add("Company 4");        list.add("Company 5");
        View view = inflater.inflate(R.layout.fragment_opportunity, container, false);
        Log.d("debug", "collaborator view created");

        //Log.d("debug", " OpportunityFragment view created");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);


        //todo use fragment or activity for details??
        /*viewPager = (ViewPager) getView().findViewById(R.id.viewpager);
        ViewPagerAdapter adapter2 = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter2.addFrag(new VolunteerFragment(), "Volunteer");
        //Log.d("debug", "VolunteerFragment() set");
        viewPager.setAdapter(adapter2);
        getListView().setupWithViewPager();*/

        //todo should move to onCreateView later, now here for testing purpose
        StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.item_in_list, list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("debug","position=" + position + "; id="+id);
        Log.d("debug", "view.getTag() = " + view.getTag() );
        Log.d("debug", "parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
        //todo decide using activity or fragment

        OneFragment nextFrag= new OneFragment().newInstance("Opportunity");
        this.getFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), nextFrag,null)
                .addToBackStack(null)
                .commit();

        //parent.getItemAtPosition(position);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap< Integer, String> mIdMap = new HashMap< Integer, String>();
        private final Context context;

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> company_list) {

            super(context, textViewResourceId, company_list);
            this.context = context;
            for (int i = 0; i < company_list.size(); ++i) {
                mIdMap.put(i, company_list.get(i));
            }
        }

        @Override
        public int getCount(){
            return mIdMap.size();
        }

        @Override
        public String getItem(int position){
            return mIdMap.get(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.item_in_list, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.text1);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image1);
            imageView.setAdjustViewBounds(true);//adjust ratio

            String company = mIdMap.get(position);
            //todo handle image properly, image online or local sd or sqlite
            if(company != null){
                textView.setText(company);
                switch (company.toLowerCase()){
                    case "company 1":
                        imageView.setImageResource(R.drawable.testpic);
                        break;
                    case "company 2":
                        imageView.setImageResource(R.drawable.testpic2);
                        break;
                    default:
                        imageView.setImageResource(R.drawable.testpic3);
                        break;
                }
            }
            //MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, yourTitle , yourDescription);
            return rowView;
        }
        //can set motion for pic/text
        private AdapterView.OnItemClickListener mOnGalleryClick = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            }
        };

    }
}
