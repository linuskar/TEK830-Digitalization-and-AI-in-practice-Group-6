package org.team6.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationBackendTest {
    private User userUnderTest;

    @BeforeEach
    public void setUp() {
        userUnderTest = new User("Bob", "Bobsson", "bob.bobsson@email.com", "Bob123");
    }

    @Test
    void testTurnOnTurnOffAllNotifications() {
        NotificationBackend.turnOnTurnOffAllNotifications(userUnderTest);
        assertFalse(userUnderTest.areNotificationsOn());
        NotificationBackend.turnOnTurnOffAllNotifications(userUnderTest);
        assertTrue(userUnderTest.areNotificationsOn());
    }
}
