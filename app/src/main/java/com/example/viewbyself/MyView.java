package com.example.viewbyself;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View {
    //Content : activity or server(server for後端)
    //AttributeSet : 屬性設定
    private LinkedList<LinkedList<HashMap<String, Float>>> lines;
    private Paint paint;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GREEN);
        lines = new LinkedList<>();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //onDraw()是系統呼叫的，canvas是系統傳的
        super.onDraw(canvas);
        //Paint : 畫筆
        //Paint paint = new Paint();
        //paint.setColor(Color.RED);
        //線的粗度
        //paint.setStrokeWidth(40);
        //cx,cy : 圓心座標
        //canvas.drawCircle(0,0,40, paint);
        //cx,cy : 線起始座標
        //canvas.drawLine(0,0,400,400, paint);
        for(LinkedList<HashMap<String, Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
            }
        }
    }

    // 摸一下會觸發
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //event.getX();//座標X
        //event.getY();//座標Y
        //super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Log.v("brad", "down");
            setFirstPoint(event);
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            Log.v("brad", "up");
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            Log.v("brad", "move");
            setMovePoint(event);
        }
        return true;
    }

    private void setFirstPoint(MotionEvent event) {
        LinkedList<HashMap<String, Float>> line = new LinkedList<>();
        Float ex = event.getX() , ey = event.getY();
        HashMap<String, Float> point = new HashMap<>();
        point.put("x" , ex);
        point.put("y" , ey);
        line.add(point);
        lines.add(line);
    }

    private void setMovePoint(MotionEvent event) {
        Float ex = event.getX() , ey = event.getY();
        HashMap<String, Float> point = new HashMap<>();
        point.put("x" , ex);
        point.put("y" , ey);
        lines.getLast().add(point);

        //重新呼叫ondraw() -->類似java的repaint
        invalidate();
    }

    /**
     * 筆記 : onclickListener() ->動作要有上下
     * 當super.onTouchEvent(event) + return true -> 才會觸發onclickListener()，因為才會有上下動作
     * onTouchEvent() :
     *    1. super.onTouchEvent(event); ->結果回傳false
     *    2. 如果想要摸下去持續偵測，則回傳true
     *    3. 先onTouchEvent()再onclickListener()
     */
}
