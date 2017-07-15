package hackmesenpai.com.lkhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by karan on 7/15/17.
 */

public class ConfirmAuctionActivity extends AppCompatActivity {

    private static final String TAG = "ConfirmAuctionActivity";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_auction_confirm);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        //Go back to the MainActivity
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }
}
