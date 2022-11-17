package com.github.lc.oss.commons.email;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmailMessage {
    private String from;
    private Set<String> to;
    private Set<String> cc;
    private Set<String> bcc;
    private String subject;
    private String body;
    private EmailTypes type = EmailTypes.HTML;

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Set<String> getTo() {
        return this.to;
    }

    public void setTo(String... to) {
        this.setTo(new HashSet<>(Arrays.asList(to)));
    }

    public void setTo(Set<String> to) {
        this.to = to;
    }

    public Set<String> getCc() {
        return this.cc;
    }

    public void setCc(String... cc) {
        this.setCc(new HashSet<>(Arrays.asList(cc)));
    }

    public void setCc(Set<String> cc) {
        this.cc = cc;
    }

    public Set<String> getBcc() {
        return this.bcc;
    }

    public void setBcc(String... bcc) {
        this.setBcc(new HashSet<>(Arrays.asList(bcc)));
    }

    public void setBcc(Set<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailTypes getType() {
        return this.type;
    }

    public void setType(EmailTypes type) {
        this.type = type;
    }
}
