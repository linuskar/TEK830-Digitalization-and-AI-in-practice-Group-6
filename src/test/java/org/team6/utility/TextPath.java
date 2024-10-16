package org.team6.utility;

public enum TextPath {
    ONE_ROW_TXT("/org/team6/texts/oneRowTestText.txt"),
    MANY_ROW_TXT("/org/team6/texts/manyRowTestText.txt"),
    ONE_ROW_MD("/org/team6/texts/oneRowTestText.md"),
    MANY_ROW_MD("/org/team6/texts/manyRowTestText.md");

    private final String path;

    TextPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }    
}
