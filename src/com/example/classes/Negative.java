package com.example.classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import com.example.interfaces.IPlugin;

/**
 * Created by Artyom on 07.12.2014.
 */
public class Negative implements IPlugin {

    @Override
    public String getPluginName() {
        return "Pixelize";
    }

    @Override
    public String getPluginDescription() {
        return "Pizelize an image";
    }

    @Override
    public Bitmap start(Bitmap src) {
        // create new bitmap with the same settings as source bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // color info
        int A, R, G, B;
        int pixelColor;
        // image size
        int height = src.getHeight();
        int width = src.getWidth();

        // scan through every pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // get one pixel
                pixelColor = src.getPixel(x, y);
                // saving alpha channel
                A = Color.alpha(pixelColor);
                // inverting byte for each R/G/B channel
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                // set newly-inverted pixel to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final bitmap
        return bmOut;
    }


}
