package org.team6;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    String soundLevel;
    Boolean notificationsOn;
    Boolean notificationOne;
    Boolean notificationTwo;
    Boolean notificationThree;
    Boolean notificationFour;

    public User(String fn, String ln, String email, String password) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = email;
        this.password = password;
        this.notificationsOn = true;
        this.notificationOne = true;
        this.notificationTwo = true;
        this.notificationThree = true;
        this.notificationFour = true;
        this.soundLevel = "VIBRATIONS";
    }
}
