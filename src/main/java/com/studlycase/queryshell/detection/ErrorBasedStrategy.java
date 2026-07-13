package com.studlycase.queryshell.detection;

import com.studlycase.queryshell.payloads.PayloadRepository;

import java.sql.Connection;

public final class ErrorBasedStrategy implements InjectionTestStrategy {

    private final PayloadRepository payloadRepository;

    public ErrorBasedStrategy(PayloadRepository payloadRepository) {
        this.payloadRepository = payloadRepository;
    }

    @Override
    public TestResult evaluate(Connection connection, String targetParameter) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public String name() {
        return "error-based";
    }
}
