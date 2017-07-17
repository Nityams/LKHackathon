package hackmesenpai.com.lkhackathon;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePage extends AppCompatActivity {

//    @BindView(R.id.userProfile) Button _userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        ButterKnife.bind(this);


//        _userProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Profile.class);
//                startActivity(intent);
//            }
//        });

//        android.support.v4.app.Fragment frag = new Frag();
//
//
////        fragmentTransaction.replace(R.id.fragment_container, newFragment).commit();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frag_container, frag)
//                .commit();
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
