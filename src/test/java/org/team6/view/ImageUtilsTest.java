package org.team6.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class ImageUtilsTest {
    private ImageView testImageView;
    private static final int START_WIDTH = 8;
    private static final int START_HEIGHT = 8;

    @BeforeEach
    void setUp() {
        testImageView = new ImageView();
        testImageView.setFitWidth(START_WIDTH);
        testImageView.setFitHeight(START_HEIGHT);
    }

    @ParameterizedTest
    @EnumSource(ImagePath.class)
    void testScaleImageViewAccordingToImage_GivenDifferentImageSizes_ShouldScaleCorrectly(ImagePath imagePath) {
        Image image = imagePath.getImage();
        ImageUtils.scaleImageViewAccordingToImage(testImageView, image, START_WIDTH, START_HEIGHT);
        // Should fit into the ImageView dimensions
        assertTrue(testImageView.getFitWidth() <= START_WIDTH);
        assertTrue(testImageView.getFitHeight() <= START_HEIGHT);
    }
}
