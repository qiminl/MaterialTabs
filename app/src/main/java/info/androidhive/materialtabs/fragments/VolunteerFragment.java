package info.androidhive.materialtabs.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import info.androidhive.materialtabs.R;

public class VolunteerFragment extends ListFragment implements AdapterView.OnItemClickListener {
    public VolunteerFragment() {
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
        //Log.d("debug", "Volunteer view created");
        return inflater.inflate(R.layout.fragment_opportunity, container, false);
    }

    @Override
    public void onStart() {

        //getActivity().setTitle("Volunteer");
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
