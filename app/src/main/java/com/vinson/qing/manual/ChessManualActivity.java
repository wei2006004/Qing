package com.vinson.qing.manual;

import android.content.Intent;
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
import com.vinson.qing.bean.ChessData;

public class ChessManualActivity extends AppCompatActivity {

    private static final int PAGE_LOCAL = 0;
    private static final int PAGE_NETWORK = 1;

    private ManualAdapter localAdapter;
    private ManualAdapter networkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initAdapter();
        initViewPager();
    }

    private void initAdapter() {
        localAdapter = new LocalManualAdapter(this);
        localAdapter.getLoader().loadDatas();
        localAdapter.setActionListener(new UiActionListener() {
            @Override
            public void onClick(ChessData chessData, int position) {
                openManualPlayActivity(chessData);
            }

            @Override
            public void onLongPressed(ChessData chessData, int position) {
            }
        });
        networkAdapter = new NetworkManualAdapter(this);
        networkAdapter.setActionListener(new UiActionListener() {
            @Override
            public void onClick(ChessData chessData, int position) {
                openManualPlayActivity(chessData);
            }

            @Override
            public void onLongPressed(ChessData chessData, int position) {
            }
        });
    }

    private void openManualPlayActivity(ChessData chessData) {
        Intent intent = new Intent(this, ManualPlayActivity.class);
        startActivity(intent);
    }

    private void initViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(ChessManualActivity.this).inflate(R.layout.recyclerview, container, false);
                recyclerView.setLayoutManager(new LinearLayoutManager(ChessManualActivity.this));
                if (position == PAGE_LOCAL) {
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == PAGE_NETWORK) {
                    networkAdapter.getLoader().loadDatas();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localAdapter.destroy();
        networkAdapter.destroy();
    }
}
