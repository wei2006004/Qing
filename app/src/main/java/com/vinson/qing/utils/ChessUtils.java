package com.vinson.qing.utils;

import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessInfo;
import com.vinson.qing.bean.ChessTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessUtils {

    private static final Chess[] chessArray = new Chess[] {
            Chess.JU_B, Chess.MA_B, Chess.XIANG_B, Chess.SHI_B, Chess.PAO_B, Chess.BING_B,
            Chess.JU_R, Chess.MA_R, Chess.XIANG_R, Chess.SHI_R, Chess.PAO_R, Chess.BING_R
    };

    public static Chess intToChess(int value) {
        return chessArray[value];
    }

    public static int chessToInt(Chess chess) {
        int ret = 0;
        for (int i = 0; i < chessArray.length; i++) {
            if (chess == chessArray[i]) {
                ret = i;
                break;
            }
        }
        return ret;
    }

    public static List<ChessInfo> getInitChessList() {
        List<ChessInfo> chessInfos = new ArrayList<>();

        chessInfos.add(new ChessInfo(0, 0, Chess.JU_B));
        chessInfos.add(new ChessInfo(1, 0, Chess.MA_B));
        chessInfos.add(new ChessInfo(2, 0, Chess.XIANG_B));
        chessInfos.add(new ChessInfo(3, 0, Chess.SHI_B));
        chessInfos.add(new ChessInfo(4, 0, Chess.JIANG_B));
        chessInfos.add(new ChessInfo(5, 0, Chess.SHI_B));
        chessInfos.add(new ChessInfo(6, 0, Chess.XIANG_B));
        chessInfos.add(new ChessInfo(7, 0, Chess.MA_B));
        chessInfos.add(new ChessInfo(8, 0, Chess.JU_B));
        chessInfos.add(new ChessInfo(1, 2, Chess.PAO_B));
        chessInfos.add(new ChessInfo(7, 2, Chess.PAO_B));
        chessInfos.add(new ChessInfo(0, 3, Chess.BING_B));
        chessInfos.add(new ChessInfo(2, 3, Chess.BING_B));
        chessInfos.add(new ChessInfo(4, 3, Chess.BING_B));
        chessInfos.add(new ChessInfo(6, 3, Chess.BING_B));
        chessInfos.add(new ChessInfo(8, 3, Chess.BING_B));

        chessInfos.add(new ChessInfo(0, 9, Chess.JU_R));
        chessInfos.add(new ChessInfo(1, 9, Chess.MA_R));
        chessInfos.add(new ChessInfo(2, 9, Chess.XIANG_R));
        chessInfos.add(new ChessInfo(3, 9, Chess.SHI_R));
        chessInfos.add(new ChessInfo(4, 9, Chess.JIANG_R));
        chessInfos.add(new ChessInfo(5, 9, Chess.SHI_R));
        chessInfos.add(new ChessInfo(6, 9, Chess.XIANG_R));
        chessInfos.add(new ChessInfo(7, 9, Chess.MA_R));
        chessInfos.add(new ChessInfo(8, 9, Chess.JU_R));
        chessInfos.add(new ChessInfo(1, 7, Chess.PAO_R));
        chessInfos.add(new ChessInfo(7, 7, Chess.PAO_R));
        chessInfos.add(new ChessInfo(0, 6, Chess.BING_R));
        chessInfos.add(new ChessInfo(2, 6, Chess.BING_R));
        chessInfos.add(new ChessInfo(4, 6, Chess.BING_R));
        chessInfos.add(new ChessInfo(6, 6, Chess.BING_R));
        chessInfos.add(new ChessInfo(8, 6, Chess.BING_R));

        return chessInfos;
    }

    public static ChessTrack moveResultToTask(String result) {
        String[] res = result.trim().split(":");
        ChessTrack track = new ChessTrack();
        track.fromx = getMoveX(res[0]);
        track.fromy = getMoveY(res[0]);
        track.tox = getMoveX(res[1]);
        track.toy = getMoveY(res[1]);
        return track;
    }

    private static int getMoveY(String re) {
        return 9 - (re.charAt(1) - '0');
    }

    private static int getMoveX(String re) {
        return re.charAt(0) - 'a';
    }
}
