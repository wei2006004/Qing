package com.vinson.qing.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vinson on 2017/4/11.
 * e-mail: wei2006004@foxmail.com
 */

public class CheckerBoard extends View {
    private final static int BROAD_PADDING = 30;
    private final static int ANNOTATION_PADDING = 8;

    private Paint mLinePaint;

    public CheckerBoard(Context context) {
        this(context, null);
    }

    public CheckerBoard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckerBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int top = getPaddingTop() + BROAD_PADDING;
        int left = getPaddingLeft() + BROAD_PADDING;
        int right = getPaddingRight() + BROAD_PADDING;
        int bottom = getPaddingBottom() + BROAD_PADDING;

        drawBoard(canvas, left, top, width - right, height - bottom);
    }

    private void drawBoard(Canvas canvas, int startx, int starty, int endx, int endy) {
        drawBoardLines(canvas, startx, starty, endx, endy);
        drawAnnotations(canvas, startx, starty, endx, endy);
    }

    private void drawAnnotations(Canvas canvas, int startx, int starty, int endx, int endy) {
        drawAnnotation(canvas, 1, 2, startx, starty, endx, endy);
        drawAnnotation(canvas, 1, 7, startx, starty, endx, endy);
        drawAnnotation(canvas, 7, 2, startx, starty, endx, endy);
        drawAnnotation(canvas, 7, 7, startx, starty, endx, endy);

        for (int i = 0; i < 9; i += 2) {
            drawAnnotation(canvas, i, 3, startx, starty, endx, endy);
        }

        for (int i = 0; i < 9; i += 2) {
            drawAnnotation(canvas, i, 6, startx, starty, endx, endy);
        }
    }

    private void drawAnnotation(Canvas canvas, int x, int y, int startx, int starty, int endx, int endy) {
        int width = endx - startx;
        int height = endy - starty;
        int centerx = (int) (x * width / 8.0) + startx;
        int centery = (int) (y * height / 9.0) + starty;
        int length = (int) (width / 8.0 * 0.4);
        int padding = ANNOTATION_PADDING;

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

    private void drawBoardLines(Canvas canvas, int startx, int starty, int endx, int endy) {
        int width = endx - startx;
        int height = endy - starty;

        for (int i = 0; i < 10; i++) {
            drawXLine(canvas, (int) (i * height / 9.0), 0, width, startx, starty);
        }

        drawYLine(canvas, 0, 0, height, startx, starty);
        drawYLine(canvas, width, 0, height, startx, starty);

        for (int i = 1; i < 8; i++) {
            drawYLine(canvas, (int) (i * width / 8.0), 0, (int) (height / 9.0 * 4), startx, starty);
        }

        for (int i = 1; i < 8; i++) {
            drawYLine(canvas, (int) (i * width / 8.0), (int) (height / 9.0 * 5), height, startx, starty);
        }

        // 斜线
        drawLine(canvas, (int) (3 * width / 8.0), 0, (int) (5 * width / 8.0), (int) (height / 9.0 * 2), startx, starty);
        drawLine(canvas, (int) (3 * width / 8.0), (int) (height / 9.0 * 2), (int) (5 * width / 8.0), 0, startx, starty);

        drawLine(canvas, (int) (3 * width / 8.0), height, (int) (5 * width / 8.0), (int) (height / 9.0 * 7), startx, starty);
        drawLine(canvas, (int) (3 * width / 8.0), (int) (height / 9.0 * 7), (int) (5 * width / 8.0), height, startx, starty);
    }

    private void drawXLine(Canvas canvas, int y, int formX, int toX, int tX, int tY) {
        drawLine(canvas, formX, y, toX, y, tX, tY);
    }

    private void drawYLine(Canvas canvas, int x, int formY, int toY, int tX, int tY) {
        drawLine(canvas, x, formY, x, toY, tX, tY);
    }

    private void drawLine(Canvas canvas, int fromx, int formY, int tox, int toY, int tX, int tY) {
        canvas.save();
        canvas.translate(tX, tY);
        canvas.drawLine(fromx, formY, tox, toY, mLinePaint);
        canvas.restore();
    }
}
