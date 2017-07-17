package hackmesenpai.com.lkhackathon;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        TextView text = (TextView)findViewById(R.id.user_name);
        text.setTextColor(Color.BLACK);
        text.setTextSize(12);
        text.setText("Android Bot");

        Button list = (Button) findViewById(R.id.listing);
        list.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Log.i("clicks","You Clicked B1");
        Intent i=new Intent(this, Auction_Detail_Seller.class);
        startActivity(i);
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
