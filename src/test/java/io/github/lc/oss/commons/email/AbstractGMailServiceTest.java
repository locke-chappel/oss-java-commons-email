package io.github.lc.oss.commons.email;

import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.github.lc.oss.commons.testing.AbstractMockTest;
import jakarta.mail.internet.MimeMessage;

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

        @Override
        protected void transport(MimeMessage mail) {
        }
    }

    @Test
    public void test_fields() {
        AbstractGMailService gmail = new TestService();

        Assertions.assertNull(gmail.getUsername());
        Assertions.assertNull(gmail.getPassword());

        final Properties config = gmail.getConfiguration();
        Assertions.assertSame(config, gmail.getConfiguration());
    }
}
