package com.studlycase.queryshell.payloads;

public final class Payload {

    private final String strategyName;
    private final String template;
    private final String description;

    public Payload(String strategyName, String template, String description) {
        this.strategyName = strategyName;
        this.template = template;
        this.description = description;
    }

    public String strategyName() {
        return strategyName;
    }

    public String template() {
        return template;
    }

    public String description() {
        return description;
    }
}
