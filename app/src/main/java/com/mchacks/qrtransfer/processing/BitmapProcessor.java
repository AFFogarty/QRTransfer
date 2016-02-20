package com.mchacks.qrtransfer.processing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

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
}
