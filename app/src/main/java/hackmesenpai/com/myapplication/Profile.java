package hackmesenpai.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void upload(View view) {
        Intent intent = new Intent(this, CreateAuction.class);
        startActivity(intent);
        finish();
    }

    public void userProfile(View view) {
        Intent intent = new Intent(this,Profile.class );
        startActivity(intent);
        finish();
    }

    public void homePage(View view){
        Intent intent = new Intent(this,Homepage.class );
        startActivity(intent);
        finish();
    }

}
