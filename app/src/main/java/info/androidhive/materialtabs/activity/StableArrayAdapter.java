package info.androidhive.materialtabs.activity;

import android.content.Context;
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


public class StableArrayAdapter extends ArrayAdapter<String> {
    HashMap< Integer, String> mIdMap = new HashMap<>();
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