package com.github.lc.oss.commons.email;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public abstract class AbstractJakartaMailService extends AbstractEmailService {
    protected abstract Properties getConfiguration();

    protected Session newSession() {
        return Session.getInstance(this.getConfiguration(), this.newAuthenticator());
    }

    protected Authenticator newAuthenticator() {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(AbstractJakartaMailService.this.getUsername(), AbstractJakartaMailService.this.getPassword());
            }
        };
    }

    @Override
    public void send(Message message) {
        Session session = this.newSession();
        try {
            MimeMessage mail = new MimeMessage(session);

            if (this.isNotBlank(message.getFrom())) {
                /*
                 * Note: Note all mail services permit setting this value and may ignore it.
                 */
                mail.setFrom(new InternetAddress(message.getFrom()));
            }

            if (this.isNotBlank(message.getTo())) {
                for (String to : message.getTo()) {
                    mail.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
                }
            }

            if (this.isNotBlank(message.getCc())) {
                for (String cc : message.getCc()) {
                    mail.addRecipient(jakarta.mail.Message.RecipientType.CC, new InternetAddress(cc));
                }
            }

            if (this.isNotBlank(message.getBcc())) {
                for (String bcc : message.getBcc()) {
                    mail.addRecipient(jakarta.mail.Message.RecipientType.BCC, new InternetAddress(bcc));
                }
            }

            if (this.isNotBlank(message.getSubject())) {
                mail.setSubject(message.getSubject());
            }

            if (this.isNotBlank(message.getBody())) {
                mail.setContent(message.getBody(), message.getType().getMime());
            }

            this.transport(mail);
        } catch (Exception ex) {
            throw new RuntimeException("Error sending email", ex);
        }
    }

    protected void transport(MimeMessage mail) {
        try {
            Transport.send(mail);
        } catch (MessagingException ex) {
            throw new RuntimeException("Error transporting email", ex);
        }
    }
}
