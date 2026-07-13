package com.studlycase.queryshell.detection;

public final class TestResult {

    private final String strategyName;
    private final boolean vulnerable;
    private final String detail;

    public TestResult(String strategyName, boolean vulnerable, String detail) {
        this.strategyName = strategyName;
        this.vulnerable = vulnerable;
        this.detail = detail;
    }

    public String strategyName() {
        return strategyName;
    }

    public boolean vulnerable() {
        return vulnerable;
    }

    public String detail() {
        return detail;
    }
}
