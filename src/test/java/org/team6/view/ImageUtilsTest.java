package org.team6.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageUtilsTest {
    private Image testImage;
    private ImageView testImageView;
    private static final String TEST_FILE_PATH = "/org/team6/images/tutorial/info0.png";

    @BeforeEach
    void setUp() {
        String fileResourceString = ImageUtils.getFileResourceString(TEST_FILE_PATH);
        testImage = new Image(fileResourceString);
        testImageView = new ImageView();
    }

    @Test
    void testScaleImageViewAccordingToImage_GivenImageIsWider_ShouldScaleByWidth() {
        ImageUtils.scaleImageViewAccordingToImage(testImageView, testImage, 100, 100);
        double expectedWidth = 100;
        double expectedHeight = 100 / (testImage.getWidth() / testImage.getHeight());

        double actualWidth = testImageView.getFitWidth();
        double actualHeight = testImageView.getFitHeight();

        assertEquals(expectedWidth, actualWidth);
        assertEquals(expectedHeight, actualHeight);
    }
}
