package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class NotificationBackendTest {
    private User userUnderTest;

    @BeforeEach
    public void setUp() {
        userUnderTest = new User("Bob", "Bobsson", "bob.bobsson@email.com", "Bob123");
    }

    @Test
    void testToggleAllNotifications_GivenToggledOnce_ShouldHaveOppositeValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        NotificationBackend.toggleAllNotifications(userUnderTest);
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @Test
    void testToggleAllNotifications_GivenToggledTwice_ShouldHaveSameValue() {
        boolean initialNotificationValue = userUnderTest.areNotificationsOn();
        NotificationBackend.toggleAllNotifications(userUnderTest);
        NotificationBackend.toggleAllNotifications(userUnderTest);
        boolean finalNotificationValue = userUnderTest.areNotificationsOn();

        assertEquals(initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledOnceAndNotificationsAreOn_ShouldHaveOppositeValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        NotificationBackend.toggleASpecificNotification(userUnderTest, notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(!initialNotificationValue, finalNotificationValue);
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void testToggleASpecificNotification_GivenToggledTwiceAndNotificationsAreOn_ShouldHaveSameValue(Notification notification) {
        assumeTrue(userUnderTest.areNotificationsOn());
        boolean initialNotificationValue = userUnderTest.isNotificationOn(notification);
        NotificationBackend.toggleASpecificNotification(userUnderTest, notification);
        NotificationBackend.toggleASpecificNotification(userUnderTest, notification);
        boolean finalNotificationValue = userUnderTest.isNotificationOn(notification);

        assertEquals(initialNotificationValue, finalNotificationValue);
    }
}
