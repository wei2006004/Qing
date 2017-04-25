package com.vinson.qing.loader;

import com.vinson.qing.bean.ChessData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vinson on 2017/4/24.
 * e-mail: wei2006004@foxmail.com
 */
public interface ChessDataService {

    @GET("/chess-list")
    Call<List<ChessData>> getChessList();
}
