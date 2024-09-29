package org.team6;

public class NotificationBackend {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static boolean turnOnTurnOffAllNotifications(User u) {
        return !u.notificationsOn;
    }

    public static void turnOnTurnOffASpecificNotification(User u, Notifications type) {
        if (u.notificationsOn) {
            switch (type) {
                case ONE -> u.notificationOne = !u.notificationOne;
                case TWO -> u.notificationTwo = !u.notificationTwo;
                case THREE -> u.notificationThree = !u.notificationThree;
                case FOUR -> u.notificationFour = !u.notificationFour;
                default -> throw new IllegalArgumentException("Notification" + type + "type not supported");
            }
        }
    }

    public static void changeSoundLevel(User u, Sound type) {
        u.soundLevel = type;
    }
}
