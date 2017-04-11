package com.vinson.qing.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.vinson.qing.R;
import com.vinson.qing.utils.DimenUtils;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class CheckerBoard extends ViewGroup {
    private final static int BROAD_PADDING = 10;

    private final static int BROAD_X_MAX_INDEX = 8;
    private final static int BROAD_Y_MAX_INDEX = 9;

    private final static int MIN_WIDTH = 240;
    private final static int MIN_HEIGHT = 270;

    private int boardWidth;
    private int boardHeight;

    private int startx;
    private int starty;

    private boolean fixScale;

    private Paint mLinePaint;

    public CheckerBoard(Context context) {
        this(context, null);
    }

    public CheckerBoard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckerBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CheckerBoard, defStyleAttr, 0);
        for (int i = 0 ;i< array.getIndexCount();i++){
            if (array.getIndex(i) == R.styleable.CheckerBoard_fixScale) {
                fixScale = array.getBoolean(i, false);
            }
        }
        initPaint();
        setWillNotDraw(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(2);
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
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initBoardParam();
        drawBoard(canvas);
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
    }

    private void drawBoard(Canvas canvas) {
        drawBoardLines(canvas);
        drawAnnotations(canvas);
    }

    private void drawAnnotations(Canvas canvas) {
        drawAnnotation(canvas, 1, 2);
        drawAnnotation(canvas, 1, 7);
        drawAnnotation(canvas, 7, 2);
        drawAnnotation(canvas, 7, 7);

        for (int i = 0; i < 9; i += 2) {
            drawAnnotation(canvas, i, 3);
        }

        for (int i = 0; i < 9; i += 2) {
            drawAnnotation(canvas, i, 6);
        }
    }

    private void drawAnnotation(Canvas canvas, int x, int y) {
        int centerx = getBoardXByIndex(x) + startx;
        int centery = getBoardYByIndex(y) + starty;
        int length = (int) (boardWidth / 8.0 * 0.35);
        int padding = (int) (boardWidth / 8.0 * 0.1);

        if (x == 0) {
            drawYLine(canvas, padding, -padding, -padding - length, centerx, centery);
            drawYLine(canvas, padding, padding, padding + length, centerx, centery);
            drawXLine(canvas, -padding, padding, padding + length, centerx, centery);
            drawXLine(canvas, padding, padding, padding + length, centerx, centery);
            return;
        }

        if (x == 8) {
            drawXLine(canvas, -padding, -padding, -padding - length, centerx, centery);
            drawXLine(canvas, padding, -padding, -padding - length, centerx, centery);
            drawYLine(canvas, -padding, -padding, -padding - length, centerx, centery);
            drawYLine(canvas, -padding, padding, padding + length, centerx, centery);
            return;
        }
        drawXLine(canvas, -padding, -padding, -padding - length, centerx, centery);
        drawXLine(canvas, -padding, padding, padding + length, centerx, centery);
        drawYLine(canvas, -padding, -padding, -padding - length, centerx, centery);
        drawYLine(canvas, -padding, padding, padding + length, centerx, centery);

        drawYLine(canvas, padding, -padding, -padding - length, centerx, centery);
        drawYLine(canvas, padding, padding, padding + length, centerx, centery);
        drawXLine(canvas, padding, -padding, -padding - length, centerx, centery);
        drawXLine(canvas, padding, padding, padding + length, centerx, centery);
    }

    private void drawBoardLines(Canvas canvas) {
        for (int i = 0; i <= BROAD_Y_MAX_INDEX; i++) {
            drawBoardXLine(canvas, i, 0, BROAD_X_MAX_INDEX);
        }

        drawBoardYLine(canvas, 0, 0, BROAD_Y_MAX_INDEX);
        drawBoardYLine(canvas, BROAD_X_MAX_INDEX, 0, BROAD_Y_MAX_INDEX);

        for (int i = 1; i <= BROAD_X_MAX_INDEX - 1; i++) {
            drawBoardYLine(canvas, i, 0, 4);
        }

        for (int i = 1; i <= BROAD_X_MAX_INDEX - 1; i++) {
            drawBoardYLine(canvas, i, 5, BROAD_Y_MAX_INDEX);
        }

        // 斜线
        drawBoardLine(canvas, 3, 0, 5, 2);
        drawBoardLine(canvas, 3, 2, 5, 0);

        drawBoardLine(canvas, 3, BROAD_Y_MAX_INDEX, 5, 7);
        drawBoardLine(canvas, 3, 7, 5, BROAD_Y_MAX_INDEX);
    }

    private void drawBoardXLine(Canvas canvas, int y, int formX, int toX) {
        drawBoardLine(canvas, formX, y, toX, y);
    }

    private void drawBoardYLine(Canvas canvas, int x, int formY, int toY) {
        drawBoardLine(canvas, x, formY, x, toY);
    }

    private void drawBoardLine(Canvas canvas, int fromx, int formY, int tox, int toY) {
        drawLine(canvas,
                getBoardXByIndex(fromx),
                getBoardYByIndex(formY),
                getBoardXByIndex(tox),
                getBoardYByIndex(toY),
                startx, starty);
    }

    private int getBoardXByIndex(int index) {
        return (int) (index * boardWidth / (float) BROAD_X_MAX_INDEX);
    }

    private int getBoardYByIndex(int index) {
        return (int) (index * boardHeight / (float) BROAD_Y_MAX_INDEX);
    }

    private void drawXLine(Canvas canvas, int y, int formX, int toX, int tX, int tY) {
        drawLine(canvas, formX, y, toX, y, tX, tY);
    }

    private void drawYLine(Canvas canvas, int x, int formY, int toY, int tX, int tY) {
        drawLine(canvas, x, formY, x, toY, tX, tY);
    }

    private void drawLine(Canvas canvas, int fromx, int formY, int tox, int toY, int tX, int tY) {
        canvas.drawLine(fromx + tX, formY + tY, tox + tX, toY + tY, mLinePaint);
    }
}
