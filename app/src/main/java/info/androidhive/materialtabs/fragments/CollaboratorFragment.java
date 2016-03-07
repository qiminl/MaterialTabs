package info.androidhive.materialtabs.fragments;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

/**
 * todo this fragment looks similar to OpportunityFragment. Might combine them later
 * Created by liuqi on 3/4/2016.
 */
public class CollaboratorFragment extends ListFragment implements AdapterView.OnItemClickListener {
    //private int fragNum;
    //list of Collaborators
    private ArrayList<String> list = new ArrayList<>();

    public CollaboratorFragment() {
    }

    /*static CollaboratorFragment init(int val) {
        CollaboratorFragment collaboratorList = new CollaboratorFragment();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        collaboratorList.setArguments(args);
        return collaboratorList;
    }*/

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //todo why would I need to track fragNum?
        //todo make it as String/dynamic
        getActivity().setTitle("Collaborator");
        //fragNum = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        list.clear();
        //todo create company class for this
        list.add("Company 1");        list.add("Company 2");        list.add("Company 3");
        list.add("Company 4");        list.add("Company 5");
        View view = inflater.inflate(R.layout.fragment_collaborator, container, false);
        //Log.d("debug", "collaborator view created");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);

        //todo #!@@#!@$%

        getActivity().setTitle("Collaborator");
        StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.item_in_list, list);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        //getActivity().setTitle("Collaborator");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * StableArrayAdapter class is used to implement a sub view of the ListView.
     * customize with pic & text & location.
     * todo make this class as a public abstract that implement by each fragment
     */
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

        /*@Override
        public long getItemId(int position) {
            Diary item = getItem(position);
            return mIdMap.get(item);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }*/

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

    }

}
