package org.team6.utility;

public enum TextPath {
    ONE_ROW_TXT("/org/team6/texts/one_row_test_text.txt"),
    MANY_ROW_TXT("/org/team6/texts/many_row_test_text.txt"),
    ONE_ROW_MD("/org/team6/texts/one_row_test_text.md"),
    MANY_ROW_MD("/org/team6/texts/many_row_test_text.md");

    private final String path;

    TextPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }    
}
