package com.example.TestAndroidApp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private final static int SELECTED_PICTURE = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button chooseImage = (Button)findViewById(R.id.button);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECTED_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case SELECTED_PICTURE:
                if(resultCode==RESULT_OK){
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(selectedImage);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();

                    Intent intent = new Intent(this, imageEditor.class);
                    intent.putExtra("image", byteArray);
                    startActivity(intent);
                    finish();
                }
                break;

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
