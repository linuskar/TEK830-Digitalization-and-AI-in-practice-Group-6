package org.team6.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Objects;

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

    public static void setImageViewSize(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public static void scaleImageViewAccordingToImage(ImageView imageView, Image image, double startWidth, double startHeight) {
        double imageAspectRatio = image.getWidth() / image.getHeight();
        double viewAspectRatio = startWidth / startHeight;

        // Adjust the ImageView dimensions based on the image's aspect ratio
        if (imageAspectRatio > viewAspectRatio) {
            // If the image is wider relative to the ImageView, scale by width
            setImageViewSize(imageView, startWidth, startWidth / imageAspectRatio);
        } else {
            // If the image is taller relative to the ImageView, scale by height
            setImageViewSize(imageView, startHeight * imageAspectRatio, startHeight);
        }
    }

    public static String getFileResourceString(String imageFilePath) {
        URL fileUrl = ImageUtils.class.getResource(imageFilePath);
        return Objects.requireNonNull(fileUrl).toExternalForm();
    }
}
