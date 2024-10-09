package org.team6.soundsystem;

import org.team6.model.Notification;

public class SoundPlayer {
    private static final SoundHandler soundHandler = new SoundHandler();

    private SoundPlayer() {}

    public static void playSound(Notification notification) {
        soundHandler.playSound(notification);
    }
}
