package com.vinson.qing.bean;

import com.vinson.qing.utils.ChessUtils;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by Vinson on 2017/4/28.
 * e-mail: wei2006004@foxmail.com
 */

public class ChessConverter implements PropertyConverter<Chess, Integer> {

    @Override
    public Chess convertToEntityProperty(Integer databaseValue) {
        return ChessUtils.intToChess(databaseValue);
    }

    @Override
    public Integer convertToDatabaseValue(Chess entityProperty) {
        return ChessUtils.chessToInt(entityProperty);
    }
}
