package org.team6.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UrlOpener {
    private UrlOpener() {}

    // Taken from https://gist.github.com/GiriB/b79f47e4d970dec887a7
    public static void openUrl(String url){
        Desktop desktop = Desktop.getDesktop();
        try {
            //specify the protocol along with the URL
            URI oURL = new URI(url);
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
