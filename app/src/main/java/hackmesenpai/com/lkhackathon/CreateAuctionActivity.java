package hackmesenpai.com.lkhackathon;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by karan on 7/15/17.
 */

public class CreateAuctionActivity extends AppCompatActivity {

    private static final String TAG = "CreateAuctionActivity";
    String itemName = "";
    String itemDescription = "";
    String endPrice = "";
    String endDate = "";

    @BindView(R.id.item_name) EditText _itemName;
    @BindView(R.id.item_description) EditText _itemDescription;
    @BindView(R.id.end_price) EditText _endPrice;
    @BindView(R.id.end_date) EditText _endDate;
    @BindView(R.id.create_button) Button _createAuctionButton;
//    @BindView(R.id.add_image) Image _addImageButton;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_create_auction);
        ButterKnife.bind(this);

        _createAuctionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAuction();
                Intent intent = new Intent(getApplicationContext(), ConfirmAuctionActivity.class);
                startActivity(intent);
            }
        });

    }

    public void createAuction() {
        Log.d(TAG, "Create Auction");

        itemName = _itemName.getText().toString();
        itemDescription = _itemDescription.getText().toString();
        endPrice = _endPrice.getText().toString();
        endDate = _endDate.getText().toString();
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
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
