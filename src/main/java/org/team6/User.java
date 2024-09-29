package org.team6;

public class User {
    String firstName;
    String lastName;
    String email;
    String password;
    String soundLevel = "VIBRATIONS";
    Boolean notificationsOn = true;
    Boolean notificationOne = true;
    Boolean notificationTwo = true;
    Boolean notificationThree = true;
    Boolean notificationFour = true;

    public User(String fn, String ln, String email, String password) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = email;
        this.password = password;
    }
}
