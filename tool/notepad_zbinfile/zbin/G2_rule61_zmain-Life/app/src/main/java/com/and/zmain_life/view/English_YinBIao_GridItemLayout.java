package com.and.zmain_life.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author mzw
 * @Description: 高度和宽度一样
 */
public class English_YinBIao_GridItemLayout extends RelativeLayout {

	private int indexA;
	private int indexB;
	private int indexC;

	private String strA;
	private String strB;
	private String strC;

	private Object obj;

    public int getIndexA() {
		return indexA;
	}

	public void setIndexA(int indexA) {
		this.indexA = indexA;
	}

	public int getIndexB() {
		return indexB;
	}

	public void setIndexB(int indexB) {
		this.indexB = indexB;
	}

	public int getIndexC() {
		return indexC;
	}

	public void setIndexC(int indexC) {
		this.indexC = indexC;
	}

	public String getStrA() {
		return strA;
	}

	public void setStrA(String strA) {
		this.strA = strA;
	}

	public String getStrB() {
		return strB;
	}

	public void setStrB(String strB) {
		this.strB = strB;
	}

	public String getStrC() {
		return strC;
	}

	public void setStrC(String strC) {
		this.strC = strC;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public English_YinBIao_GridItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public English_YinBIao_GridItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public English_YinBIao_GridItemLayout(Context context) {
        super(context);
    }
 
    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // For simple implementation, or internal size is always 0.
        // We depend on the container to specify the layout size of
        // our view. We can't really know what it is since we will be
        // adding and removing different arbitrary views and do not
        // want the layout to change as this happens.
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
 
        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}