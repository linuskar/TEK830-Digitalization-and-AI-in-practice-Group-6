package org.team6.tutorial;

import javafx.scene.image.Image;
import org.team6.utility.TextFileReader;

import java.util.ArrayList;
import java.util.List;

public class TutorialPageItemHandler {
    private final List<TutorialPageItem> tutorialPageItems = new ArrayList<>();
    private static final String TEXT_FILE_PATH = "org/team6/texts/";
    private static final String IMAGE_FILE_PATH = "org/team6/images/tutorial";
    private static final int ITEM_COUNT = 5;

    public TutorialPageItemHandler() {
        for (int i = 0; i < ITEM_COUNT; i++) {
            String textFileName = "info" + i + ".md";
            String itemText = TextFileReader.readFileAsString(TEXT_FILE_PATH + textFileName);

            String imageFileName = "info" + i + ".png";
            Image itemImage = new Image(IMAGE_FILE_PATH + imageFileName);

            TutorialPageItem itemToAdd = new TutorialPageItem(itemText, itemImage);
            tutorialPageItems.add(itemToAdd);
        }
    }

    public List<TutorialPageItem> getTutorialPageItems() {
        return tutorialPageItems;
    }
}
