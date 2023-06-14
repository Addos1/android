package com.example.dots.view;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.example.dots.model.Dot;
import com.example.dots.model.Dots;

public class DotView extends View {
    private volatile Dots dots;
    public DotView(Context context, Dots dots) {
        super(context);
        this.dots = dots;
        setFocusableInTouchMode(true);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusableInTouchMode(true);
    }

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFocusableInTouchMode(true);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0, 0, getWidth() - 1, getHeight() - 1, paint);

        if(null == dots) {return;}
        paint.setStyle(Paint.Style.FILL);

        for(Dot dot : dots.getDots()) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(dot.getX(), dot.getY(), dot.getDiameter(), paint);
        }
    }
}

