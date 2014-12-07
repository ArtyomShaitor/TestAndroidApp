package com.example.TestAndroidApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.example.classes.Negative;
import com.example.interfaces.IPlugin;

/**
 * Created by Artyom on 07.12.2014.
 */
public class imageEditor extends Activity {

    private Bitmap bmp;
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_editor);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("image");

        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image = (ImageView) findViewById(R.id.imageView);
        image.setImageBitmap(bmp);

        Button button = (Button)findViewById(R.id.button);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}