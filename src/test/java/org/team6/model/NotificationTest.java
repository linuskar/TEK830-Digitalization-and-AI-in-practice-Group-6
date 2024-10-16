package org.team6.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class NotificationTest {
    @ParameterizedTest
    @EnumSource(Notification.class)
    void testNotificationEnum_StringStartAllCaps(Notification notification) {
        String testNotificationText = Notification.getText(notification);
        assertTrue(Character.isUpperCase(testNotificationText.charAt(0)));
    }
}
