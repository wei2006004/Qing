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

import com.vinson.qing.BaseActivity;
import com.vinson.qing.R;
import com.vinson.qing.bean.ChessData;
import com.vinson.qing.widget.AppBar;

import butterknife.BindView;

public class ChessManualActivity extends BaseActivity {

    private static final int PAGE_LOCAL = 0;
    private static final int PAGE_NETWORK = 1;

    private ManualAdapter localAdapter;
    private ManualAdapter networkAdapter;

    @BindView(R.id.appbar)
    AppBar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter();
        initViewPager();

        appBar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appBar.setTitle("棋谱");
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
        networkAdapter.getLoader().loadDatas();
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
        ManualPlayActivity.startActivity(this, chessData);
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }
}
