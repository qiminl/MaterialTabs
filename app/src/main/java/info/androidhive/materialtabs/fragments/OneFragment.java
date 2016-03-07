package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import info.androidhive.materialtabs.Comment;
import info.androidhive.materialtabs.DBHandler;
import info.androidhive.materialtabs.R;


public class OneFragment extends ListFragment implements AdapterView.OnItemClickListener{

    private String NAME_TAG = "not set";
    private final static String ARGUMENT_TAG = "DETAIL";

    private String DETAIL_TAG = "detail_tag";
    private ArrayList<String> commentList = new ArrayList<>();
    private ArrayList<Comment> comment_list = new ArrayList<>();
    private TextView title, date, description, like;
    private ImageView logo;
    private FloatingActionButton commant, fab;
    private ListView listView;
    private int number_like = 0;

    public OneFragment() {
        // Required empty public constructor
    }

    public OneFragment newInstance(String  Opportunity) {
        OneFragment myFragment = new OneFragment();

        Bundle args = new Bundle();
        args.putString(ARGUMENT_TAG, Opportunity);
        if(myFragment.getArguments() == null)
            myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if(getArguments() !=null)
            NAME_TAG = getArguments().getString(ARGUMENT_TAG);
        Log.d("debug", "NAME_TAG" + NAME_TAG);
        comment_list.add(new Comment("1", "1", "1", "none", "none", "wow wow wow wow"));
        comment_list.add(new Comment("2","1","1","none","none","la la la la"));
        comment_list.add(new Comment("3","1","1","none","none","wow wow wow wow"));
        comment_list.add(new Comment("4","1","1","none","none","la la la la"));
        comment_list.add(new Comment("5","1","1","none","none","wow wow wow wow"));

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
        //Log.d("debug", "getTargetFragment().getTag() = " + getTargetFragment().getTag());
        //todo build db to fed data (a DB handler will be great)
        title = (TextView) view.findViewById(R.id.title);
        title.setText("TITLE BA LA BA LA BA LA \n "+ " ba la ba la ");

        date= (TextView) view.findViewById(R.id.date);
        date.setText("date");

        description= (TextView) view.findViewById(R.id.description);
        description.setText("ba la ba la ba la \n");
        Log.d("debug", "view created");

        like= (TextView) view.findViewById(R.id.like);
        number_like += 1024;
        like.setText("Like +"+ Integer.toString(number_like));

        logo= (ImageView) view.findViewById(R.id.image);
        logo.setImageResource(R.drawable.testpic3);

        //commant =  (FloatingActionButton) view.findViewById(R.id.comment);
        //fab = (FloatingActionButton) view.findViewById(R.id.fab);

        DBHandler myDiaryDBHandler = new DBHandler(this.getContext());

        commentList.add(myDiaryDBHandler.findCommentByID("1").getCOLUMN_COMMENT());
        commentList.add(myDiaryDBHandler.findCommentByID("2").getCOLUMN_COMMENT());
        commentList.add(myDiaryDBHandler.findCommentByID("3").getCOLUMN_COMMENT());
        commentList.add(myDiaryDBHandler.findCommentByID("4").getCOLUMN_COMMENT());

        listView = (ListView) view.findViewById(android.R.id.list);
        StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), R.layout.item_in_list, commentList);
        //todo create a pool for adpater to work on memory
        setListAdapter(adapter);
        //getListView().setOnItemClickListener(this);
        getListView().setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.d("debug", "position=" + position + "; id=" + id);
        //Log.d("debug", "view.getTag() = " + view.getTag() );
        //Log.d("debug", "parent.getItemAtPosition(position) = " + parent.getItemAtPosition(position));
        //todo decide using activity or fragment

        String object_name = (String)parent.getItemAtPosition(position);

        //jumping to detail of selected object
        OneFragment nextFrag= new OneFragment().newInstance(object_name);
        this.getFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(), nextFrag,null)
                .addToBackStack(null)
                .commit();
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
