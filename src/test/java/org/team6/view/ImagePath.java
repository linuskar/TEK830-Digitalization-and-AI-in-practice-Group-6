package org.team6.view;

import javafx.scene.image.Image;

public enum ImagePath {
    NORMAL_NORMAL("/org/team6/images/8x8.png"),
    WIDE_NORMAL("/org/team6/images/16x8.png"),
    NORMAL_TALL("/org/team6/images/8x16.png"),
    WIDE_TALL("/org/team6/images/16x16.png"),
    THIN_NORMAL("/org/team6/images/4x8.png"),
    NORMAL_SHORT("/org/team6/images/8x4.png"),
    THIN_SHORT("/org/team6/images/4x4.png"),
    THIN_TALL("/org/team6/images/4x16.png"),
    WIDE_SHORT("/org/team6/images/16x4.png");

    private final String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public Image getImage() {
        return new Image(ImageUtils.getFileResourceString(path));
    }
}
