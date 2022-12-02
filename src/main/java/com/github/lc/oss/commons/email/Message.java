package com.github.lc.oss.commons.email;

import java.util.Set;

public interface Message {
    String getFrom();

    Set<String> getTo();

    Set<String> getCc();

    Set<String> getBcc();

    String getSubject();

    String getBody();

    EmailTypes getType();
}
