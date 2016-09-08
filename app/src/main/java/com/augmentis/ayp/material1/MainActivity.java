package com.augmentis.ayp.material1;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //getWindow().setExitTransition(new Explode());
        //getWindow().setEnterTransition(new Explode());

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_on_main);

        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setIcon(R.drawable.ic_ab_icon);
        }

    }
}
