package org.team6.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class NotificationTest {
    @ParameterizedTest
    @EnumSource(Notification.class)
    void testNotificationEnum_StringStartAllCaps(Notification notification) {
        String testNotificationText = Notification.getText(notification);
        boolean startsWithAllCaps = Character.isUpperCase(testNotificationText.charAt(0));
        assertTrue(startsWithAllCaps, "Notification text should start with a capital letter.");
    }
}
