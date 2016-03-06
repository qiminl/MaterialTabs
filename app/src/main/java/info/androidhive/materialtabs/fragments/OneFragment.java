package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import info.androidhive.materialtabs.R;


public class OneFragment extends ListFragment implements AdapterView.OnItemClickListener{

    private String NAME_TAG;
    private final static String ARGUMENT_TAG = "DETAIL";

    private ArrayList<String> commantList = new ArrayList<>();
    private TextView title, date, description, like;
    private ImageView logo;
    private FloatingActionButton commant, fab;
    //private ListView listView;

    public OneFragment() {
        // Required empty public constructor
    }

    public static OneFragment newInstance(String  Opportunity) {
        OneFragment myFragment = new OneFragment();

        Bundle args = new Bundle();
        args.putString(ARGUMENT_TAG, Opportunity);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        NAME_TAG = getArguments().getString(ARGUMENT_TAG);
        commantList.add("comment 1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        //todo build db to fed data (a DB handler will be great)
        title = (TextView) view.findViewById(R.id.title);
        title.setText(NAME_TAG);

        date= (TextView) view.findViewById(R.id.date);
        date.setText("date");

        description= (TextView) view.findViewById(R.id.description);
        //description.setText("");

        like= (TextView) view.findViewById(R.id.like);

        logo= (ImageView) view.findViewById(R.id.image);

        commant =  (FloatingActionButton) view.findViewById(R.id.commant);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        //listView = (ListView) view.findViewById(R.id.list_item);
        StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.item_in_list, commantList);
        //todo create a pool for adpater to work on memory
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("debug", "position=" + position + "; id=" + id);
        Log.d("debug", "view.getTag() = " + view.getTag() );
        Log.d("debug", "parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
        //todo decide using activity or fragment

        String object_name = (String)parent.getItemAtPosition(position);
        /*
        //jumping to detail of selected object
        OneFragment nextFrag= new OneFragment().newInstance(object_name);
        this.getFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), nextFrag,null)
                .addToBackStack(null)
                .commit();*/
    }
    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap< Integer, String> mIdMap = new HashMap< Integer, String>();
        private final Context context;

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  ArrayList<String> commantList) {

            super(context, textViewResourceId, commantList);
            this.context = context;
            for (int i = 0; i < commantList.size(); ++i) {
                mIdMap.put(i, commantList.get(i));
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

            String comment = mIdMap.get(position);
            //todo handle image properly, image online or local sd or sqlite
            if(comment != null){
                textView.setText(comment);
                switch (comment.toLowerCase()){
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
        //can set motion for each pic/text
        private AdapterView.OnItemClickListener mOnGalleryClick = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            }
        };

    }

}
