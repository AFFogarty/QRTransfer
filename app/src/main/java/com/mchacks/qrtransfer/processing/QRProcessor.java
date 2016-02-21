package com.mchacks.qrtransfer.processing;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.util.LinkedList;


public class QRProcessor implements QRInterface {

    public static Bitmap generateQrCode(String myCodeText) throws WriterException
    {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        int size = 800;

        BitMatrix bitMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size);
        int width = bitMatrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, width, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                bmp.setPixel(y, x, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

    /**
     * Encode a file as QR code images.
     *
     * @param file a file that we want to convert to QR codes
     * @return a linked list of QR code images
     */
    @Override
    public LinkedList<Image> fileToQrCodes(File file) {
        return null;
    }

    /**
     * Given a video file, scan for QR codes and return the decoded file.
     *
     * @param videoFile a file that is a video
     * @return the decoded file
     */
    @Override
    public File videoToFile(File videoFile) {
        return null;
    }


    public static String readQRCode(Bitmap image) throws FormatException, ChecksumException, NotFoundException {
        QRCodeReader reader = new QRCodeReader();
        int[] pixels = new int[image.getWidth() * image.getHeight()];

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixels[i * j] = image.getPixel(i, j);
            }
        }

        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(image.getWidth(), image.getHeight(), pixels);
        GlobalHistogramBinarizer globalHistogramBinarizer = new GlobalHistogramBinarizer(rgbLuminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(globalHistogramBinarizer);
        Result result = reader.decode(binaryBitmap);
        return result.getText();
    }
}
