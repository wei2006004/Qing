package com.vinson.qing.manual;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.vinson.qing.bean.ChessData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/18.
 * e-mail: wei2006004@foxmail.com
 */

public class ManualAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public void setChessDatas(List<ChessData> chessDatas) {
        this.chessDatas = chessDatas;
        notifyDataSetChanged();
    }

    protected List<ChessData> chessDatas = new ArrayList<>();

    public ManualAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ManualViewHodler.createViewHodler(context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ManualViewHodler) {
            ((ManualViewHodler) holder).bindData(chessDatas.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return chessDatas.size();
    }
}
