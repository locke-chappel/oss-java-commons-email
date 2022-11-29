package com.github.lc.oss.commons.email;

import java.util.Properties;

public abstract class AbstractGMailService extends AbstractJakartaMailService {
    private static Properties SERVER_CONFIG = null;

    @Override
    protected Properties getConfiguration() {
        if (AbstractGMailService.SERVER_CONFIG == null) {
            Properties config = new Properties();
            config.put("mail.smtp.host", "smtp.gmail.com");
            config.put("mail.smtp.port", 587);
            config.put("mail.smtp.auth", "true");
            config.put("mail.smtp.starttls.enable", "true");
            AbstractGMailService.SERVER_CONFIG = config;
        }
        return AbstractGMailService.SERVER_CONFIG;
    }
}
