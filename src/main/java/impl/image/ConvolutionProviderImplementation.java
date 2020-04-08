package impl.image;

import api.image.ConvolutionProvider;

import java.awt.*;

public class ConvolutionProviderImplementation implements ConvolutionProvider {

    @Override
    public Color[][] apply(Color[][] image, double[][] kernel) {
        Color[][] bluredImage = new Color[image.length][image[0].length];
        int size = kernel.length;
        int step = size / 2;
        int bluredImageColorAlpha = 255;
        int imageSizeRow = image.length;
        int imageSizeColumn = image[0].length;

        for (int i = 0; i < imageSizeRow; i++) {
            for (int j = 0; j < imageSizeColumn; j++) {
                // bluring
                int bluredImageColorRed = 0;
                int bluredImageColorGreen = 0;
                int bluredImageColorBlue = 0;
                int imageRow = -step;
                int kernelRow = size - 1;

                while (imageRow <= i + step && kernelRow >= 0) {
                    int imageColumn = -step;
                    int kernelColumn = size - 1;

                    while (imageColumn <= j + step && kernelColumn >= 0) {
                        double kernelItem = kernel[kernelRow][kernelColumn];
                        if (isInRange(i + imageRow, imageSizeRow) && isInRange(j + imageColumn, imageSizeColumn)) {
                            Color imageColor = image[i + imageRow][j + imageColumn];
                            bluredImageColorRed += imageColor.getRed() * kernelItem;
                            bluredImageColorGreen += imageColor.getGreen() * kernelItem;
                            bluredImageColorBlue += imageColor.getBlue() * kernelItem;
                        }

                        imageColumn++;
                        kernelColumn--;
                    }
                    imageRow++;
                    kernelRow--;
                }
                bluredImage[i][j] = new Color(bluredImageColorRed, bluredImageColorGreen, bluredImageColorBlue, bluredImageColorAlpha);
            }
        }
        return bluredImage;
    }

    boolean isInRange(int i, int length) {
        return i >= 0 && i < length;
    }
}
