package org.team6;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    String soundLevel = "VIBRATIONS";
    Boolean notificationsOn = true;
    Boolean notificationOne = true;
    Boolean notificationTwo = true;
    Boolean notificationThree = true;
    Boolean notificationFour = true;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
