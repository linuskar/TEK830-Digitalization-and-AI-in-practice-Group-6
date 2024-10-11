package org.team6.tutorial;

import javafx.scene.image.Image;
import org.team6.utility.TextFileReader;
import org.team6.view.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TutorialPageItemHandler {
    private static final Logger logger = Logger.getLogger(TutorialPageItemHandler.class.getName());

    private final List<TutorialPageItem> tutorialPageItems = new ArrayList<>();
    private static final String TEXT_FILE_PATH = "/org/team6/texts/";
    private static final String IMAGE_FILE_PATH = "/org/team6/images/tutorial/";

    public TutorialPageItemHandler() {
        File[] textFiles = getFiles(TEXT_FILE_PATH, "info\\d+\\.md");
        File[] imageFiles = getFiles(IMAGE_FILE_PATH, "info\\d+\\.png");
        if (textFiles == null || imageFiles == null) {
            return;
        }

        for (int i = 0; i < textFiles.length; i++) {
            String textFileName = textFiles[i].getName();
            String itemText = TextFileReader.readFileAsString(TEXT_FILE_PATH + textFileName);

            String imageFileName = imageFiles[i].getName();
            String imageFileString = ImageUtils.getFileResourceString(IMAGE_FILE_PATH + imageFileName);

            Image itemImage = new Image(imageFileString);

            TutorialPageItem itemToAdd = new TutorialPageItem(itemText, itemImage);
            tutorialPageItems.add(itemToAdd);
        }
    }

    private File[] getFiles(String filePath, String fileNameRegex) {
        File fileDir = new File(Objects.requireNonNull(getClass().getResource(filePath)).getFile());
        File[] files = fileDir.listFiles((dir, name) -> name.matches(fileNameRegex));
        if (files == null) {
            logger.log(Level.SEVERE, () -> "No files found in: " + filePath);
        }
        return files;
    }

    public TutorialPageItem getTutorialPageItemAt(int index) {
        return tutorialPageItems.get(index);
    }

    public int getTutorialPageItemCount() {
        return tutorialPageItems.size();
    }
}
