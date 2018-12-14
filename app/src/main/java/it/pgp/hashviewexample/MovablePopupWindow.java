package it.pgp.hashviewexample;

import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

public class MovablePopupWindow extends PopupWindow {
    private void makeMovable(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            int orgX, orgY;
            int offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        orgX = (int) event.getX();
                        orgY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        offsetX = (int)event.getRawX() - orgX;
                        offsetY = (int)event.getRawY() - orgY;
                        update(offsetX, offsetY, -1, -1, true);
                        break;
                }
                return true;
            }});
    }

    public MovablePopupWindow(View contentView) {
        super(contentView);
        makeMovable(contentView);
    }

    public MovablePopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        makeMovable(contentView);
    }

    public MovablePopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        makeMovable(contentView);
    }
}
