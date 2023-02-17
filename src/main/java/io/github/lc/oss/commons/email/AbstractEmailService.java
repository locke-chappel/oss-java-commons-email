package io.github.lc.oss.commons.email;

import java.util.Collection;

public abstract class AbstractEmailService implements EmailService {
    protected abstract String getUsername();

    protected abstract String getPassword();

    protected boolean isNotBlank(Object value) {
        if (value == null) {
            return false;
        }

        if (value instanceof String) {
            return !((String) value).trim().equals("");
        }

        if (value instanceof Collection) {
            return !((Collection<?>) value).isEmpty();
        }

        return true;
    }
}
