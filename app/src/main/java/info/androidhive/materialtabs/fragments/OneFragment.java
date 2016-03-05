package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.androidhive.materialtabs.R;


public class OneFragment extends Fragment{

    private String NAME_TAG;
    private final static String ARGUMENT_TAG = "DETAIL";

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }


}
