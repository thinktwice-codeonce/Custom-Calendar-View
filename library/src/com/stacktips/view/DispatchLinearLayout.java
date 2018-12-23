package com.stacktips.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class DispatchLinearLayout extends LinearLayout {

    private OnSwipeListener onSwipeListener;
    private float previousX;

    public DispatchLinearLayout(@NonNull Context context) {
        super(context);
    }

    public DispatchLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                previousX = event.getX();
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float currentX = event.getX();
                if (currentX - previousX > 100) { //swipe right
                    if (onSwipeListener != null) {
                        onSwipeListener.onSwipeRight();
                    }
                    return true;
                } else if (previousX - currentX > 100) { //swipe left
                    if (onSwipeListener != null) {
                        onSwipeListener.onSwipeLeft();
                    }
                    return true;
                }
        }
        return super.dispatchTouchEvent(event);
    }
}
