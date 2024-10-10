package org.team6.tutorial;

import javafx.scene.image.Image;
import org.team6.utility.TextFileReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TutorialPageItemHandler {
    private final List<TutorialPageItem> tutorialPageItems = new ArrayList<>();
    private static final String TEXT_FILE_PATH = "/org/team6/texts/";
    private static final String IMAGE_FILE_PATH = "/org/team6/images/tutorial/";
    private static final int ITEM_COUNT = 5;

    public TutorialPageItemHandler() {
        for (int i = 0; i < ITEM_COUNT; i++) {
            String textFileName = "info" + i + ".md";
            String itemText = TextFileReader.readFileAsString(TEXT_FILE_PATH + textFileName);

            String imageFileName = "info" + i + ".png";
            String imageFileString = getFileString(IMAGE_FILE_PATH + imageFileName);

            Image itemImage = new Image(imageFileString);

            TutorialPageItem itemToAdd = new TutorialPageItem(itemText, itemImage);
            tutorialPageItems.add(itemToAdd);
        }
    }

    private static String getFileString(String textFileName) {
        URL fileUrl = TutorialPageItemHandler.class.getResource(textFileName);
        return Objects.requireNonNull(fileUrl).toExternalForm();
    }

    public TutorialPageItem getTutorialPageItemAt(int index) {
        return tutorialPageItems.get(index);
    }

    public int getTutorialPageItemCount() {
        return tutorialPageItems.size();
    }
}
