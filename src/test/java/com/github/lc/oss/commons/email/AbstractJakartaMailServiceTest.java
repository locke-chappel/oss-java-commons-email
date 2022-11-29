package com.github.lc.oss.commons.email;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.lc.oss.commons.testing.AbstractMockTest;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;

public class AbstractJakartaMailServiceTest extends AbstractMockTest {
    private static class TestService extends AbstractJakartaMailService {
        @Override
        protected String getUsername() {
            return "JUnit";
        }

        @Override
        protected String getPassword() {
            return "password";
        }

        @Override
        protected void transport(MimeMessage mail) {
        }

        @Override
        protected Properties getConfiguration() {
            Properties config = new Properties();
            config.put("mail.smtp.host", "localhost");
            config.put("mail.smtp.port", "123456");
            config.put("mail.smtp.auth", "true");
            config.put("mail.smtp.starttls.enable", "true");
            return config;
        }
    }

    @Test
    public void test_newSession() {
        AbstractJakartaMailService service = new TestService();

        Session session1 = service.newSession();
        Session session2 = service.newSession();
        Assertions.assertNotNull(session1);
        Assertions.assertNotNull(session2);
        Assertions.assertNotSame(session1, session2);
    }

    @Test
    public void test_newAuthenticator() {
        AbstractJakartaMailService service = new TestService();

        Authenticator auth1 = service.newAuthenticator();
        Authenticator auth2 = service.newAuthenticator();
        Assertions.assertNotNull(auth1);
        Assertions.assertNotNull(auth2);
        Assertions.assertNotSame(auth1, auth2);

        Method getPasswordAuthentication = this.findMethod("getPasswordAuthentication", Authenticator.class);
        boolean canAccess = getPasswordAuthentication.canAccess(auth1);
        try {
            getPasswordAuthentication.setAccessible(true);
            PasswordAuthentication result = (PasswordAuthentication) getPasswordAuthentication.invoke(auth1);
            Assertions.assertEquals("JUnit", result.getUserName());
            Assertions.assertEquals("password", result.getPassword());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Assertions.fail("Unexpected exception", ex);
        } finally {
            getPasswordAuthentication.setAccessible(canAccess);
        }
    }

    @Test
    public void test_send_null() {
        AbstractJakartaMailService service = new TestService();

        service.transport(null);
    }

    @Test
    public void test_send_allFields() {
        AbstractJakartaMailService service = new TestService();

        EmailMessage message = new EmailMessage();
        message.setFrom("from");
        message.setTo("to");
        message.setCc("cc");
        message.setBcc("bcc");
        message.setSubject("subject");
        message.setBody("body");
        message.setType(EmailTypes.PlainText);

        service.send(message);
    }

    @Test
    public void test_send_error() {
        AbstractJakartaMailService service = new TestService() {
            @Override
            protected void transport(MimeMessage mail) {
                throw new RuntimeException("Boom!");
            }
        };

        EmailMessage message = new EmailMessage();

        try {
            service.send(message);
            Assertions.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assertions.assertEquals("Error sending email", ex.getMessage());
        }
    }

    @Test
    public void test_transport_error() {
        AbstractJakartaMailService gmail = new AbstractJakartaMailService() {
            @Override
            protected String getUsername() {
                return null;
            }

            @Override
            protected String getPassword() {
                return null;
            }

            @Override
            protected Properties getConfiguration() {
                return null;
            }
        };

        MimeMessage message = new MimeMessage((Session) null) {
            @Override
            public void saveChanges() throws MessagingException {
                throw new MessagingException("Boom!");
            }
        };

        try {
            gmail.transport(message);
            Assertions.fail("Expected exception");
        } catch (RuntimeException ex) {
            Assertions.assertEquals("Error transporting email", ex.getMessage());
        }
    }
}
