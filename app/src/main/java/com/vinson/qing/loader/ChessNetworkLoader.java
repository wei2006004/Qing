package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;
import com.vinson.qing.utils.L;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vinson on 2017/4/19.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessNetworkLoader extends Loader<ChessData> {

    private Retrofit retrofit;

    public ChessNetworkLoader() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.60.104.60:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void loadDatas() {
        ChessDataService service = retrofit.create(ChessDataService.class);
        Call<List<ChessData>> call = service.getChessList();
        call.enqueue(new Callback<List<ChessData>>() {
            @Override
            public void onResponse(Call<List<ChessData>> call, Response<List<ChessData>> response) {
                chessDatas.clear();
                chessDatas.addAll(response.body());
                notifyLoadDoneToMainThread(0, 0, chessDatas);
            }

            @Override
            public void onFailure(Call<List<ChessData>> call, Throwable t) {
            }
        });
    }

    @Override
    public List<ChessData> getLastLoadData() {
        return chessDatas;
    }

    private List<ChessData> chessDatas = new ArrayList<>();
}
