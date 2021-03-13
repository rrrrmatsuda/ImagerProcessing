package com.rriiyyaa.Abstract;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class ImageBaseOperation {
    private final int IMAGE_SIZE = 400;

    public static int a(int c) {
        return c >>> 24;
    }

    public static int r(int c) {
        return c >> 16 & 0xff;
    }

    public static int g(int c) {
        return c >> 8 & 0xff;
    }

    public static int b(int c) {
        return c & 0xff;
    }

    public static int gray(int c) {
        return c << 16 | c << 8 | c;
    }

    public static int rgb(int r, int g, int b) {
        return 0xff000000 | r << 16 | g << 8 | b;
    }

    public static int argb(int a, int r, int g, int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }

    /**
     * BufferedImageの画像をfileName.jpgで保存する
     * @param saveImage
     * @param fileName
     * @throws IOException
     */
    public void saveImage(BufferedImage saveImage, String fileName) throws IOException {
        String cd = new File(".").getAbsoluteFile().getParent();
        File output = new File(cd + "/src/resources/output/" + fileName + ".jpg");
        ImageIO.write(saveImage, "jpg", output);
    }

    /**
     * inputフォルダ内のファイル名を指定して画像をBufferedImageに変換する
     * @param filePath
     * @return
     * @throws IOException
     */
    public BufferedImage readImage(String filePath, String fileExtension) throws IOException{
        String cd = new File(".").getAbsoluteFile().getParent();

        File inputFile = new File(cd + "/src/resources/input/" + filePath + "." + fileExtension);
        BufferedImage read = ImageIO.read(inputFile);

        return read;
    }


    /**
     * BufferedImageの画像をグレースケール変換する
     * @param image
     * @return
     */
    public BufferedImage convertToMono(BufferedImage image) {

        int w = image.getWidth(), h = image.getHeight();
        BufferedImage monoImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int c = image.getRGB(x, y);
                int mono = (int) (0.299 * this.r(c) + 0.587 * this.g(c) + 0.114 * this.b(c));
                int rgb = (this.a(c) << 24) + (mono << 16) + (mono << 8) + mono;
                monoImage.setRGB(x, y, rgb);
            }
        }

        return monoImage;
    }



    
}
