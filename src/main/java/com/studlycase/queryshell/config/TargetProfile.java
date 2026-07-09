package com.studlycase.queryshell.config;

public final class TargetProfile {

    private final String host;
    private final int port;
    private final String user;

    public TargetProfile(String host, int port, String user) {
        this.host = host;
        this.port = port;
        this.user = user;
    }

    public String host() {
        return host;
    }

    public int port() {
        return port;
    }

    public String user() {
        return user;
    }
}
