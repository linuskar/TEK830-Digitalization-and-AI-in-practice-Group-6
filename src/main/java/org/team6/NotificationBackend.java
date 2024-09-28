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
                case ONE:
                    u.notificationOne = !u.notificationOne;
                    break;
                case TWO:
                    u.notificationTwo = !u.notificationTwo;
                    break;
                case THREE:
                    u.notificationThree = !u.notificationThree;
                    break;
                case FOUR:
                    u.notificationFour = !u.notificationFour;
                    break;
                default:
                    break;
            }
        }
    }

    public static void changeSoundLevel(User u, Sound type) {
        switch (type) {
            case VIBRATIONS:
                u.soundLevel = "VIBRATIONS";
                break;
            case LOW:
                u.soundLevel = "LOW";
                break;
            case MEDIUM:
                u.soundLevel = "MEDIUM";
                break;
            case HIGH:
                u.soundLevel = "HIGH";
                break;
            default:
                break;
        }
    }
}
