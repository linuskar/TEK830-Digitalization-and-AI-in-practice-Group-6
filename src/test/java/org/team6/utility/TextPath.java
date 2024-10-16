package org.team6.utility;

import javax.print.DocFlavor.STRING;

public enum TextPath {
    ONEROWTXT("/org/team6/texts/oneRowTestText.txt"),
    MANYROWTXT("/org/team6/texts/manyRowTestText.txt"),
    ONEROWMD("/org/team6/texts/oneRowTestText.md"),
    MANYROWMD("/org/team6/texts/manyRowTestText.md");

    private final String path;

    TextPath(String path) {
        this.path = path;
    }

    public String getPath(){
        return path;
    }    
}
