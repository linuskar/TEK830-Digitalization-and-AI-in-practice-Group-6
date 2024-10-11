package org.team6.tutorial;

import javafx.scene.image.Image;
import org.team6.utility.TextFileReader;
import org.team6.view.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TutorialPageItemHandler {
    private final List<TutorialPageItem> tutorialPageItems = new ArrayList<>();
    private static final String TEXT_FILE_PATH = "/org/team6/texts/";
    private static final String IMAGE_FILE_PATH = "/org/team6/images/tutorial/";

    public TutorialPageItemHandler() {
        File[] textFiles = getTextFiles(TEXT_FILE_PATH, "info\\d+\\.md");
        File[] imageFiles = getTextFiles(IMAGE_FILE_PATH, "info\\d+\\.png");

        if (textFiles != null && imageFiles != null) {
            for (File textFile : textFiles) {
                String textFileName = textFile.getName();
                String itemText = TextFileReader.readFileAsString(TEXT_FILE_PATH + textFileName);

                String imageFileName = textFileName.replace(".md", ".png");
                String imageFileString = ImageUtils.getFileResourceString(IMAGE_FILE_PATH + imageFileName);

                Image itemImage = new Image(imageFileString);

                TutorialPageItem itemToAdd = new TutorialPageItem(itemText, itemImage);
                tutorialPageItems.add(itemToAdd);
            }
        }
    }

    private File[] getTextFiles(String filePath, String fileNameRegex) {
        File fileDir = new File(getClass().getResource(filePath).getFile());
        return fileDir.listFiles((dir, name) -> name.matches(fileNameRegex));
    }

    public TutorialPageItem getTutorialPageItemAt(int index) {
        return tutorialPageItems.get(index);
    }

    public int getTutorialPageItemCount() {
        return tutorialPageItems.size();
    }
}
