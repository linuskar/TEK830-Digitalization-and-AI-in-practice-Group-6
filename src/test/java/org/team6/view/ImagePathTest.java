package org.team6.view;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class ImagePathTest {
    @ParameterizedTest
    @EnumSource(ImagePath.class)
    void testGetImage_ForALLImagePath_ReturnsImage(ImagePath imagePath) {
        assertNotNull(imagePath.getImage());
    }
}
