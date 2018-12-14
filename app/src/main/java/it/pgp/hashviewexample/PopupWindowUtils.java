package it.pgp.hashviewexample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PopupWindowUtils {

    public static PopupWindow createAndShowHashViewPopupWindow(Activity activity,
                                                               int layoutResId,
                                                               int targetHvId,
                                                               View anchor) {
        LayoutInflater layoutInflater = (LayoutInflater)activity.getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = layoutInflater.inflate(R.layout.movable_popup_window, null);
        View popupView = layoutInflater.inflate(layoutResId, null);

//        LinearLayout container = popupView.findViewById(R.id.hvContainer);
        LinearLayout container = popupView.findViewById(targetHvId);
        container.addView(new HashView(activity,new byte[0],5,4,600,600));

        PopupWindow popupWindow = new MovablePopupWindow(
                popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button btnDismiss = popupView.findViewById(R.id.dismiss);

        btnDismiss.setOnClickListener(view -> popupWindow.dismiss());

        popupWindow.showAsDropDown(anchor, 50, -30);

        return popupWindow;
    }
}
