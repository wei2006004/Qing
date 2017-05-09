package com.vinson.qing.loader;

import com.vinson.qing.bean.MoveResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Vinson on 2017/4/24.
 * e-mail: wei2006004@foxmail.com
 */
public interface ChessDataService {

    @GET("/chess-list")
    Observable<List<ChessRecord>> getChessList();

    @GET("/bestMove")
    Call<MoveResult> bestMove(@Query("fen") String fen, @Query("side") int side);
}
