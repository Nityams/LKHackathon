package hackmesenpai.com.lkhackathon;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        android.support.v4.app.Fragment frag = new Frag();


//        fragmentTransaction.replace(R.id.fragment_container, newFragment).commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, frag)
                .commit();
    }


    public void upload(View view) {
    }

    public void userProfile(View view) {
    }
}
