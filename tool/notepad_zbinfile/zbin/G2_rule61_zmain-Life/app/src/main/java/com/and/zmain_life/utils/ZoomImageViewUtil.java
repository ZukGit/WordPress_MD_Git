package com.and.zmain_life.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;

import utils.ZUtil;

public class ZoomImageViewUtil {
        private static final String TAG = "ZoomImageViewUtil";
        private static final boolean DEBUG = true;

        //初始化标志
        static    private boolean mInitOnce = false;
        //初始化缩放值
        static  private float mInitScale;
       static   private float mMinScale;
        //双击放大的值
        static    private float mMidScale;
        //放大最大值
        static    private float mMaxScale;
        //负责图片的平移缩放
        static  private Matrix mScaleMatrix;
        //为缩放而生的类，捕获缩放比例


    /****************************自由移动***************/
    //记录手指数量
    static  private int mLastPointerCount;
    //记录上次手指触摸位置
    static  private float mLastX;
    static  private float mLastY;
    //触摸移动距离
    static  private int mTouchSlop;
    //是否可以拖动
    static  private boolean isCanDrag;
    //边界检查时用
    static  private boolean isCheckLeftAndRight;
    static  private boolean isCheckTopAndBottom;

    /*****************双击放大********************************/
    static private GestureDetector mGestureDetector;


    static void log(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }


static int point_index = 0 ;
public static class  Position_Rect {
    public    float target_pre_x_point ;
    public  float target_pre_y_point ;

    public float point_x_scale;
    public float point_y_scale;
    public float  center_x;
    public float  center_y;
    public float  after_scale_x;
    public float  after_scale_y;

    public float full_draw_h;
    public float full_draw_w;
    Position_Rect(float x, float y ){
        target_pre_x_point = x;
        target_pre_y_point = y;
    }
}

static ArrayList<Position_Rect> PositionArr;

   public static void initPositionArr(){
       PositionArr = new ArrayList<Position_Rect>();
        int device_height =  ZUtil.getRealScreenHeight();
        int device_width =  ZUtil.getRealScreenWidth();


        float device_middle_x = device_width/2;
        float device_middle_y = device_height/2;

        float device_top_left_x_point1 = device_middle_x/2;
        float device_top_left_y_point1 = device_middle_y/4;

        float device_top_right_x_point2 = device_middle_x/2+device_middle_x;
        float device_top_right_y_point2 =  device_middle_y/4;

        float device_top_left_x_point3 = device_middle_x/2;
        float device_top_left_y_point3 = device_middle_y/4 * 3;


        float device_top_right_x_point4 = device_middle_x/2 + device_middle_x ;
        float device_top_right_y_point4 = device_middle_y/4 * 3;

        float device_middle_x_point5 = device_middle_x;
        float device_middle_y_point5 = device_middle_y;


        float device_bottom_left_x_point6 = device_middle_x/2;
        float device_bottom_left_y_point6 = device_middle_y + device_middle_y/4;

        float device_bottom_right_x_point7 = device_middle_x/2+device_middle_x;
        float device_bottom_right_y_point7 = device_middle_y + device_middle_y/4;


        float device_bottom_left_x_point8 = device_middle_x/2;
        float device_bottom_left_y_point8 = device_middle_y + device_middle_y/4 * 3;


        float device_bottom_right_x_point9 = device_middle_x/2+device_middle_x;
        float device_bottom_right_y_point9 = device_middle_y + device_middle_y/4 * 3;

        PositionArr.add(new Position_Rect(device_top_left_x_point1,device_top_left_y_point1));
        PositionArr.add(new Position_Rect(device_top_right_x_point2,device_top_right_y_point2));
        PositionArr.add(new Position_Rect(device_top_left_x_point3,device_top_left_y_point3));
        PositionArr.add(new Position_Rect(device_top_right_x_point4,device_top_right_y_point4));
        PositionArr.add(new Position_Rect(device_middle_x_point5,device_middle_y_point5));
        PositionArr.add(new Position_Rect(device_bottom_left_x_point6,device_bottom_left_y_point6));
        PositionArr.add(new Position_Rect(device_bottom_right_x_point7,device_bottom_right_y_point7));
        PositionArr.add(new Position_Rect(device_bottom_left_x_point8,device_bottom_left_y_point8));
        PositionArr.add(new Position_Rect(device_bottom_right_x_point9,device_bottom_right_y_point9));

        for (int i = 0; i < PositionArr.size(); i++) {
            Position_Rect rext = PositionArr.get(i);
            log("Point_"+(i+1)+"x="+rext.target_pre_x_point+"    "+"Point_"+(i+1)+"y="+rext.target_pre_y_point);
        }

}





    static public void OperationImageView(ImageView v , MotionEvent e , float scaleNum) {
        v.setScaleType(ImageView.ScaleType.MATRIX);
        float x = e.getX();
        float y = e.getY();
        int device_height =  ZUtil.getRealScreenHeight();
        int device_width =  ZUtil.getRealScreenWidth();

        int origin_drawable_h =  v.getDrawable().getIntrinsicHeight();
        int origin_drawable_w =  v.getDrawable().getIntrinsicWidth();
        int layoutWidth =   v.getLayoutParams().width;
        int layoutHeight =   v.getLayoutParams().height;


        Position_Rect targetRect =    calculTargetRectPosition(x, y ,  device_width , device_height, origin_drawable_w,origin_drawable_h ,  layoutWidth, layoutHeight, scaleNum  );

//        ViewGroup.LayoutParams layoutParams =  v.getLayoutParams();
//        layoutParams.width = (int)targetRect.full_draw_w;
//        layoutParams.height = (int)targetRect.full_draw_h;
//        v.setLayoutParams(layoutParams);


        Matrix matrix =  v.getMatrix();
//        matrix.postScale(targetRect.point_x_scale,targetRect.point_y_scale);
        matrix.postScale(targetRect.point_x_scale,targetRect.point_y_scale,0,0);
//        matrix.postTranslate((targetRect.full_draw_w * -1 ) + device_width,(targetRect.full_draw_h * -1 )+ device_height);   // 以它为左顶点
        matrix.postTranslate(targetRect.target_pre_x_point,targetRect.target_pre_y_point );   // 以它为左顶点

        System.out.println("calculTargetRectPosition    matrix="+matrix);
        v.setImageMatrix(matrix);

    }
    static public void onDoubleTap(ImageView v , MotionEvent e) {
        mScaleMatrix = v.getMatrix();

        int device_height =  ZUtil.getRealScreenHeight();
        int device_width =  ZUtil.getRealScreenWidth();

        float x = e.getX();
        float y = e.getY();


        int image_ori_width = v.getWidth();
        int  image_ori_height =  v.getHeight();

        float device_middle_x = device_width/2;
        float device_middle_y = device_height/2;

        float device_top_left_x_point1 = device_middle_x/2;
        float device_top_left_y_point1 = device_middle_y/4;

        float device_top_right_x_point2 = device_middle_x/2+device_middle_x;
        float device_top_right_y_point2 =  device_middle_y/4;

        float device_top_left_x_point3 = device_middle_x/2;
        float device_top_left_y_point3 = device_middle_y/4 * 3;


        float device_top_right_x_point4 = device_middle_x/2 + device_middle_x ;
        float device_top_right_y_point4 = device_middle_y/4 * 3;

        float device_middle_x_point5 = device_middle_x;
        float device_middle_y_point5 = device_middle_y;


        float device_bottom_left_x_point6 = device_middle_x/2;
        float device_bottom_left_y_point6 = device_middle_y + device_middle_y/4;

        float device_bottom_right_x_point7 = device_middle_x/2+device_middle_x;
        float device_bottom_right_y_point7 = device_middle_y + device_middle_y/4;


        float device_bottom_left_x_point8 = device_middle_x/2;
        float device_bottom_left_y_point8 = device_middle_y + device_middle_y/4 * 3;


        float device_bottom_right_x_point9 = device_middle_x/2+device_middle_x;
        float device_bottom_right_y_point9 = device_middle_y + device_middle_y/4 * 3;



        float image_ori_x =  v.getX();
        float image_ori_y =  v.getY();
/*

        try {
            mScaleMatrix.postScale(sx, sy,device_top_left_x_point1,device_top_left_y_point1);
            log("点_1  point1_x="+device_top_left_x_point1+"   point1_y="+device_top_left_y_point1);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_top_right_x_point2,device_top_right_y_point2);
            log("点_2  point2_x="+device_top_right_x_point2+"   point2_y="+device_top_right_y_point2);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_top_left_x_point3,device_top_left_y_point3);
            log("点_3  point3_x="+device_top_left_x_point3+"   point3_y="+device_top_left_y_point3);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_top_right_x_point4,device_top_right_y_point4);
            log("点_4  point4_x="+device_top_right_x_point4+"   point4_y="+device_top_right_y_point4);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_middle_x_point5,device_middle_y_point5);
            log("点_5  point5_x="+device_middle_x_point5+"   point5_y="+device_middle_y_point5);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_bottom_left_x_point6,device_bottom_left_y_point6);
            log("点_6  point6_x="+device_bottom_left_x_point6+"   point6_y="+device_bottom_left_y_point6);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_bottom_right_x_point7,device_bottom_right_y_point7);
            log("点_7  point7_x="+device_bottom_right_x_point7+"   point7_y="+device_bottom_right_y_point7);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_bottom_left_x_point8,device_bottom_left_y_point8);
            log("点_8  point8_x="+device_bottom_left_x_point8+"   point8_y="+device_bottom_left_y_point8);
            v.requestLayout();
            Thread.sleep(5000);
            mScaleMatrix.postScale(sx, sy,device_bottom_right_x_point9,device_bottom_right_y_point9);
            log("点_9  point9_x="+device_bottom_left_x_point8+"   point9_y="+device_bottom_left_y_point8);

        }catch ( Exception e1){

        }
*/

        float targetX = device_top_left_x_point1;
        float targetY = device_top_left_y_point1;
        Position_Rect rect =  PositionArr.get(point_index%PositionArr.size());


        Rect imageRect =  v.getDrawable().getBounds();

        int origin_drawable_h =  v.getDrawable().getIntrinsicHeight();
        int origin_drawable_w =  v.getDrawable().getIntrinsicWidth();

        float imageRect_x = imageRect.width();
        float imageRect_y = imageRect.height();

        targetX = rect.target_pre_x_point;
        targetY = rect.target_pre_y_point;

        float base_scale_x = device_width/ origin_drawable_w ;
        float base_scale_y = device_height/origin_drawable_h;

        float scale_base_imageRect_x = origin_drawable_w * base_scale_x;
        float scale_base_imageRect_y = origin_drawable_h * base_scale_y;

        float scaleNum = 2f;

        float result_scale_x = base_scale_x * scaleNum;
        float result_scale_y = base_scale_y * scaleNum;


        float scale_result_imageRect_x = origin_drawable_w * result_scale_x;
        float scale_result_imageRect_y = origin_drawable_h * result_scale_y;


        targetX = 0;   // targetX 和 targetY 是缩放的显示的位置 即在左上角的坐标
        targetY = 0;

        // 缩放 以 点击的点为中心  那么 要计算它的 左上角的点 怎么算? 而且 好像 比例 都放大了  中心点的坐标也变化了
        float after_scale_x = x * result_scale_x;
        float after_scale_y = y * result_scale_y;

        // 以 after_scale_x  和  after_scale_y 为 中心点的左上角坐标为
        float after_scale_begin_x = after_scale_x - device_width/2;
        float after_scale_begin_y = after_scale_y - device_height/2;


        int layoutWidth =   v.getLayoutParams().width;
        int layoutHeight =   v.getLayoutParams().height;

        Position_Rect targetRect =    calculTargetRectPosition(x, y ,  device_width , device_height, origin_drawable_w,origin_drawable_h ,  layoutWidth,layoutHeight, scaleNum  );



//        mScaleMatrix.postScale(targetRect.point_x_scale, targetRect.point_y_scale,targetRect.after_scale_x,targetRect.after_scale_y);

//        mScaleMatrix.postScale(targetRect.point_x_scale, targetRect.point_y_scale,x,y);

        point_index++;

        v.setImageMatrix(mScaleMatrix);
        v.requestLayout();

       float image_new_x =  v.getX();
        float image_new_y =  v.getY();
        int image_new_width = v.getWidth();
        int  image_new_height =  v.getHeight();
       Rect newRect =  v.getDrawable().getBounds();

        log("calculTargetRectPosition  image_new_x = "+ image_new_x +"    image_new_y = "+ image_new_y +"  image_new_width="+image_new_width+"  image_new_height="+image_new_height +"  newRect.width()"+ newRect.width() + "   newRect.high="+newRect.height() +"    mScaleMatrix="+mScaleMatrix.toShortString()   +"   mScaleMatrix.toString="+mScaleMatrix.toString());


//设置 device_width = 1080   device_height=2340 image_ori_width=1080  image_ori_height=2340 image_new_width=1080 image_new_height=2340  x=672.0  y=1055.0    sx=1.5   sy=1.5   image_ori_x=0.0   image_ori_y=0.0  image_new_x=0.0  image_new_y=0.0  imageRect_x=640.0   imageRect_y=1136.0



        //   好像 代码 就是这样的
        if(true){
            return;
        }
        log("当前scale " + getScale(v) + "mid: " + mMidScale);
        if (getScale(v) < mMidScale) {
//            mScaleMatrix.postScale(mMidScale/getScale(v), mMidScale/getScale(v),v.getWidth()/2,v.getHeight()/2);
            mScaleMatrix.postScale(2/getScale(v), 2/getScale(v),x,y);

            log("设置 mInitScale="+mInitScale+"  mInitScale="+mInitScale+"   v.getWidth()/2="+v.getWidth()/2+"   v.getHeight()/2="+v.getHeight()/2 +"  mScaleMatrix="+mScaleMatrix.toShortString());

            v.setImageMatrix(mScaleMatrix);

        } else {
            log("恢复初始化");
            //计算将图片移动至中间距离
            int dx = v.getWidth() / 2 - v.getDrawable().getIntrinsicWidth() / 2;
            int dy = v.getHeight() / 2 - v.getDrawable().getIntrinsicHeight() / 2;
            mScaleMatrix.reset();
            mScaleMatrix.postTranslate(dx, dy);
            mScaleMatrix.postScale(mInitScale, mInitScale,v.getWidth()/2,v.getHeight()/2);
            log("恢复初始化  mInitScale="+mInitScale+"  mInitScale="+mInitScale+"   v.getWidth()/2="+v.getWidth()/2+"   v.getHeight()/2="+v.getHeight()/2 +"  mScaleMatrix="+mScaleMatrix.toShortString());
            v.setImageMatrix(mScaleMatrix);
        }
    }

   public static Position_Rect calculTargetRectPosition(float click_x, float click_y, int device_w, int device_h, int origin_draw_w, int origin_draw_h, int layout_width, int layout_height, float scaleNum){

        Position_Rect resultRect = new Position_Rect(0,0);
       float baseScaleNum_Scale_1_x = (float)device_w/(float)origin_draw_w;
       float baseScaleNum_Scale_1_y = (float)device_h/(float)origin_draw_h;

       float full_draw_width = (float) baseScaleNum_Scale_1_x * (float)origin_draw_w * scaleNum;
       float full_draw_high = (float) baseScaleNum_Scale_1_y * (float)origin_draw_h * scaleNum;

       // 手指 按下的 x 坐标 在 扩大 后的 新的坐标 值
       float after_scale_x =   (float)click_x * scaleNum;
       float after_scale_y =   (float)click_y * scaleNum;
       // 以 after_scale_x  和  after_scale_y 为 中心点的左上角坐标为

      float A =  ((float)(full_draw_width/(float)origin_draw_w));
       float B =  ((float)(full_draw_high/(float)origin_draw_h));
       float point_x_scale = ((float)full_draw_width/(float)origin_draw_w) ;
       float point_y_scale = ((float)(full_draw_high/(float)origin_draw_h)) ;

       float x_center = after_scale_x;
       float y_center = after_scale_y;

       float pre_x_point = Math.abs(after_scale_x - (float)(device_w/2));
       float pre_y_point = Math.abs(after_scale_y - (float)(device_h/2));

       float target_pre_x_point = 0f;
       float target_pre_y_point = 0f;

       //   放大后 为了是能X轴全屏显示  最大的移动距离  是一个负值！！！  不能比这个更小
       float full_draw_left_bottom_Max_X =  (full_draw_width * -1 ) + device_w;

       //   放大后 为了是能Y轴全屏显示  最大的移动距离  是一个负值！！！  不能比这个更小
       float full_draw_left_bottom_Max_Y = (full_draw_high * -1 ) + device_h;

       // x 轴 移动的范围 从   MAX_X --- 0 之间
       // y 轴 移动的范围 从   MAX_Y --- 0 之间
       target_pre_x_point = pre_x_point * -1;
       target_pre_y_point = pre_y_point * -1;

       if(target_pre_x_point < full_draw_left_bottom_Max_X){
           target_pre_x_point = full_draw_left_bottom_Max_X;
       }

       if(target_pre_y_point < full_draw_left_bottom_Max_Y){
           target_pre_y_point = full_draw_left_bottom_Max_Y;
       }



       resultRect.target_pre_x_point = target_pre_x_point;
       resultRect.target_pre_y_point = target_pre_y_point;
       resultRect.after_scale_x = after_scale_x;
       resultRect.after_scale_y = after_scale_y;
       resultRect.full_draw_h = full_draw_high;
       resultRect.full_draw_w = full_draw_width;
       resultRect.point_x_scale = point_x_scale;
       resultRect.point_y_scale = point_y_scale;
       resultRect.center_x=(float)(device_w/2);
       resultRect.center_y=(float)(device_h/2);
       System.out.println("calculTargetRectPosition  click_x= "+click_x+"   click_y="+click_y+"   after_scale_x="+after_scale_x+"  after_scale_y="+after_scale_y+"   pre_x_point="+pre_x_point+"   pre_y_point="+pre_y_point+" target_pre_x_point="+target_pre_x_point+"   target_pre_y_point="+target_pre_y_point+"  device_w="+device_w+"   device_h="+device_h+"  origin_draw_w="+origin_draw_w+"   origin_draw_h="+origin_draw_h+"  baseScaleNum_Scale_1_x="+baseScaleNum_Scale_1_x+"   baseScaleNum_Scale_1_y="+baseScaleNum_Scale_1_y+"  scaleNum="+scaleNum +"  full_draw_width = "+ full_draw_width +"    full_draw_high="+full_draw_high +"  A="+A+"   B="+B +"  point_x_scale="+point_x_scale+"   point_y_scale="+point_y_scale +"   layout_width="+layout_width+"    layout_height="+layout_height);







        return resultRect;


    }

    /**
     * 获取当前图片的缩放值
     *
     * @return
     */
    static  public float getScale(ImageView v) {
        float[] values = new float[9];
        v.getMatrix().getValues(values);
        return values[Matrix.MSCALE_X];
    }


    static public  void onGlobalLayout(ImageView v) {

            //得到控件的宽和高
            int width = v.getWidth();
            int height = v.getHeight();
            mScaleMatrix = v.getImageMatrix();
            //拿到图片的宽高
            Drawable drawable = v.getDrawable();
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
//            log("onGlobalLayout 计算出的 scale = "+scale +" 默认所有的 scale 都为 1");
             scale = 1.0f;

            mInitScale = scale;
            mMinScale = scale;
            mMidScale = scale * 2;
            mMaxScale = scale * 5;

            //计算将图片移动至中间距离
            int dx = v.getWidth() / 2 - drawableWidth / 2;
            int dy = v.getHeight() / 2 - drawableHeight / 2;

//            mScaleMatrix.postTranslate(dx, dy);
            //xy方向不变形，必须传一样的
//            mScaleMatrix.postScale(mInitScale, mInitScale, width / 2, height / 2);
//            v.setImageMatrix(mScaleMatrix);


    }


   public static boolean onTouch(ImageView v, MotionEvent event) {

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
        RectF rectF = getMatrixRectF(v);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //请求不被拦截
                if(rectF.width() > v.getWidth() || rectF.height() > v.getHeight()){
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

            case MotionEvent.ACTION_MOVE:
                if(rectF.width() > (v.getWidth() + 0.01) || rectF.height() > (v.getHeight() + 0.01)){
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }

                float dx = currentX - mLastX;
                float dy = currentY - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }

                if (isCanDrag) {
                    RectF rectf = getMatrixRectF(v);
                    if (v.getDrawable() != null) {
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        //如果宽度小于控件宽度,不允许横向移动
                        if (rectf.width() < v.getWidth()) {
                            dx = 0;
                            isCheckLeftAndRight = false;
                        }
                        //若高度小于控件高度,不允许纵向移动
                        if (rectf.height() < v.getHeight()) {
                            dy = 0;
                            isCheckTopAndBottom = false;
                        }
//                        mScaleMatrix.postTranslate(dx, dy);
//                        checkBorderForTraslate(v);
//                        v.setImageMatrix(mScaleMatrix);
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
     * 当移动时,进行边界检查.
     */
    static void checkBorderForTraslate(ImageView imageView) {
        RectF rectF = getMatrixRectF(imageView);

        float deltaX = 0;
        float deltaY = 0;
        int width = imageView.getWidth();
        int height = imageView.getHeight();

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

    /**
     * 判断当前移动距离是否大于系统默认最小移动距离
     *
     * @param dx
     * @param dy
     * @return
     */
    static boolean isMoveAction(float dx, float dy) {
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

    static RectF getMatrixRectF(ImageView v) {
        Matrix matrix = mScaleMatrix;
        RectF rectF = new RectF();

        Drawable drawable = v.getDrawable();

        if (drawable != null) {
            rectF.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            //用matrix进行map一下
            matrix.mapRect(rectF);
        }

        return rectF;
    }


}
