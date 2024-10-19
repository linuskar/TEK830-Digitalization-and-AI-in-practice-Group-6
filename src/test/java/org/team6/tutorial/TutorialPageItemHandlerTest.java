package org.team6.tutorial;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class TutorialPageItemHandlerTest {
    private TutorialPageItemHandler tutorialPageItemHandler;
    private static final List<Character> punctuationMarks = List.of('.', '!', '?');

    @BeforeEach
    void setUp() {
        tutorialPageItemHandler = new TutorialPageItemHandler();
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnATutorialPageItem() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertNotNull(tutorialPageItem, "TutorialPageItem should not be null");
        }
    }

    @Test
    void testGetTutorialPageItem_GivenNegativeIndex_ShouldThrowIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> tutorialPageItemHandler.getTutorialPageItemAt(-1),
                "IndexOutOfBoundsException should be thrown when index is negative");
    }

    @Test
    void testGetTutorialPageItem_GivenTooBigIndex_ShouldThrowIndexOutOfBoundsException() {
        int itemCount = tutorialPageItemHandler.getTutorialPageItemCount();
        assertThrows(IndexOutOfBoundsException.class,
                () -> tutorialPageItemHandler.getTutorialPageItemAt(itemCount),
                "IndexOutOfBoundsException should be thrown when index is too big");
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemsWithContent() {
        // Would like this to be parameterized, but it's not possible to pass the tutorialPageItemHandler as a parameter
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertNotNull(tutorialPageItem.infoText(), "TutorialPageItem should have text");
            assertNotNull(tutorialPageItem.infoImage(), "TutorialPageItem should have image");
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithNonEmptyText() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            assertFalse(tutorialPageItem.infoText().isEmpty(), "TutorialPageItem text should not be empty");
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithTextStartingWithCapitalLetter() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            boolean startsWithCapitalLetter = Character.isUpperCase(tutorialPageItem.infoText().charAt(0));
            assertTrue(startsWithCapitalLetter,
                    "TutorialPageItem text should start with capital letter. \nCurrent text:\n" + tutorialPageItem.infoText());
        }
    }

    @Test
    void testGetTutorialPageItem_GivenValidIndex_ShouldReturnTutorialPageItemWithTextEndingWithPunctuation() {
        for (int i = 0; i < tutorialPageItemHandler.getTutorialPageItemCount(); i++) {
            TutorialPageItem tutorialPageItem = tutorialPageItemHandler.getTutorialPageItemAt(i);
            char lastChar = tutorialPageItem.infoText().charAt(tutorialPageItem.infoText().length() - 1);
            assertTrue(punctuationMarks.contains(lastChar),
                    "TutorialPageItem text should end with punctuation. \nCurrent text:\n" + tutorialPageItem.infoText());
        }
    }

    @Test
    void testGetTutorialPageItemCount_ShouldBeGreaterThanZero() {
        int itemCount = tutorialPageItemHandler.getTutorialPageItemCount();
        assertTrue(itemCount > 0, "TutorialPageItemCount should be greater than zero");
    }
}
