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

public abstract class ManualAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Loader.LoadListener<ChessData>{

    public abstract Loader<ChessData> getLoader();

    public void setActionListener(UiActionListener actionListener) {
        this.actionListener = actionListener;
    }

    private UiActionListener actionListener;

    private Context context;

    public void setChessDatas(List<ChessData> chessDatas) {
        this.chessDatas = chessDatas;
        notifyDataSetChanged();
    }

    public void deleteChessData(int position) {
        chessDatas.remove(position);
        notifyItemRemoved(position);
    }

    protected List<ChessData> chessDatas = new ArrayList<>();

    public ManualAdapter(Context context) {
        this.context = context;
        getLoader().addLoadListener(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ManualViewHodler hodler = ManualViewHodler.createViewHodler(context);
        hodler.setActionListener(actionListener);
        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ManualViewHodler) {
            ((ManualViewHodler) holder).bindData(chessDatas.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return chessDatas.size();
    }
}
