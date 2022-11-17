package com.github.lc.oss.commons.email;

import java.util.Set;

import com.github.lc.oss.commons.util.TypedEnumCache;

public enum EmailTypes {
    PlainText("text/plain"),
    HTML("text/html");

    private static final TypedEnumCache<EmailTypes, EmailTypes> CACHE = new TypedEnumCache<>(EmailTypes.class, false);

    public static final Set<EmailTypes> all() {
        return EmailTypes.CACHE.values();
    }

    public static EmailTypes byName(String name) {
        return EmailTypes.CACHE.byName(name);
    }

    public static boolean hasName(String name) {
        return EmailTypes.CACHE.hasName(name);
    }

    public static EmailTypes tryParse(String name) {
        return EmailTypes.CACHE.tryParse(name);
    }

    private final String mime;

    private EmailTypes(String mime) {
        this.mime = mime;
    }

    public String getMime() {
        return this.mime;
    }
}
