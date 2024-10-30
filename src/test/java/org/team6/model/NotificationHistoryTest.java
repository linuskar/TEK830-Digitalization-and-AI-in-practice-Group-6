package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationHistoryTest {
    private static final int MAX_NOTIFICATION_AMOUNT = 5;
    private NotificationHistory historyUnderTest;

    @BeforeEach
    void setUp() {
        historyUnderTest = new NotificationHistory();
    }

    @Test
    void NotificationHistory_GivenNoNotifications_HasEmptyList() {
        assertTrue(historyUnderTest.getNotificationList().isEmpty());
    }

    @Test
    void addNotification_GivenANotification_ShouldNotHaveEmptyList() {
        historyUnderTest.addNotification(Notification.LOW_ELECTRICITY_PRICE);
        assertFalse(historyUnderTest.getNotificationList().isEmpty());
    }

    @ParameterizedTest
    @EnumSource(Notification.class)
    void addNotification_GivenNotification_ShouldAddToHistory(Notification notification) {
        historyUnderTest.addNotification(notification);
        assertTrue(historyUnderTest.getNotificationList().contains(notification));
    }

    // Going to create tests for the max amount which is internally set to 5, perhaps it should have been a parameter in the constructor.
    @Test
    void addNotification_GivenMoreThanMaxAmountOfNotifications_ShouldHaveMaxAmountOfNotifications() {
        for (int i = 0; i < MAX_NOTIFICATION_AMOUNT+1; i++) {
            historyUnderTest.addNotification(Notification.LOW_ELECTRICITY_PRICE);
        }
        assertEquals(MAX_NOTIFICATION_AMOUNT, historyUnderTest.getNotificationList().size());
    }

    @Test
    void addNotification_GivenMoreThanMaxAmountOfNotifications_ShouldRemoveOldestNotification() {
        historyUnderTest.addNotification((Notification.HIGH_ELECTRICITY_PRICE));
        for (int i = 0; i < MAX_NOTIFICATION_AMOUNT; i++) {
            historyUnderTest.addNotification(Notification.LOW_ELECTRICITY_PRICE);
        }
        assertFalse(historyUnderTest.getNotificationList().contains(Notification.HIGH_ELECTRICITY_PRICE));
    }

    @Test
    void addNotification_GivenMoreThanMaxAmountOfNotifications_ShouldAddNewNotification() {
        for (int i = 0; i < MAX_NOTIFICATION_AMOUNT; i++) {
            historyUnderTest.addNotification(Notification.LOW_ELECTRICITY_PRICE);
        }
        historyUnderTest.addNotification(Notification.HIGH_ELECTRICITY_PRICE);
        assertTrue(historyUnderTest.getNotificationList().contains(Notification.HIGH_ELECTRICITY_PRICE));
    }

    @Test
    void getNotificationList_GivenNotifications_ShouldReturnInReverseOrder() {
        for (Notification notification : Notification.values()) {
            historyUnderTest.addNotification(notification);
        }

        int lastElementIndex = Notification.values().length - 1;
        for (int i = 0; i < Notification.values().length; i++) {
            Notification actualNotification = historyUnderTest.getNotificationList().get(lastElementIndex - i);
            Notification expectedNotification = Notification.values()[i];

            assertEquals(expectedNotification, actualNotification);
        }
    }
}
