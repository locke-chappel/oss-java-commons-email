package com.github.lc.oss.commons.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractMockTest;

public class EmailMessageTest extends AbstractMockTest {
    @Test
    public void test_fields() {
        EmailMessage message = new EmailMessage();

        Assertions.assertNull(message.getFrom());
        Assertions.assertNull(message.getTo());
        Assertions.assertNull(message.getCc());
        Assertions.assertNull(message.getBcc());
        Assertions.assertNull(message.getSubject());
        Assertions.assertNull(message.getBody());
        Assertions.assertEquals(EmailTypes.HTML, message.getType());
    }

    @Test
    public void test_errors() {
        EmailMessage message = new EmailMessage();
        try {
            message.setType(null);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("Email type cannot be null", ex.getMessage());
        }
    }
}
