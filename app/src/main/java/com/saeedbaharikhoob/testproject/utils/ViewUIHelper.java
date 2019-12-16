package com.saeedbaharikhoob.testproject.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;


public class ViewUIHelper {

    /**
     * set width and height for view
     * @param view
     * @param width
     * @param height
     */
    public static void setWidthHeightPixel(View view, int width, int height){

        view.getLayoutParams().width = width;
        view.getLayoutParams().height = height;
    }

    /**
     * set margin for view
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */

    public static void setMargin(View view, int left, int top, int right, int bottom){

        ViewGroup.MarginLayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();

        if(params == null)
            return;

        left = dpToPx(left);
        top = dpToPx(top);
        right = dpToPx(right);
        bottom = dpToPx(bottom);

        params.setMargins(left, top, right, bottom);

        view.requestLayout();
    }

    /**
     * set padding for view
     * @param view
     * @param padding
     */
    public static void setPadding(View view, int padding) {

        setPadding(view, padding, padding, padding, padding);
    }

    /**
     * set padding with custom size for view
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setPadding(View view, int left, int top, int right, int bottom){

        left = dpToPx(left);
        top = dpToPx(top);
        right = dpToPx(right);
        bottom = dpToPx(bottom);

        view.setPadding(left, top, right, bottom);
    }

    /**
     * convert dp to px
     * @param dp
     * @return
     */
    public static int dpToPx(int dp)
    {
        if(G.context != null)
            return (int) (dp * G.context.getResources().getSystem().getDisplayMetrics().density);

        return dp;
    }

    /**
     * convert px to dp
     * @param px
     * @return
     */
    public static int pxToDp(int px)
    {
        if(G.context != null)
            return (int) (px / G.context.getResources().getSystem().getDisplayMetrics().density);

        return px;
    }

    /**
     * get phones screen height
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return height;
    }

    /**
     * get phones screen width
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size.x;

    }


}
