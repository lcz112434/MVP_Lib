package com.lcz.lczed_mvpbase.widget;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lcz.lczed_mvpbase.R;

import java.util.ArrayList;

/**
 * 仿View加载页面
 */
public class RotateDotView extends View implements Runnable {
    /**
     * 总旋转角度
     */
    private static final int TOTAL_ROTATION_ANGLE = 360;
    /**
     * 间隔时间
     */
    private static final int INTERVAL_TIME = 65;
    /**
     * View默认最小宽度
     */
    private static final int DEFAULT_MIN_WIDTH = 70;

    /**
     * 控件宽、高
     */
    private int mViewWidth;
    private int mViewHeight;

    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 外接圆的半径
     */
    private float mCircleRadius;
    /**
     * 起始点的颜色
     */
    private int mDotColor;
    /**
     * 一共多少个点
     */
    private int mDotCount = 8;
    /**
     * 平均角度
     */
    int mAngle = TOTAL_ROTATION_ANGLE / mDotCount;
    /**
     * 每个点的数据
     */
    private ArrayList<Dot> mDots;
    /**
     * 当前旋转到的角度
     */
    private int mCurrentAngle = 0;

    public RotateDotView(Context context) {
        super(context);
        init();
    }

    public RotateDotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateDotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDotColor = getContext().getResources().getColor(R.color.none_color);
        mPaint = new Paint();
        mPaint.setColor(mDotColor);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(dip2px(getContext(), 3f));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mCircleRadius = (Math.min(mViewHeight, mViewWidth) / 2f) * 0.8f;
        mDots = generateDot();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将坐标系原点移动到画布正中心
        canvas.translate(mViewWidth / 2, mViewHeight / 2);
        canvas.rotate(mCurrentAngle);
        int radius = dip2px(getContext(), 2.6f);
        for (Dot dot : mDots) {
            mPaint.setColor(dot.color);
            canvas.drawCircle(dot.x, dot.y, radius, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(handleMeasure(widthMeasureSpec), handleMeasure(heightMeasureSpec));
    }

    /**
     * 处理MeasureSpec
     */
    private int handleMeasure(int measureSpec) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            //处理wrap_content的情况
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(this, INTERVAL_TIME);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
    }

    @Override
    public void run() {
        if (mCurrentAngle >= TOTAL_ROTATION_ANGLE) {
            mCurrentAngle = mCurrentAngle - TOTAL_ROTATION_ANGLE;
        } else {
            //每次叠加一个圆点的角度，就不会觉得在圆圈转动，而是点在切换
            mCurrentAngle += mAngle;
        }
        invalidate();
        postDelayed(this, INTERVAL_TIME);
    }

    /**
     * 生成点
     */
    private ArrayList<Dot> generateDot() {
        //创建颜色估值器
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        ArrayList<Dot> points = new ArrayList<>();
        for (int i = 0; i < mDotCount; i++) {
            float currentAngle = i * mAngle;
            //三角函数，计算坐标，注意这里Math的三角函数方法，传入的是弧长，需要乘以Math.PI来将角度换算为弧长，再进行计算
            float x = (float) (mCircleRadius * Math.cos((currentAngle / 180) * Math.PI));
            float y = (float) (mCircleRadius * Math.sin((currentAngle / 180) * Math.PI));
            //估算颜色，计算每个点的颜色
            float fraction = currentAngle / TOTAL_ROTATION_ANGLE;
            int startColor = Color.argb(225, 255, 255, 255);
            int color = (int) argbEvaluator.evaluate(fraction, startColor, mDotColor);
            points.add(new Dot(x, y, color));
        }
        return points;
    }

    private static class Dot {
        /**
         * x坐标
         */
        float x;
        /**
         * y坐标
         */
        float y;
        /**
         * 颜色
         */
        int color;

        Dot(float x, float y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}