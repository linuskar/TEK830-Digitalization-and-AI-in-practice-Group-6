package org.team6;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class NotificationController {

    @FXML
    private TextArea notificationText;
    
    public void SetNotificationText(String text){
        notificationText.setText(text);
    }

    public String GetNotificationText(){
        return notificationText.getText();
    }

}
