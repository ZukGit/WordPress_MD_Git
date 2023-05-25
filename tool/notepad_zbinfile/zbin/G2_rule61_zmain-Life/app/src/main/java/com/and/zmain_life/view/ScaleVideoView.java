package com.and.zmain_life.view;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;

import com.and.zmain_life.bean.DataHolder;
import com.and.zmain_life.utils.ZoomImageViewUtil;

public class ScaleVideoView extends VideoView implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private static final String TAG = "ScaleVideoView";
    private static final float MAX_SCALE = 4;
    private static final float MIN_SCALE = 1;

    private GestureDetector mGestureDetector;
    private boolean isHaveScrolled;
    private boolean isHaveScale;
    private float oldTwoPointerDistance;
    private int video_raw_high;
    private int video_raw_width;
    public int mScaleVideo_Num = 1;
    public int mScaleVideo_DoubleClick_Num = 0;
    public ScaleVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(getContext(), this);
        mScaleVideo_Num = 1;
        mScaleVideo_DoubleClick_Num = 0;
    }


    @Override
    public void start() {
        super.start();

        if(DataHolder.System_SDK_Version >= 29) {  //只有 SDK_29  安卓Q 安卓10.0 才能执行

            startScaleAnimation(getScaleX(), 1, null);
        }
    }

    public ScaleVideoView(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(getContext(), this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        super.onTouchEvent(event);
        if (event.getPointerCount() == 1) {
            Log.d(getClass().toString(), "onTouch:  event.getPointerCount() == 1 ");
            return mGestureDetector.onTouchEvent(event);
        } else if (event.getPointerCount() == 2) {
            Log.d(getClass().toString(), "onTouch:  event.getPointerCount() == 2 ");

            return onScaleEvent(event);
        }
        Log.d(getClass().toString(), "onTouch:  event.getPointerCount() == "+ event.getPointerCount());

        return false;
    }

  static  int scaleNum = 1 ;
    private boolean onScaleEvent(MotionEvent event) {
        Log.d(TAG, "onScaleEvent:Happen ");
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN) {
            oldTwoPointerDistance = spacing(event);//两点按下时的距离
            Log.d(TAG, "onScaleEvent: ACTION_POINTER_DOWN");
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldTwoPointerDistance = spacing(event);//两点按下时的距离
                Log.d(TAG, "onScaleEvent: ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                //只有2个手指的时候才有放大缩小的操作
                float currentDist = spacing(event);
                float scale = currentDist / oldTwoPointerDistance;
              int measureWidth =   this.getMeasuredWidth();
                int measureHigh =      this.getMeasuredHeight();
                Log.d(TAG, "onScaleEvent: currentDist = " + currentDist + " oldTwoPointerDistance = " + oldTwoPointerDistance+" measureWidth="+measureWidth+"  measureHigh="+measureHigh);

                if(currentDist - oldTwoPointerDistance > 50){
                    this.setScale(scaleNum);
                    scaleNum++;
                }
                Log.d(TAG, "onScaleEvent:CCCC ");

//                setScale(scale);
                return true;
        }
        Log.d(TAG, "onScaleEvent:DDD ");
        return false;
    }

    private float spacing(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        } else {
            return 0;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int width = getDefaultSize(0, widthMeasureSpec);
//        int height = getDefaultSize(0, heightMeasureSpec);
//        setMeasuredDimension(width, height);

        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

    }

    public void setFixedVideoSize(int width, int height) {
        getHolder().setFixedSize(width, height);
    }

    /**
     * 触摸使用的移动事件
     * @param lessX
     * @param lessY
     */
    private void setSelfPivot(float lessX, float lessY) {
        float setPivotX = 0;
        float setPivotY = 0;
        setPivotX = getPivotX() + lessX;
        setPivotY = getPivotY() + lessY;
        Log.e("lawwingLog", "setPivotX:" + setPivotX + "  setPivotY:" + setPivotY
                + "  getWidth:" + getWidth() + "  getHeight:" + getHeight());
        if (setPivotX < 0 && setPivotY < 0) {
            setPivotX = 0;
            setPivotY = 0;
        } else if (setPivotX > 0 && setPivotY < 0) {
            setPivotY = 0;
            if (setPivotX > getWidth()) {
                setPivotX = getWidth();
            }
        } else if (setPivotX < 0 && setPivotY > 0) {
            setPivotX = 0;
            if (setPivotY > getHeight()) {
                setPivotY = getHeight();
            }
        } else {
            if (setPivotX > getWidth()) {
                setPivotX = getWidth();
            }
            if (setPivotY > getHeight()) {
                setPivotY = getHeight();
            }
        }
        setPivot(setPivotX, setPivotY);
    }

    /**
     * 平移画面，当画面的宽或高大于屏幕宽高时，调用此方法进行平移
     * @param x
     * @param y
     */
    public void setPivot(float x, float y) {
        setPivotX(x);
        setPivotY(y);
    }

    /**
     * 设置放大缩小
     * @param scale
     */
    public void setScale(float scale) {
        Log.d(TAG, "setScale: scale = " + scale + " getScaleX() * scale = " + getScaleX() * scale);
        float currentScaleX = getScaleX() * scale;
        float currentScaleY = getScaleY() * scale;
        if (currentScaleX > MAX_SCALE || currentScaleY > MAX_SCALE) {
            currentScaleX = MAX_SCALE;
            currentScaleY = MAX_SCALE;
        } else if (currentScaleX < MIN_SCALE || currentScaleY < MIN_SCALE) {
            currentScaleX = MIN_SCALE;
            currentScaleY = MIN_SCALE;
        }
        setScaleX(currentScaleX);
        setScaleY(currentScaleY);
    }



    @Override
    public void setVideoPath(String path) {
        super.setVideoPath(path);
        mScaleVideo_Num = 1;
        mScaleVideo_DoubleClick_Num = 0;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        isHaveScrolled = false;
        isHaveScale = false;
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        isHaveScrolled = true;
        setSelfPivot(e1.getX() - e2.getX(), e1.getY() - e2.getY());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(TAG, "onDoubleTap: ");


        if(DataHolder.System_SDK_Version >= 29){  //只有 SDK_29  安卓Q 安卓10.0 才能执行
            Log.i(TAG,"onDoubleTap 当前 SDK_Version="+DataHolder.System_SDK_Version+"  达到 10.0 版本的 29  可以使用 VideoScale功能");
            mScaleVideo_DoubleClick_Num++;   // 双击的次数

            if(mScaleVideo_DoubleClick_Num%2 == 0 ){
                startScaleAnimation(getScaleX(), 1,e);
                Log.d(TAG, "XX onDoubleTap: getScaleX() = "+ mScaleVideo_Num);

            }else{

                if (mScaleVideo_Num > 5) {
                    mScaleVideo_Num = 2 ;
                    Log.d(TAG, "AA onDoubleTap: getScaleX() = "+ mScaleVideo_Num);

                    startScaleAnimation(getScaleX(), mScaleVideo_Num,e);
                } else {
                    mScaleVideo_Num++;
                    Log.d(TAG, "BB onDoubleTap: getScaleX() = "+ mScaleVideo_Num);

                    startScaleAnimation(getScaleX(), mScaleVideo_Num,e);

                }



            }




        }else{
            Log.i(TAG,"onDoubleTap 当前 SDK_Version="+DataHolder.System_SDK_Version+"  不够10.0 版本的 29  无法使用 VideoScale功能");
        }


        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void startScaleAnimation(float start, float end, MotionEvent e) {
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(200);
/*        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();


                Log.d("TAG", "startScaleAnimation  currentValueX=" + currentValue+"  setScaleY="+currentValue);

                setScaleX(currentValue);
                setScaleY(currentValue);
                Log.d("TAG", "cuurent value is " + currentValue);
            }
        });
        anim.start();*/

        int width = this.getWidth();
        int high = this.getHeight();
        double scale_width = width* getScaleX();
        double scale_high = high* getScaleY();
        float x_point = 0;
        float y_point =0;
        if(e ==null){
             x_point = 0;
             y_point =0;
        }else{
             x_point = e.getX();
             y_point = e.getY();
        }

        double  cur_center_x =  scale_width - width/2;
        double  cur_center_y =  scale_high - high/2;

        double x_scale_point = x_point* getScaleX();
        double y_scale_point = y_point* getScaleY();

        double x_move  = x_scale_point - cur_center_x;
        double  y_move  =y_scale_point - cur_center_y;
        if(x_move > 0){
            x_move = 0;
        }

        if(y_move > 0){
            y_move = 0;
        }
if(getLayoutParams() == null){
    return ;
}
        int layoutWidth =   getLayoutParams().width;
        int layoutHeight =   getLayoutParams().height;

     ZoomImageViewUtil.Position_Rect   targetRect = ZoomImageViewUtil.calculTargetRectPosition(x_point,y_point,width,high,width,high,layoutWidth,layoutHeight,end);


        Matrix matrix =  getMatrix();

        matrix.postScale(targetRect.point_x_scale,targetRect.point_y_scale,0,0);
//        matrix.postTranslate((targetRect.full_draw_w * -1 ) + device_width,(targetRect.full_draw_h * -1 )+ device_height);   // 以它为左顶点
        matrix.postTranslate(targetRect.target_pre_x_point,targetRect.target_pre_y_point );   // 以它为左顶点


        setAnimationMatrix(matrix);

//        matrix.postTranslate(targetRect.target_pre_x_point,targetRect.target_pre_y_point );


        Log.d(TAG, "ZZAA x_move["+x_move+"] " + "y_move["+y_move+"] "  + "width["+width+"] "+ "high["+high+"] " + "getScaleX()["+getScaleX()+"] "+ "getScaleY()["+getScaleY()+"] ");

        Log.d(TAG, "ZZAA scale_width["+scale_width+"] " + "scale_high["+scale_high+"] "  + "x_point["+x_point+"] "+ "y_point["+y_point+"] " + "x_scale_point["+x_scale_point+"] "+ "y_scale_point["+y_scale_point+"] ");

//        setTranslationX((float)x_move);  // 移动的 X 坐标  Y 坐标?
//        setTranslationY((float)y_move);  //


    }
}

