package wly.com.qq_slidingmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by wly2014 on 2015/2/10.
 */
public class Sliding extends HorizontalScrollView {
    private LinearLayout layout;

    private ViewGroup menu;
    private Content content;

    private final int widthSize;
    private int mMenuPaddingRight = 350;
    private int mMenuWidth;
    private boolean isOpen = false;
    private boolean isMoving;
    private float downX;
    private float downY;
    private boolean isMoved;
    private float Sliding_downX;
    private float moveX;
    private float preMoveX;


    public Sliding(Context context) {
        this(context, null);
    }

    public Sliding(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Sliding(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        widthSize = outMetrics.widthPixels;

    }

    public void close() {
        this.smoothScrollTo(mMenuWidth, 0);

    }

    public void open() {
        this.smoothScrollTo(0, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        layout = (LinearLayout) getChildAt(0);
        menu = (ViewGroup) layout.getChildAt(0);
        content = (Content) layout.getChildAt(1);

        mMenuWidth = menu.getLayoutParams().width = widthSize - mMenuPaddingRight;//menu的宽度
        content.getLayoutParams().width = widthSize;


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i("changed", changed + "");
        if (changed) {  //刚开始第一次画界面时为true

            this.scrollTo(mMenuWidth, 0);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Sliding_downX = ev.getRawX();
                Log.i("Sliding_downX", Sliding_downX + "");
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        super.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                Log.i("S_downX", downX + "");
                downY = ev.getY();

                break;
            case MotionEvent.ACTION_MOVE:

                moveX = ev.getRawX();
                Log.i("preMoveX", preMoveX + "");

                if (preMoveX == 0) {
                    preMoveX = moveX;
                }
                Log.i("Sliding_moveX", moveX + "");

                this.scrollBy((int) (preMoveX - moveX), 0);
                preMoveX = moveX;

                return true;
            case MotionEvent.ACTION_UP:
                Log.i("S_Up", "UP");
                preMoveX = 0;

                Log.i("Math", Math.abs(Sliding_downX - moveX) + "");
                Log.i("mMenuWidth / 2", mMenuWidth / 2 + "");

                if (Math.abs(Sliding_downX - moveX) <= mMenuWidth / 2) {
                    close();
                } else {
                    open();
                }

                break;
        }
        return false;
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /**
         * 1 ~ 0
         */
        float scale = l * 1.0f / mMenuWidth;
        if (scale == 0) {
            content.isOpen = isOpen = true;
        } else if (scale == 1) {
            content.isOpen = isOpen = false;
        }
        Log.i("onScrollChanged", isOpen + "");

        float rightScale = 0.7f + 0.3f * scale;
        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 1.0f - 0.6f * scale;

//        ViewHelper.setX(menu,l);
        ViewHelper.setTranslationX(menu, l);//相同?

        ViewHelper.setAlpha(menu, leftAlpha);
        /**
         * 缩放content
         */
        ViewHelper.setPivotX(content, 0);
        ViewHelper.setPivotY(content, content.getHeight() / 2);
        ViewHelper.setScaleX(content, rightScale);
        ViewHelper.setScaleY(content, rightScale);


        for (int i = 0; i < menu.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) menu.getChildAt(i);
            ImageView imageView = (ImageView) viewGroup.getChildAt(0);
            TextView textView = (TextView) viewGroup.getChildAt(1);
            ViewHelper.setTranslationX(imageView, l * 0.2f);
            ViewHelper.setRotation(imageView, 45.0f * scale);
            ViewHelper.setTranslationX(textView, l * 0.2f);

        }


    }


}
