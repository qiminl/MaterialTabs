package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.androidhive.materialtabs.R;

/**
 * Created by liuqi on 3/4/2016.
 */
public class OpportunityFragment extends Fragment {

    private TextView textView;
    public OpportunityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_opportunity, container, false);
        textView = (TextView) view.findViewById(R.id.text1);
        textView.setText("OPPORTUNITY");
        return view;
    }

}
