package com.vinson.qing.manual;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.vinson.qing.R;

public class ChessManualActivity extends AppCompatActivity {

    private ManualAdapter localAdapter;
    private ManualAdapter networkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        localAdapter = new ManualAdapter(this);
        localAdapter.setChessDatas(ChessLocalLoader.getInstance().getChessDatas());
        networkAdapter = new ManualAdapter(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(ChessManualActivity.this).inflate(R.layout.recyclerview, container, false);
                recyclerView.setLayoutManager(new LinearLayoutManager(ChessManualActivity.this));
                if (position == 0) {
                    recyclerView.setAdapter(localAdapter);
                } else {
                    recyclerView.setAdapter(networkAdapter);
                }
                container.addView(recyclerView);
                return recyclerView;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }
}
