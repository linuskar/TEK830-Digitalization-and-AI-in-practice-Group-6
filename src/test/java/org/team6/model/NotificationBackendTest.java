package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class NotificationBackendTest {
    private User userUnderTest;
    private NotificationBackend backendUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User("Bob", "Bobsson", "bob.bobsson@email.com", "Bob123");
        backendUnderTest = new NotificationBackend(userUnderTest);
    }

    @Test
    void testToggleAllNotifications_GivenToggledOnce_ShouldHaveOppositeValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        userUnderTest.toggleNotifications();
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @Test
    void testToggleAllNotifications_GivenToggledTwice_ShouldHaveSameValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        userUnderTest.toggleNotifications();
        userUnderTest.toggleNotifications();
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledOnceAndNotificationsAreOn_ShouldHaveOppositeValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        userUnderTest.toggleSpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledTwiceAndNotificationsAreOn_ShouldHaveSameValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        userUnderTest.toggleSpecificNotification(notification);
        userUnderTest.toggleSpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledOnceAndNotificationsAreOff_ShouldHaveSameValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        userUnderTest.toggleNotifications();
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        userUnderTest.toggleSpecificNotification(notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0})
    void testSetVolume_GivenAValidVolume_ShouldChangeToIt(double volume) {
        userUnderTest.setVolume(volume);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(volume, changedVolume);
    }

    @Test
    void testSetVolume_GivenTooLowVolume_ShouldChangeToZero() {
        userUnderTest.setVolume(-1);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(0, changedVolume);
    }

    @Test
    void testSetVolume_GivenTooHighVolume_ShouldChangeToOne() {
        userUnderTest.setVolume(2);
        double changedVolume = userUnderTest.getVolume();

        assertEquals(1, changedVolume);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOnAndNotificationToTestIsOn_ShouldNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        extraConditionForDailyReport(notification);

        NotificationListener listener = sentNotification -> assertTrue(true);
        sendNotificationSequence(notification, listener);
    }

    private void extraConditionForDailyReport(Notification notification) {
        if (notification == Notification.DAILY_REPORT) {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            if (userUnderTest.getDailyReportTime() != currentHour) {
                userUnderTest.setDailyReportTime(currentHour);
            }
        }
    }

    private void sendNotificationSequence(Notification notification, NotificationListener listener) {
        backendUnderTest.addNotificationListener(listener);
        backendUnderTest.sendNotification(notification);
        backendUnderTest.removeNotificationListener(listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOnAndNotificationToTestIsOff_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        userUnderTest.toggleSpecificNotification(notification);

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOffAndNotificationToTestIsOn_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        userUnderTest.toggleNotifications();

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testSendNotification_GivenNotificationsAreOffAndNotificationToTestIsOff_ShouldNotNotifyListener(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        assumeTrue(userUnderTest.isNotificationOn(notification));
        userUnderTest.toggleNotifications();
        userUnderTest.toggleSpecificNotification(notification);

        NotificationListener listener = sentNotification -> fail();
        sendNotificationSequence(notification, listener);
    }
}
