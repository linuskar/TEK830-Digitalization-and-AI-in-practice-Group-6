package org.team6;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NotificationController {

    @FXML
    private TextArea notificationText;
    
    public void setNotificationText(String text){
        notificationText.setText(text);
    }

    public String getNotificationText(){
        return notificationText.getText();
    }

}
