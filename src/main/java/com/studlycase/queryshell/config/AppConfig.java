package com.studlycase.queryshell.config;

public final class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    private int connectionTimeoutMs = 5000;
    private int threadPoolSize = 4;
    private boolean verbose = false;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public int connectionTimeoutMs() {
        return connectionTimeoutMs;
    }

    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }

    public int threadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public boolean verbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
