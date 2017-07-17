package hackmesenpai.com.lkhackathon;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

        import butterknife.BindView;
        import butterknife.ButterKnife;


public class ConfirmAuctionActivity extends AppCompatActivity {

    private static final String TAG = "ConfirmAuctionActivity";
    @BindView(R.id.userProfile)  Button _userProfile;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_auction_confirm);
        ButterKnife.bind(this);

        _userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profile.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        //Go back to the MainActivity
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);

    }
    public void upload(View view) {
        System.out.println("Uploading!!!!!!");
        Intent intent = new Intent(this, CreateAuctionActivity.class);
        startActivity(intent);
        finish();
    }

//    public void userProfile(View view) {
//        Intent intent = new Intent(this, Profile.class);
//        startActivity(intent);
//        finish();
//    }
    public void homePage(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        finish();
    }
}