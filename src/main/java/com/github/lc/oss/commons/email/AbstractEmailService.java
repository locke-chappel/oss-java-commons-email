package com.github.lc.oss.commons.email;

public abstract class AbstractEmailService implements EmailService {
    protected abstract String getServerUrl();

    protected abstract String getUsername();

    protected abstract String getPassword();
}
