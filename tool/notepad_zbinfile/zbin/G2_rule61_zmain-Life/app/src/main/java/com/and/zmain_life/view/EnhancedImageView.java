
package com.and.zmain_life.view;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * OnGlobalLayoutListener 获取控件宽高
 */
@SuppressLint("AppCompatCustomView")
public class EnhancedImageView extends ImageView
        implements ViewTreeObserver.OnGlobalLayoutListener
        , ScaleGestureDetector.OnScaleGestureListener
        , View.OnTouchListener {
    private static final String TAG = "EnhancedImageView";
    private static final boolean DEBUG = true;

    //初始化标志
    private boolean mInitOnce = false;
    //初始化缩放值
    private float mInitScale;
    private float mMinScale;
    //双击放大的值
    private float mMidScale;
    //放大最大值
    private float mMaxScale;
    //负责图片的平移缩放
    private Matrix mScaleMatrix;
    //为缩放而生的类，捕获缩放比例
    private ScaleGestureDetector mScaleGestureDetector;

    /****************************自由移动***************/
    //记录手指数量
    private int mLastPointerCount;
    //记录上次手指触摸位置
    private float mLastX;
    private float mLastY;
    //触摸移动距离
    private int mTouchSlop;
    //是否可以拖动
    private boolean isCanDrag;
    //边界检查时用
    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;

    /*****************双击放大********************************/
    public GestureDetector mGestureDetector;

    public EnhancedImageView(Context context) {
        this(context, null);
    }

    public EnhancedImageView(Context context,  AttributeSet attrs) {
        this(context, attrs, 0);
    }

    static int mDoubleTap_Count = 0 ;
    public void ScaleTypeChange(){

        if(mDoubleTap_Count%2 == 0){
            this.setScaleType(ScaleType.FIT_XY);
            log("当前scale mDoubleTap_Count = "+mDoubleTap_Count+"  ScaleType= ScaleType.FIT_XY");
        }else{
            this.setScaleType(ScaleType.MATRIX);
            log("当前scale mDoubleTap_Count = "+mDoubleTap_Count+"  ScaleType= ScaleType.MATRIX");
        }



    }


    public EnhancedImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScaleMatrix = new Matrix();
        //覆盖用户设置
//        super.setScaleType(ScaleType.MATRIX);
        super.setScaleType(ScaleType.FIT_XY);
        this.setLongClickable(true);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
        //获取系统默认缩放

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {

                float x = e.getX();
                float y = e.getY();
                log("当前scale " + getScale() + "mid: " + mMidScale);
                if (getScale() < mMidScale) {
                    mScaleMatrix.postScale(mMidScale/getScale(), mMidScale/getScale(),getWidth()/2,getHeight()/2);
                    setImageMatrix(mScaleMatrix);
                } else {
                    log("恢复初始化");
                    //计算将图片移动至中间距离
                    int dx = getWidth() / 2 - getDrawable().getIntrinsicWidth() / 2;
                    int dy = getHeight() / 2 - getDrawable().getIntrinsicHeight() / 2;
                    mScaleMatrix.reset();
                    mScaleMatrix.postTranslate(dx, dy);
                    mScaleMatrix.postScale(mInitScale, mInitScale,getWidth()/2,getHeight()/2);
                    setImageMatrix(mScaleMatrix);
                }

                return false;
            }
        });
    }

  void  ScaleTypeChangeOperation(){


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }

    /**
     * 全局布局完成后会调用
     */
    @Override
    public void onGlobalLayout() {
//        if (!mInitOnce) {
            //得到控件的宽和高
            int width = getWidth();
            int height = getHeight();

            //拿到图片的宽高
            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();

            float scale = 1.0f;
            //若图片宽度大于控件宽度 高度小于空间高度
            if (drawableWidth > width && drawableHeight < height) {
                log("若图片宽度大于控件宽度 高度小于空间高度");
                scale = width * 1.0f / drawableWidth;
                //图片的高度大于控件高度 宽度小于控件宽度
            } else if (drawableHeight > height && drawableWidth < width) {
                log("图片的高度大于控件高度 宽度小于控件宽度");
                scale = height * 1.0f / drawableHeight;
            } else if (drawableWidth > width && drawableHeight > height) {
                log("都大于");
                scale = Math.min(width * 1.0f / drawableWidth, height * 1.0f / drawableHeight);
            } else if (drawableWidth < width && drawableHeight < height) {
                log("都小于");
                scale = Math.min(width * 1.0f / drawableWidth, height * 1.0f / drawableHeight);
            }
            mInitScale = scale;
            mMinScale = scale;
            mMidScale = scale * 2;
            mMaxScale = scale * 5;

            //计算将图片移动至中间距离
            int dx = getWidth() / 2 - drawableWidth / 2;
            int dy = getHeight() / 2 - drawableHeight / 2;

            mScaleMatrix.postTranslate(dx, dy);
            //xy方向不变形，必须传一样的
            mScaleMatrix.postScale(mInitScale, mInitScale, width / 2, height / 2);
            setImageMatrix(mScaleMatrix);

//            mInitOnce = true;
//        }
    }

    /**
     * 获取当前图片的缩放值
     *
     * @return
     */
    public float getScale() {
        float[] values = new float[9];
        mScaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }


    /**
     * 为缩放而生的类：ScaleGestureDetector
     *
     * @param detector
     * @return
     */
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScale();
        float scaleFactor = detector.getScaleFactor();
        log("多点触控时候的缩放值: " + scaleFactor);
        if (getDrawable() == null) {
            return true;
        }

        //缩放范围的控制, 放大时需要小于最大，缩小时需要大于最小
        if ((scale < mMaxScale && scaleFactor > 1.0f) || (scale > mMinScale && scaleFactor < 1.0f)) {
            if (scale * scaleFactor < mMinScale) {
                scaleFactor = mMinScale / scale;
            }

            if (scale * scaleFactor > mMaxScale) {
                scaleFactor = mMaxScale / scale;
            }
            log("设置最终缩放值 " + scaleFactor);
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());

            checkBorderForScale();

            setImageMatrix(mScaleMatrix);
        }

        return true;
    }

    /**
     * 获得图片放大缩小以后的宽和高
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();

        Drawable drawable = getDrawable();

        if (drawable != null) {
            rectF.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            //用matrix进行map一下
            matrix.mapRect(rectF);
        }

        return rectF;
    }

    /**
     * 缩放时候进行边界控制等
     */
    private void checkBorderForScale() {
        RectF rect = getMatrixRectF();

        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();

        if (rect.width() >= width) {
            if (rect.left > 0) { //和屏幕左边有空隙
                deltaX = -rect.left; //左边移动
            }
            // 和屏幕as
            if (rect.right < width) {
                deltaX = width - rect.right;
            }
        }

        if (rect.height() >= height) {
            if (rect.top > 0) {
                deltaY = -rect.top;
            }

            if (rect.bottom < height) {
                deltaY = height - rect.bottom;
            }
        }

        //如果宽度或者高度小于控件的宽和高 居中处理
        if (rect.width() < width) {
            deltaX = getWidth() / 2 - rect.right + rect.width() / 2;
        }

        if (rect.height() < height) {
            deltaY = getHeight() / 2 - rect.bottom + rect.height() / 2;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 当移动时,进行边界检查.
     */
    private void checkBorderForTraslate() {
        RectF rectF = getMatrixRectF();

        float deltaX = 0;
        float deltaY = 0;
        int width = getWidth();
        int height = getHeight();

        //上边有空白,往上移动
        if (rectF.top > 0 && isCheckTopAndBottom) {
            deltaY = -rectF.top;
        }

        if (rectF.bottom < height && isCheckTopAndBottom) {
            deltaY = height - rectF.bottom;
        }
        //左边和空白往左边移动
        if (rectF.left > 0 && isCheckLeftAndRight) {
            deltaX = -rectF.left;
        }

        if (rectF.right < width && isCheckLeftAndRight) {
            deltaX = width - rectF.right;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    private void log(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    /**
     * 为了让mScaleGestureDetector拿到手势
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        mScaleGestureDetector.onTouchEvent(event);
        /**
         * 计算多指触控中心点
         */
        float currentX = 0;
        float currentY = 0;
        int pointCount = event.getPointerCount();

        for (int i = 0; i < pointCount; i++) {
            currentX += event.getX(i);
            currentY += event.getY(i);
        }
        currentX /= pointCount;
        currentY /= pointCount;

        if (mLastPointerCount != pointCount) {
            isCanDrag = false;
            mLastX = currentX;
            mLastY = currentY;
        }

        mLastPointerCount = pointCount;
        RectF rectF = getMatrixRectF();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //请求不被拦截
                if(rectF.width() > getWidth() || rectF.height() > getHeight()){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if(rectF.width() > (getWidth() + 0.01) || rectF.height() > (getHeight() + 0.01)){
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                float dx = currentX - mLastX;
                float dy = currentY - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }

                if (isCanDrag) {
                    RectF rectf = getMatrixRectF();
                    if (getDrawable() != null) {
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        //如果宽度小于控件宽度,不允许横向移动
                        if (rectf.width() < getWidth()) {
                            dx = 0;
                            isCheckLeftAndRight = false;
                        }
                        //若高度小于控件高度,不允许纵向移动
                        if (rectf.height() < getHeight()) {
                            dy = 0;
                            isCheckTopAndBottom = false;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        checkBorderForTraslate();
                        setImageMatrix(mScaleMatrix);
                    }
                }
                mLastX = currentX;
                mLastY = currentY;
                break;
            //结束时,将手指数量置0
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mLastPointerCount = 0;
                break;
            default:
                break;

        }


        return true;
    }


    /**
     * 判断当前移动距离是否大于系统默认最小移动距离
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

    private void resetToInit(){
        //得到控件的宽和高
        int width = getWidth();
        int height = getHeight();

        //拿到图片的宽高
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();

        float scale = 1.0f;
        //若图片宽度大于控件宽度 高度小于空间高度
        if (drawableWidth > width && drawableHeight < height) {
            log("若图片宽度大于控件宽度 高度小于空间高度");
            scale = width * 1.0f / drawableWidth;
            //图片的高度大于控件高度 宽度小于控件宽度
        } else if (drawableHeight > height && drawableWidth < width) {
            log("图片的高度大于控件高度 宽度小于控件宽度");
            scale = height * 1.0f / drawableHeight;
        } else if (drawableWidth > width && drawableHeight > height) {
            log("都大于");
            scale = Math.min(width * 1.0f / drawableWidth, height * 1.0f / drawableHeight);
        } else if (drawableWidth < width && drawableHeight < height) {
            log("都小于");
            scale = Math.min(width * 1.0f / drawableWidth, height * 1.0f / drawableHeight);
        }
        mInitScale = scale;
        mMidScale = scale * 2;
        mMaxScale = scale * 5;

        //计算将图片移动至中间距离
        int dx = getWidth() / 2 - drawableWidth / 2;
        int dy = getHeight() / 2 - drawableHeight / 2;

        mScaleMatrix.postTranslate(dx, dy);

        ValueAnimator valueAnimator = new ValueAnimator();
    }

}