package org.team6.tutorial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemsWithContent() {
        // Would like this to be parameterized, but it's not possible to pass the tutorialPageItemHandler as a parameter
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertNotNull(tutorialPageItem.infoText());
            assertNotNull(tutorialPageItem.infoImage());
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithNonEmptyText() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertFalse(tutorialPageItem.infoText().isEmpty());
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithTextStartingWithCapitalLetter() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertTrue(Character.isUpperCase(tutorialPageItem.infoText().charAt(0)));
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithTextEndingWithPunctuation() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            char lastChar = tutorialPageItem.infoText().charAt(tutorialPageItem.infoText().length() - 1);
            assertTrue(lastChar == '.' || lastChar == '!' || lastChar == '?');
        }
    }

    @Test
    void testGetTutorialPageItemCount_ShouldBeGreaterThanZero() {
        int itemCount = tutorialPageItemHandler.getTutorialPageItemCount();
        assertTrue(itemCount > 0);
    }



}
