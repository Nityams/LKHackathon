package hackmesenpai.com.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RedFlash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_flash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                go();
            }
        }, 200);


    }

    private void go() {
        Intent intent = new Intent(this, BuyerAuction.class);
        startActivity(intent);
        finish();
    }
}