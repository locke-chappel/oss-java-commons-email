package com.github.lc.oss.commons.email;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractMockTest;

public class AbstractGMailServiceTest extends AbstractMockTest {
    private static class TestService extends AbstractGMailService {
        @Override
        protected String getUsername() {
            return null;
        }

        @Override
        protected String getPassword() {
            return null;
        }
    }

    @Test
    public void test_fields() {
        AbstractGMailService gmail = new TestService();

        Assertions.assertNull(gmail.getServerUrl());
        Assertions.assertNull(gmail.getUsername());
        Assertions.assertNull(gmail.getPassword());
    }

    @Test
    public void test_send_null() {
        AbstractGMailService gmail = new TestService();

        gmail.send(null);
    }

    @Test
    public void test_send_allFields() {
        AbstractGMailService gmail = new TestService();

        EmailMessage message = new EmailMessage();
        message.setFrom("from");
        message.setTo("to");
        message.setCc("cc");
        message.setBcc("bcc");
        message.setSubject("subject");
        message.setBody("body");
        message.setType(EmailTypes.PlainText);

        gmail.send(message);
    }
}
