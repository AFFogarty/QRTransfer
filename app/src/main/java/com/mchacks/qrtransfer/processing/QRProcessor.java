package com.mchacks.qrtransfer.processing;

import android.media.Image;

import java.io.File;
import java.util.LinkedList;


public class QRProcessor implements QRInterface {

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
}
