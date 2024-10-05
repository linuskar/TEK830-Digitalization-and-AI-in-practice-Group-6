package org.team6.soundsystem;

import org.team6.Main;
import org.team6.model.Notification;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

class SoundHandler {
    private final Map<Notification, Media> notificationSoundMap = new EnumMap<>(Notification.class);
    private static final String SOUND_PATH = "/org/team6/sounds/test.mp3";

    public SoundHandler() {
        for (Notification notification : Notification.values()) {
            Media sound = getSoundFromPath(SOUND_PATH);
            notificationSoundMap.put(notification, sound);
        }
    }

    private static Media getSoundFromPath(String soundPath) {
        URL soundUrl = Objects.requireNonNull(Main.class.getResource(soundPath));
        String soundToPlay = soundUrl.toExternalForm();
        return new Media(soundToPlay);
    }

    public void playSound(Notification notification) {
        Media sound = notificationSoundMap.get(notification);
        if (sound != null) {
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }
    }
}
