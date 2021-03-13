package com.rriiyyaa;

import com.rriiyyaa.Abstract.ImageBaseOperation;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class FilteringImage extends ImageBaseOperation {
    public void  generateTest() throws IOException {
        BufferedImage image = readImage("lena", "png");
        image = convertToMono(image);
        saveImage(image, "mono");
    }
}
