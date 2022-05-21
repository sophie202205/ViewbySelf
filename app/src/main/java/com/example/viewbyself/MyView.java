package com.example.viewbyself;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    //Content : activity or server(server for後端)
    //AttributeSet : 屬性設定


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //onDraw()是系統呼叫的，canvas是系統傳的
        super.onDraw(canvas);
        //Paint : 畫筆
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //線的粗度
        paint.setStrokeWidth(40);
        //cx,cy : 圓心座標
        canvas.drawCircle(0,0,40, paint);
        //cx,cy : 線起始座標
        canvas.drawLine(0,0,400,400, paint);
    }
}
