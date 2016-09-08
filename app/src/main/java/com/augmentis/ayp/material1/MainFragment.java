package com.augmentis.ayp.material1;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rawin on 08-Sep-16.
 */
public class MainFragment extends Fragment {

    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new TempAdapter());

        return v;
    }

    private class TempHolder extends RecyclerView.ViewHolder
        implements View.OnTouchListener, View.OnClickListener {
        TextView mTextView;
        String mText;

        TempHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.item_main_text);

            //itemView.setOnTouchListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = MotionEventCompat.getActionMasked(motionEvent);
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    view.animate()
                            .translationZ(20.0f)
                            .setDuration(500)
                            .start();
                    return true;

                case MotionEvent.ACTION_UP:
                    view.animate()
                            .translationZ(0.0f)
                            .start();
                    return true;
            }
            return false;
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(), TextActivity.class);
            i.putExtra(TextActivity.TEXT, mText);

            String targetText = getString(R.string.target_text);
            Bundle bundle = ActivityOptions
                    .makeSceneTransitionAnimation(getActivity(),itemView, targetText)
                    .toBundle();
            startActivity(i, bundle);
        }

        public void bindText (String displayText, int minHeight) {
            mText = displayText;
            mTextView.setText(displayText);
            mTextView.setMinHeight(100 + minHeight);
        }
    }

    private class TempAdapter extends RecyclerView.Adapter<TempHolder> {

        List<Integer> mHeightList;

        TempAdapter() {
            mHeightList = new ArrayList<>();
            for(int i = 0; i < 40; i++) {
                long height = Math.round(Math.random() * 400);
                mHeightList.add(Long.valueOf(height).intValue());
            }
        }

        @Override
        public TempHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.item_main, parent, false);
            return new TempHolder(v);
        }

        @Override
        public void onBindViewHolder(TempHolder holder, int position) {
            String displayText = "Position " + (position + 1);
            holder.bindText(displayText, mHeightList.get(position));
        }

        @Override
        public int getItemCount() {
            return mHeightList.size();
        }
    }

}
