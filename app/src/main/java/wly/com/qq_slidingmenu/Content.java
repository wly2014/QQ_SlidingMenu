package wly.com.qq_slidingmenu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/3/13.
 */
public class Content extends ViewGroup {
    private int widthSize;
    private int heightSize;
    public boolean isOpen;

    //    public ListView listView;
    private boolean isFirst;

    private Context context;
    private boolean isMoved;
    private boolean isMoved_R;
    private boolean isMoved_L;
    private Sliding sliding;

    public Content(Context context) {
        this(context, null);
    }

    public Content(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Content(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        Log.i("sliding", getParent() + "");

        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        widthSize = outMetrics.widthPixels;

        isOpen = false;
        isMoved = false;
        isFirst = true;

        requestDisallowInterceptTouchEvent(false);      //false-->可以拦截

    }

    /**
     * 可以通过getLayoutParams设置宽高
     * 或者setMeasuredDimension设置
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            View cView = getChildAt(i);
            cView.measure(widthMeasureSpec, heightMeasureSpec);

            heightSize = cView.getMeasuredHeight();
        }

//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
        Log.i("...", "width:" + widthSize + ",height" + heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int cCount = getChildCount();

        int cWidth;
        int cHeight;
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();  //这里得到的，是上面计算的
            cHeight = childView.getMeasuredHeight();
            Log.i("TAG", "cWidth:" + cWidth + ",cHeight" + cHeight);

            childView.layout(0, 0, cWidth, cHeight);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    float downX;
    float downY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isOpen) {
            return true;    //当打开时，拦截所有的事件
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                Log.i("onInterceptTouchEvent", "DOWN");
                return false;

            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                Log.i("Inter_C_moveX", moveX + "");

                if (moveX > downX) {

                    Log.i("onInterceptTouchEvent", "拦截");
                    return true;//拦截
                }
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                Log.i("isOpen", isOpen + "");
                Log.i("C_downX", downX + "");
                if (isOpen) {        //打开时，也就是拦截了

                    return true;        //只有DOWN返回true时，下面的MOVE，UP才能被该View接收到
                }
                /**
                 * 首先，事件具有“记忆性”：见印象笔记；
                 * 若content的内容是一片空白，也就是说不会对首先传入的DOWN事件做任何的反应，
                 * DOWN事件只得被继续向上传递，最终没有任何层消耗掉，
                 * 因此随后的MOVE，UP等动作，不再传给这些没有接收DOWN的全部层，
                 * 也就是，没反应了.
                 * 因此，在执行到该onTouchEvent时，一定要将DOWN事件拦下，一面又传出去了，无人消耗。
                 */
                return true;//break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();

                sliding.onTouchEvent(event);
                Log.i("sliding", sliding + "");
                return false;
            case MotionEvent.ACTION_UP:
                Log.i("C_Up", "UP了");
                float endX = event.getX();

                sliding.onTouchEvent(event);

        }

        return false;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setSliding(Sliding sliding) {
        this.sliding = sliding;
    }
}
