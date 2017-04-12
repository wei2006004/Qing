package com.vinson.qing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
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

    private int childWidth;

    private boolean fixScale;

    private BoardDrawer boardDrawer;
    private ViewDragHelper dragHelper;

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
        initDragger();
    }

    private void initDragger() {
        dragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child instanceof ChessView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left < getPaddingLeft()) {
                    return getPaddingLeft();
                }
                if (left + childWidth > getWidth() - getPaddingRight()) {
                    return getWidth() - childWidth - getPaddingRight();
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top < getPaddingTop()) {
                    return getPaddingTop();
                }
                if (top + childWidth > getHeight() - getPaddingBottom()) {
                    return getHeight() - childWidth - getPaddingBottom();
                }
                return top;
            }
        });
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return dragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    public void addChess(ChessInfo info) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(new ChessView(getContext(), info), layoutParams);
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
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (!(child instanceof ChessView)) {
                throw new IllegalStateException("child must be chessveiw");
            }
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            ChessInfo info = ((ChessView) child).getChessInfo();
            int centerx = getBoardXByIndex(info.x);
            int centery = getBoardYByIndex(info.y);
            child.layout(centerx - width / 2, centery - height / 2, centerx + width / 2, centery + height / 2);
        }
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

        setMeasuredDimension(sizeWidth, sizeHeight);
        initBoardParam(sizeWidth, sizeHeight);

        int childMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
        for (int i = 0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.measure(childMeasureSpec, childMeasureSpec);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boardDrawer.drawBoard(canvas);
    }

    private void initBoardParam(int width, int height) {
        int boardPadding = DimenUtils.dp2px(BROAD_PADDING);
        int rwidth = width - 2 * boardPadding - getPaddingLeft() - getPaddingRight();
        int rheight = height - 2 * boardPadding - getPaddingTop() - getPaddingBottom();
        float eachW = rwidth / (float) (BROAD_X_MAX_INDEX + 1);
        float eachH = rheight / (float) (BROAD_Y_MAX_INDEX + 1);
        childWidth = (int) ((eachH > eachW ? eachW : eachH) * 0.85);

        int padding = boardPadding + childWidth / 2;
        int top = getPaddingTop() + padding;
        int left = getPaddingLeft() + padding;

        startx = left;
        starty = top;
        boardWidth = rwidth - childWidth;
        boardHeight = rheight - childWidth;

        boardDrawer.startx = startx;
        boardDrawer.starty = starty;
        boardDrawer.boardWidth = boardWidth;
        boardDrawer.boardHeight = boardHeight;
    }

    private int getBoardXByIndex(int index) {
        return (int) (index * boardWidth / (float) BROAD_X_MAX_INDEX) + startx;
    }

    private int getBoardYByIndex(int index) {
        return (int) (index * boardHeight / (float) BROAD_Y_MAX_INDEX) + starty;
    }
}
