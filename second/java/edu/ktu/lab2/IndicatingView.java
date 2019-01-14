package edu.ktu.lab2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class IndicatingView extends View {

    public static final int NOTEXECUTED = 0;
    public static final int SUCCESS = 1;
    public static final int FAILED = 2;
    public static final int EXECUTING = 3;

    int state = NOTEXECUTED;

    public IndicatingView(Context context) {super(context);}

    public IndicatingView(Context context, AttributeSet attrs) { super(context, attrs);}

    public IndicatingView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        Paint paint;

        switch (state){
            case SUCCESS:
                paint = new Paint();
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(20f);

                canvas.drawLine(0, 0, width/2, height, paint);
                canvas.drawLine(width/2, height, width, height/2, paint);
                break;
            case FAILED:
                paint = new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0, 0, width, height, paint);
                canvas.drawLine(0, height, width, 0, paint);
                break;
            case EXECUTING:
                paint = new Paint();
                /*paint.setColor(Color.BLACK);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0, 0, width/2, height, paint);
                canvas.drawLine(width/2, height, width, 0, paint);
                canvas.drawLine(width, 10, 0, 10, paint);
                canvas.*/
                float [] points  = new float[8];
                points[0] = 0;
                points[1] = 0;
                points[2] = width/2;
                points[3] = height;
                points[4] = width;
                points[5] = 0;
                points[6] = 0;
                points[7] = 0;

                canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 8, points, 0, null, 0, null, 0, null, 0, 0, paint);
                Path path = new Path();
                path.moveTo(points[0] , points[1]);
                path.lineTo(points[2],points[3]);
                path.lineTo(points[4],points[5]);
                canvas.drawPath(path,paint);
                break;
            default:
                break;
        }
    }
}
