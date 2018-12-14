package it.pgp.hashviewexample;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiHashViewDialog extends Dialog {

    public static final AtomicInteger hs = new AtomicInteger(0);
    public static MultiHashViewDialog instance = null;

    public LinearLayout hvLayout;
    public Context context;

    // pass context anyway even if can be unused
    public static void addHashView(int generatedViewId, Context context) {
        synchronized (hs) {
            int h = hs.get();
            HashView hv = new HashView(context,new byte[0],5,4,600,600);
            hv.setId(generatedViewId);
            if (h==0) {
                instance = new MultiHashViewDialog(context);
                instance.hvLayout.addView(hv);
                instance.show();
            }
            else if (h>0) {
                instance.hvLayout.addView(hv);
            }
            else throw new RuntimeException("Negative number of hashviews");
            hs.incrementAndGet();
        }
    }

    public static void removeHashView(int generatedViewId) {
        synchronized (hs) {
            int h = hs.get();
            if (h == 1) {
                instance.dismiss();
                instance = null;
            }
            else if (h > 1) {
                instance.hvLayout.removeView(instance.findViewById(generatedViewId));
            }
            else throw new RuntimeException("Guard block");
            hs.decrementAndGet();
        }
    }

    public MultiHashViewDialog(Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_main);
        hvLayout = findViewById(R.id.hvLayout);
        setOnDismissListener(dialogInterface -> {
            synchronized (hs) {
                hs.set(0);
            }
        });
    }
}
