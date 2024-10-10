package org.team6.tutorial;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TutorialPageItemHandler {
    private final List<TutorialPageItem> tutorialPageItems = new ArrayList<>();
    private static final String TEXT_FILE_PATH = "";
    private static final String IMAGE_FILE_PATH = "";
    private static final int ITEM_COUNT = 5;
    
    public TutorialPageItemHandler() {
        for (int i = 0; i < ITEM_COUNT; i++) {
            String itemText = TEXT_FILE_PATH;
            Image itemImage = new Image(IMAGE_FILE_PATH);
            TutorialPageItem itemToAdd = new TutorialPageItem(itemText, itemImage);
            tutorialPageItems.add(itemToAdd);
        }
    }

    public List<TutorialPageItem> getTutorialPageItems() {
        return tutorialPageItems;
    }
}
