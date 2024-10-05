package org.team6.soundsystem;

import org.team6.Main;
import org.team6.model.Notification;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.EnumMap;
import java.util.Map;

class SoundHandler {
    private final Map<Notification, Media> notificationSoundMap = new EnumMap<>(Notification.class);

    public SoundHandler() {
        for (Notification notification : Notification.values()) {
            String soundToPlay = Main.class.getResource("/org/team6/sounds/test.mp3").toExternalForm();
            Media sound = new Media(soundToPlay);
            notificationSoundMap.put(notification, sound);
        }
    }

    public void playSound(Notification notification) {
        Media sound = notificationSoundMap.get(notification);
        if (sound != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }
}
