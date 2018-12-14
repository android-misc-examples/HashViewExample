package it.pgp.hashviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button showHv = findViewById(R.id.showHv);
        Button showMultiHv = findViewById(R.id.showMultiHv);
        showHv.setOnClickListener(v->new MainDialog(this).show());
        showMultiHv.setOnClickListener(v -> new Thread(()-> runOnUiThread(()->MultiHashViewDialog.addHashView(12345,MainActivity.this))).start());

        Button queryHs = findViewById(R.id.queryHs);
        queryHs.setOnClickListener(v-> Toast.makeText(this, "Number is "+MultiHashViewDialog.hs.get(), Toast.LENGTH_SHORT).show());

        Button showPopupWindow = findViewById(R.id.showPopupWindow);
        showPopupWindow.setOnClickListener(v -> PopupWindowUtils.createAndShowHashViewPopupWindow(
                this,R.layout.movable_popup_window,R.id.hvContainer,showPopupWindow
        ));
    }
}
