package org.team6.view;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ImageUtils {
    private ImageUtils() {}
    
    public static void makeCornersRounded(ImageView imageView, double arcSize) {
        makeCornersRounded(imageView, arcSize, arcSize);
    }

    public static void makeCornersRounded(ImageView imageView, double arcWidth, double arcHeight) {
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(arcWidth);
        clip.setArcHeight(arcHeight);
        imageView.setClip(clip);
    }
}
