package com.hencoder.hencoderpracticedraw7.practice.practice01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Printer;
import android.view.View;

import com.hencoder.hencoderpracticedraw7.R;


public class Practice01ArgbEvaluatorView extends View {

    //Y轴方向旋转角度
    private float degreeY;
    //不变的那一半，Y轴方向旋转角度
    private float fixDegreeY;
    //Z轴方向（平面内）旋转的角度
    private float degreeZ;

    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;

    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Camera camera;
    private Matrix matrix;

    public Practice01ArgbEvaluatorView(Context context) {
        super(context);
    }

    public Practice01ArgbEvaluatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman1);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setTextSize(30);

        paint3.setColor(Color.RED);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(20);

        paint4.setColor(Color.BLUE);
        paint4.setStrokeWidth(20);

        camera = new Camera();
        matrix = new Matrix();
    }

    public Practice01ArgbEvaluatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float x = centerX - bitmapWidth / 2;
        float y = centerY - bitmapHeight / 2;


//        matrix.reset();
//        canvas.save();
//        camera.save();
//        camera.rotateY(degreeY);
//        camera.getMatrix(matrix);
//        camera.restore();
//        matrix.postRotate(-degreeZ);
//        matrix.postTranslate(centerX, centerY);
//        matrix.preRotate(degreeZ);
//        matrix.preTranslate(-centerX, -centerY);
//        canvas.concat(matrix);
//        canvas.drawBitmap(bitmap, x, y, paint);
//        canvas.restore();


        //画变换的一半
        //先旋转，再裁切，再使用camera执行3D动效,**然后保存camera效果**,最后再旋转回来
//        degreeZ = 15;
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degreeZ);
        camera.rotateY(degreeY);
        camera.applyToCanvas(canvas);
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(0, -centerY, centerX, centerY);
        canvas.drawRect(0, -centerY, centerX, centerY, paint2);
        canvas.drawPoint(0, -centerY, paint3);
        canvas.drawPoint(centerX, centerY, paint4);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();

        //画不变换的另一半
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degreeZ);
        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        canvas.drawRect(-centerX + 50, -centerY + 50, 0 - 50, centerY - 50, paint3);
        canvas.drawPoint(0, -centerY, paint4);
        //此时的canvas的坐标系已经旋转，所以这里是rotateY
        camera.rotateY(fixDegreeY);
        camera.applyToCanvas(canvas);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap2, x, y, paint);
        canvas.restore();
    }

    /**
     * 启动动画之前调用，把参数reset到初始状态
     */
    public void reset() {
        degreeY = 0;
        fixDegreeY = 0;
        degreeZ = 0;
        invalidate();
    }

    public float getFixDegreeY() {
        return fixDegreeY;
    }

    public void setFixDegreeY(float fixDegreeY) {
        this.fixDegreeY = fixDegreeY;
        invalidate();
    }

    public float getDegreeY() {
        return degreeY;
    }

    public void setDegreeY(float degreeY) {
        this.degreeY = degreeY;
        invalidate();
    }

    public float getDegreeZ() {
        return degreeZ;
    }

    public void setDegreeZ(float degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
    }

}
