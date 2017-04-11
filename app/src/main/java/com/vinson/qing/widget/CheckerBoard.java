package com.vinson.qing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.vinson.qing.R;
import com.vinson.qing.bean.Chess;
import com.vinson.qing.bean.ChessInfo;
import com.vinson.qing.utils.ChessUtils;
import com.vinson.qing.utils.DimenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.vinson.qing.widget.BoardDrawer.BROAD_X_MAX_INDEX;
import static com.vinson.qing.widget.BoardDrawer.BROAD_Y_MAX_INDEX;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class CheckerBoard extends ViewGroup {

    private final static int BROAD_PADDING = 10;

    private final static int MIN_WIDTH = 240;
    private final static int MIN_HEIGHT = 270;

    private int boardWidth;
    private int boardHeight;

    private int startx;
    private int starty;

    private boolean fixScale;

    private BoardDrawer boardDrawer;

    public CheckerBoard(Context context) {
        this(context, null);
    }

    public CheckerBoard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckerBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckerBoard, defStyleAttr, 0);

        List<ChessInfo> chessInfos = new ArrayList<>();
        for (int i = 0; i < array.getIndexCount(); i++) {
            if (array.getIndex(i) == R.styleable.CheckerBoard_fixScale) {
                fixScale = array.getBoolean(i, false);
            }
            if (array.getIndex(i) == R.styleable.CheckerBoard_initChess) {
                boolean initChess = array.getBoolean(i, false);
                if (initChess) {
                    chessInfos = new ArrayList<>(ChessUtils.getInitChessList());
                }
            }
        }
        array.recycle();

        boardDrawer = new BoardDrawer();
        setWillNotDraw(false);

        setChessList(chessInfos);
    }

    public void addChess(ChessInfo info) {
        addView(new ChessView(getContext(), info));
    }

    public void addChess(int x, int y, Chess chess) {
        addChess(new ChessInfo(x, y, chess));
    }

    public void setChessList(List<ChessInfo> list) {
        for (ChessInfo info : list) {
            addChess(info);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            sizeWidth = DimenUtils.dp2px(MIN_WIDTH);
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            sizeHeight = DimenUtils.dp2px(MIN_HEIGHT);
        }
        if (fixScale) {
            sizeHeight = (int) (sizeWidth / (float) BROAD_X_MAX_INDEX * BROAD_Y_MAX_INDEX);
        }
        float eachW = sizeWidth / (float) BROAD_X_MAX_INDEX;
        float eachH = sizeHeight / (float) BROAD_Y_MAX_INDEX;
        int childWidth = (int)((eachH > eachW ? eachW : eachH) * 0.8);
        measureChildren(MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY));
        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initBoardParam();
        boardDrawer.drawBoard(canvas);
    }

    private void initBoardParam() {
        int width = getWidth();
        int height = getHeight();

        int padding = DimenUtils.dp2px(BROAD_PADDING);
        int top = getPaddingTop() + padding;
        int left = getPaddingLeft() + padding;
        int right = getPaddingRight() + padding;
        int bottom = getPaddingBottom() + padding;

        startx = left;
        starty = top;
        boardWidth = width - right - startx;
        boardHeight = height - bottom - starty;

        boardDrawer.startx = startx;
        boardDrawer.starty = starty;
        boardDrawer.boardWidth = boardWidth;
        boardDrawer.boardHeight = boardHeight;
    }

    private int getBoardXByIndex(int index) {
        return (int) (index * boardWidth / (float) BROAD_X_MAX_INDEX);
    }

    private int getBoardYByIndex(int index) {
        return (int) (index * boardHeight / (float) BROAD_Y_MAX_INDEX);
    }
}
