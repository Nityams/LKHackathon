package hackmesenpai.com.lkhackathon;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

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
        Intent i=new Intent(HomePage.this, Auction_Detail_Seller.class);
        startActivity(i);
    }
}
