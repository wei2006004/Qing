package com.vinson.qing.loader;

import java.util.List;

/**
 * Created by Vinson on 2017/5/9.
 * e-mail: wei2006004@foxmail.com
 */
public class ChessRecord {
    String name;
    String contest;
    String site;
    String redPlayer;
    String blackPlayer;
    String date;
    int result;
    String fen;
    String ecco;
    List<String> moveList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContest() {
        return contest;
    }

    public void setContest(String contest) {
        this.contest = contest;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getRedPlayer() {
        return redPlayer;
    }

    public void setRedPlayer(String redPlayer) {
        this.redPlayer = redPlayer;
    }

    public String getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(String blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public String getEcco() {
        return ecco;
    }

    public void setEcco(String ecco) {
        this.ecco = ecco;
    }

    public List<String> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<String> moveList) {
        this.moveList = moveList;
    }
}
