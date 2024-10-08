package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
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
    void testSetVolume_GivenAValidVolume_ShouldChangeToIt(double volume) {
        NotificationBackend.setVolume(volume);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(volume, changedVolume);
    }

    @Test
    void testSetVolume_GivenTooLowVolume_ShouldChangeToZero() {
        NotificationBackend.setVolume(-1);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(0, changedVolume);
    }

    @Test
    void testSetVolume_GivenTooHighVolume_ShouldChangeToOne() {
        NotificationBackend.setVolume(2);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(1, changedVolume);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOnAndNotificationToTestIsOn_ShouldNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));

        NotificationListener listener = sentNotification -> assertTrue(true);
        sendNotificationSequence(notification, listener);
    }

    private static void sendNotificationSequence(Notification notification, NotificationListener listener) {
        NotificationBackend.addNotificationListener(listener);
        NotificationBackend.sendNotification(notification);
        NotificationBackend.removeNotificationListener(listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOnAndNotificationToTestIsOff_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        NotificationBackend.toggleASpecificNotification(notification);

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOffAndNotificationToTestIsOn_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        NotificationBackend.toggleAllNotifications();

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOffAndNotificationToTestIsOff_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        NotificationBackend.toggleAllNotifications();
        NotificationBackend.toggleASpecificNotification(notification);

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }
}
