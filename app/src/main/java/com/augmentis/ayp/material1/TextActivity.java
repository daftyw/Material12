package com.augmentis.ayp.material1;

import android.animation.Animator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity implements View.OnLayoutChangeListener {

    protected static final String TEXT = "TEXT";
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_text);

        mTextView = (TextView) findViewById(R.id.display_text);

        if(getIntent() != null) {
            mTextView.setText(getIntent().getStringExtra(TEXT));
            mTextView.addOnLayoutChangeListener(this);
        }

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar_on_text);
        setSupportActionBar(tb);

        ActionBar ab = getSupportActionBar();

        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(getIntent().getStringExtra(TEXT));
        }

        final Button button = (Button) findViewById(R.id.text_view_button);
        final Button button2 = (Button) findViewById(R.id.text_view2_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button2.setEnabled( ! button2.isEnabled() );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Log.d("TextActivity", "BACK HOME!");
            supportFinishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        view.removeOnLayoutChangeListener(this);

        int cx = mTextView.getWidth() / 2;
        int cy = mTextView.getHeight() / 2;

        float finalRaius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(mTextView, cx, cy, 0, finalRaius);
        anim.setDuration(500).start();
        mTextView.setVisibility(View.VISIBLE);
    }
}
