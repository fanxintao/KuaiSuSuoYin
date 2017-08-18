package com.taotao.kuaisusuoyin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fanxintao on 2017/8/17.
 */

public class IndexBar extends View {

    /*绘制的列表导航字母*/
    private String letters[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    /*字母画笔*/
    private Paint lettersPaint;
    /*字母背景画笔*/
    private Paint bgPaint;

    /*每一个字母框的宽度*/
    private int itemWidth;
    /*每一个字母框的高度*/
    private int itemHeight;
    /*每一个字母的宽度*/
    private int letterWidth;
    /*每一个字母的高度*/
    private int letterHeight;

    /*永远获取文字宽高*/
    private Rect bounds;

    /*手指按下字母的索引*/
    private int touchIndex=0;

    /*手指按下字母改变的接口*/
    private OnLetterChangeListener changeListener;

    public IndexBar(Context context) {
        this(context,null);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lettersPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        lettersPaint.setTextSize(20);
        lettersPaint.setTypeface(Typeface.DEFAULT_BOLD);
        bgPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(Color.GREEN);
        bounds=new Rect();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth=getMeasuredWidth();
        //使得边距好看一点
        int height=getMeasuredHeight()-10;
        itemHeight=height/27;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<letters.length;i++){
            //判断是不是我们按下当前字母
            if (touchIndex==i){
//                canvas.drawCircle(itemWidth/2,itemHeight/2+i*itemHeight,23,bgPaint);
//                lettersPaint.setColor(Color.WHITE);
            }else {
                lettersPaint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            lettersPaint.getTextBounds(letters[i],0,1,bounds);
            letterWidth=bounds.width();
            letterHeight=bounds.height();

            //绘制字母
            float letterX=itemWidth/2-letterWidth/2;
            float letterY=itemHeight/2+letterHeight/2+i*itemHeight;
            canvas.drawText(letters[i],letterX,letterY,lettersPaint);
        }
    }

    /**
     * 手指触摸按下时改变字母背景颜色
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                float y=event.getY();
                //获得我们按下的是哪一个字母的索引
                int index= (int) (y/itemHeight);
                if (index!=touchIndex){
                    touchIndex=index;
                    //防止数组越界
                    if (changeListener!=null&&0<=touchIndex&&touchIndex<=letters.length-1){
                        //回调按下字母
                        changeListener.letterChange(letters[index]);
                    }
                }
                invalidate();

                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }

    public interface OnLetterChangeListener{
        void letterChange(String letter);
    }

    public void setOnLetterChangeListener(OnLetterChangeListener changeListener){
        this.changeListener=changeListener;
    }


    /*设置当前按下的是哪个字母*/
    public void setTouchIndex(String word){
        for (int i=0;i<letters.length;i++){
            if (letters[i].equals(word)){
                touchIndex=i;
                invalidate();
                return;
            }
        }
    }
}
