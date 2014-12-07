package com.example.interfaces;

import android.graphics.Bitmap;

/**
 * Created by Artyom on 07.12.2014.
 */
public interface IPlugin {
    public String getPluginName();
    public String getPluginDescription();
    public Bitmap start(Bitmap bitmapIn);
}
