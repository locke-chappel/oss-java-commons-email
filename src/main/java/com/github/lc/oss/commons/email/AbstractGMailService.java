package com.github.lc.oss.commons.email;

public abstract class AbstractGMailService extends AbstractEmailService {
    @Override
    protected String getServerUrl() {
        return null;
    }

    @Override
    public void send(EmailMessage message) {

    }
}
