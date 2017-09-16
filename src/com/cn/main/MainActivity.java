package com.cn.main;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

public class MainActivity extends Activity {
    ExpandableListView mExpandableListView;
    ExpandableListViewAdapter mExpandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        mExpandableListViewAdapter = new ExpandableListViewAdapter(this);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);
        mExpandableListView.expandGroup(0);
//        openAll() ;
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // TODO Auto-generated method stub
//                Log.e("hefeng", "ExpandableListView GroupClickListener groupPosition=" + groupPosition);
                return false;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Log.e("hefeng", "ExpandableListView ChildClickListener groupPosition=" + groupPosition + "||childPosition=" + childPosition);
                return false;
            }
        });
     
    }

    private void openAll() {
        int groupCount = mExpandableListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            mExpandableListView.expandGroup(i);
        }
        ;
    }

}
