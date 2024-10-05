package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class NotificationBackendTest {
    private User userUnderTest;

    @BeforeEach
    public void setUp() {
        userUnderTest = new User("Bob", "Bobsson", "bob.bobsson@email.com", "Bob123");
        NotificationBackend.setUser(userUnderTest);
    }

    @Test
    void testToggleAllNotifications_GivenToggledOnce_ShouldHaveOppositeValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        NotificationBackend.toggleAllNotifications();
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @Test
    void testToggleAllNotifications_GivenToggledTwice_ShouldHaveSameValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        NotificationBackend.toggleAllNotifications();
        NotificationBackend.toggleAllNotifications();
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledOnceAndNotificationsAreOn_ShouldHaveOppositeValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        NotificationBackend.toggleASpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledTwiceAndNotificationsAreOn_ShouldHaveSameValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        NotificationBackend.toggleASpecificNotification(notification);
        NotificationBackend.toggleASpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledOnceAndNotificationsAreOff_ShouldHaveSameValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        NotificationBackend.toggleAllNotifications();
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        NotificationBackend.toggleASpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0})
    void testChangeSoundLevel_GivenAValidVolume_ShouldChangeToIt(double volume) {
        NotificationBackend.changeVolume(volume);
        double changedSoundLevel = userUnderTest.getVolume();

        assertEquals(volume, changedSoundLevel);
    }
}
