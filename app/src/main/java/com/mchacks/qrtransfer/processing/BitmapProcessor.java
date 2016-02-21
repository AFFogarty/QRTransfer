package com.mchacks.qrtransfer.processing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

/**
 * Created by Andrew on 2016-02-20.
 */
public class BitmapProcessor {

    /**
     * A one color image.
     * @param width
     * @param height
     * @param color
     * @see <http>https://gist.github.com/catehstn/6fc1a9ab7388a1655175#file-androidonecolorimage-java-L8</http>
     * @return A one color image with the given width and height.
     */
    public static Bitmap createImage(int width, int height, int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(0F, 0F, (float) width, (float) height, paint);
        return bitmap;
    }

    public static RGBLuminanceSource toRGBLumSource(Bitmap bm)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int[] pixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);
        return new RGBLuminanceSource(width, height, pixels);
    }

//    public static PlanarYUVLuminanceSource toYUVLumSource(Bitmap bm)
//    {
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        int[] pixels = new int[width * height];
//        bm.getPixels(pixels, 0, width, 0, 0, width, height);
//        return new PlanarYUVLuminanceSource(pixels, width, height, 0, 0, width, height, false);
//    }

    public static BinaryBitmap toBinaryBitmap(Bitmap bm)
    {
        RGBLuminanceSource rgb = toRGBLumSource(bm);
        return new BinaryBitmap(new HybridBinarizer(rgb));
    }
}

