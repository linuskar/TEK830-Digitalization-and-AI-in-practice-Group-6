package org.team6;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Sound soundLevel = Sound.VIBRATIONS;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sound getSoundLevel() {
        return soundLevel;
    }

    public void setSoundLevel(Sound soundLevel) {
        this.soundLevel = soundLevel;
    }

    public Boolean getNotificationsOn() {
        return notificationsOn;
    }

    public void setNotificationsOn(Boolean notificationsOn) {
        this.notificationsOn = notificationsOn;
    }

    public Boolean getNotificationOne() {
        return notificationOne;
    }

    public void setNotificationOne(Boolean notificationOne) {
        this.notificationOne = notificationOne;
    }

    public Boolean getNotificationTwo() {
        return notificationTwo;
    }

    public void setNotificationTwo(Boolean notificationTwo) {
        this.notificationTwo = notificationTwo;
    }

    public Boolean getNotificationThree() {
        return notificationThree;
    }

    public void setNotificationThree(Boolean notificationThree) {
        this.notificationThree = notificationThree;
    }

    public Boolean getNotificationFour() {
        return notificationFour;
    }

    public void setNotificationFour(Boolean notificationFour) {
        this.notificationFour = notificationFour;
    }

}
