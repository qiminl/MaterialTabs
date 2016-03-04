package info.androidhive.materialtabs.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import info.androidhive.materialtabs.R;

/**
 * Created by liuqi on 3/4/2016.
 */
public class CollaboratorFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collaborator, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),R.array.Planets, android.R.layout.simple_list_item_1);
        String List[] = {"Larry", "Moe", "Curly"};
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.activity_list_item,List);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * StableArrayAdapter implement a sub view of the ListView.
     * customize with pic & text & location.
     *
     */
    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap< Integer, String> mIdMap = new HashMap< Integer, String>();
        private final Context context;

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> diary_list) {
            super(context, textViewResourceId, diary_list);
            this.context = context;
            for (int i = 0; i < diary_list.size(); ++i) {
                mIdMap.put(i, diary_list.get(i));
            }
        }
        /*
        @Override
        public long getItemId(int position) {
            Diary item = getItem(position);
            return mIdMap.get(item);
        }*/
        @Override
        public boolean hasStableIds() {
            return true;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.item_in_list, parent, false);
            //todo create sub view of the list.
/*            TextView textView = (TextView) rowView.findViewById(R.id.diaryTextView1);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.diaryImageView1);
            imageView.setAdjustViewBounds(true);//adjust ratio

            String diary = mIdMap.get(position);
            textView.setText("");
            if(diary != null){
                Uri uri = Uri.parse(diary);
                imageView.setImageURI(uri);
            }

            if(diary!=null) {
                //imageView.setImageBitmap();
            }
            //MediaStore.Images.Media.insertImage(getContentResolver(), yourBitmap, yourTitle , yourDescription);
*/
            return rowView;
        }

    }

}
