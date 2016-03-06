package info.androidhive.materialtabs.activity;

import android.app.Activity;
import android.os.Bundle;

public class EmptyActivity extends Activity {

    private String TAG = "not set";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle temp = getIntent().getExtras();
        TAG = temp.getString("name");
    }

    @Override
    protected void onStart() {
        super.onStart();    }
}
