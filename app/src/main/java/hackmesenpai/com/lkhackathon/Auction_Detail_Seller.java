package hackmesenpai.com.lkhackathon;

/**
 * Created by nhuluong on 7/15/17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class Auction_Detail_Seller extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auction_detail_seller);
        File file = new File("vase.jpg");
        Uri uri = Uri.fromFile(file);
        ImageView imageView = (ImageView) findViewById(R.id.item_image);
        imageView.setImageURI(uri);


    }

}