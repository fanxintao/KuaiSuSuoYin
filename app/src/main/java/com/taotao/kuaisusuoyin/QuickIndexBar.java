package com.taotao.kuaisusuoyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by fanxintao on 2017/8/16.
 */

public class QuickIndexBar extends View {

    private Paint paint;

    private static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z", "#"};
    private float cellHeight;//文本框的高度
    private float cellWidht;  //文本框的宽度
    private float textHeight;   //文本的高度
    private float textWidth;      //文本的宽度

    private Rect bounds;


    private OnLetterLeftListen mleftListen;


    public interface OnLetterLeftListen {
        void onLetterClick(String letter);
    }

    public void setOnLetterLeftListen(OnLetterLeftListen leftListen) {
        this.mleftListen = leftListen;
    }


    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(24);
        paint.setTypeface(Typeface.DEFAULT_BOLD);

        bounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        for (int i = 0; i < letters.length; i++) {
            String text = letters[i];
            textWidth = paint.measureText(text);
            paint.getTextBounds(text, 0, text.length(), bounds);
            textHeight = bounds.height();
            float x = cellWidht * 0.5f - textWidth * 0.5f;
            float y = cellHeight * 0.5f + textHeight * 0.5f + i * cellHeight;
            canvas.drawText(letters[i], x, y, paint);

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cellWidht = getMeasuredWidth();
        int height = getMeasuredHeight();
        cellHeight = height * 1.0f / letters.length;
    }


    int downIndex=-1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index;
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                index= (int) (event.getY()/cellHeight);
                if (index>0&&index<letters.length){
                    if (mleftListen!=null){
                        mleftListen.onLetterClick(letters[index]);
                    }
                    downIndex=index;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                index= (int) (event.getY()/cellHeight);
                if (index>0&&index<letters.length&&index!=downIndex){
                    if (mleftListen!=null){
                        mleftListen.onLetterClick(letters[index]);
                    }
                    downIndex=index;
                }

                break;
            case MotionEvent.ACTION_UP:
                downIndex=-1;

                break;

        }



        return true;
    }
}
