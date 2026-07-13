package com.studlycase.queryshell.detection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public final class DetectionEngine {

    private final List<InjectionTestStrategy> strategies = new ArrayList<>();

    public void register(InjectionTestStrategy strategy) {
        strategies.add(strategy);
    }

    public List<TestResult> runAll(Connection connection, String targetParameter) {
        List<TestResult> results = new ArrayList<>();
        for (InjectionTestStrategy strategy : strategies) {
            results.add(strategy.evaluate(connection, targetParameter));
        }
        return results;
    }
}
