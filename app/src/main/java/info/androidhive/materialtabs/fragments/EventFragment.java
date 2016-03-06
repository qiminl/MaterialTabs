package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import info.androidhive.materialtabs.R;

public class EventFragment  extends ListFragment implements AdapterView.OnItemClickListener {
    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //todo hmm
        Log.d("debug", "event view created");
        return inflater.inflate(R.layout.fragment_opportunity, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //getActivity().setTitle("Event");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
