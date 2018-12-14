package it.pgp.hashviewexample;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;

class MainDialog extends Dialog {
    MainDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_main);

        HashView hv = new HashView(context,new byte[0],5,4,600,600);
        LinearLayout hvLayout = findViewById(R.id.hvLayout);
        hvLayout.addView(hv);
    }
}
