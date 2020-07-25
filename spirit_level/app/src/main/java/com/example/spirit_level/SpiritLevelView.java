package com.example.spirit_level;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SpiritLevelView extends View {
    private static final int BUBBLE_SIZE = 50;
    private final Paint paint;
    private Point center;
    private PointF bubble;
    private int height, width;
    private int square_side = 700;

    public SpiritLevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bubble = new PointF();
        center = new Point();
        paint = new Paint();

        setBackgroundColor(Color.GRAY);
        paint.setColor(Color.CYAN);
    }

    public void setBubble(float x, float y) {
        if (center.x + x / 9.18f * width < center.x - square_side / 2.0) {
            bubble.x = (float) (center.x - square_side / 2.0);
        } else if (center.x + x / 9.18f * width > center.x + square_side / 2.0) {
            bubble.x = (float) (center.x + square_side / 2.0);
        } else {
            bubble.x = center.x + x / 9.18f * width;
        }

        if (center.y + y / 9.18f * height < center.y - square_side / 2.0) {
            bubble.y = (float) (center.y - square_side / 2.0);
        } else if (center.y + y / 9.18f * height > center.y + square_side / 2.0) {
            bubble.y = (float) ((float) center.y + square_side / 2.0);
        } else {
            bubble.y = center.y + y / 9.18f * height;
        }


        Log.i("LevelView_Bubble ****", bubble.toString());
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int old_w, int old_h) {
        super.onSizeChanged(w, h, old_w, old_h);
        center.x = w / 2;
        center.y = h / 2;
        height = h;
        width = w;
        setBubble(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);

        canvas.drawRect(width / 2f - square_side / 2f,
                height / 2f - square_side / 2f,
                width / 2f + square_side / 2f,
                height / 2f + square_side / 2f, paint);

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(0);
        canvas.drawRect(width / 2f - square_side / 2f + 10,
                height / 2f - square_side / 2f + 10,
                width / 2f + square_side / 2f - 10,
                height / 2f + square_side / 2f - 10, paint);

        paint.setColor(Color.CYAN);
        canvas.drawCircle(bubble.x, bubble.y, BUBBLE_SIZE, paint);
        paint.setColor(Color.GRAY);
    }
}
