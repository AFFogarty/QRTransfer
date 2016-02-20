package com.mchacks.qrtransfer.processing;


import android.media.Image;

import java.io.File;
import java.util.LinkedList;

public interface QRInterface {

    LinkedList<Image> fileToQrCodes(File file);

    File videoToFile(File videoFile);
}
