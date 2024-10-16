package org.team6.tutorial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(ApplicationExtension.class)
class TutorialPageItemHandlerTest {
    private TutorialPageItemHandler tutorialPageItemHandler;

    @BeforeEach
    void setUp() {
        tutorialPageItemHandler = new TutorialPageItemHandler();
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnATutorialPageItem() {
        TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(0);
        assertNotNull(tutorialPageItem);
    }

    @Test
    void testGetTutorialPageItem_GivenNegativeIndex_ShouldThrowIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> tutorialPageItemHandler.getTutorialPageItemAt(-1));
    }

    @Test
    void testGetTutorialPageItem_GivenTooBigIndex_ShouldThrowIndexOutOfBoundsException() {
        int itemCount = tutorialPageItemHandler.getTutorialPageItemCount();
        assertThrows(IndexOutOfBoundsException.class, () -> tutorialPageItemHandler.getTutorialPageItemAt(itemCount));
    }

    
}
