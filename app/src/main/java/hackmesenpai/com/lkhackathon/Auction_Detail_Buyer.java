package hackmesenpai.com.lkhackathon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by nhuluong on 7/15/17.
 */

public class Auction_Detail_Buyer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auction_detail_buyer);



    }
    public void upload(View view) {
        System.out.println("Uploading!!!!!!");
        Intent intent = new Intent(this, CreateAuctionActivity.class);
        startActivity(intent);
        finish();
    }

    public void userProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }
    public void homePage(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
