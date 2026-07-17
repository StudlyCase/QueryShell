package com.studlycase.queryshell.report;

import com.studlycase.queryshell.detection.TestResult;

import java.util.List;

public final class CsvReportWriter implements ReportGenerator {

    @Override
    public void write(List<TestResult> results) {
        System.out.println("strategy,vulnerable,detail");
        for (TestResult result : results) {
            System.out.printf("%s,%b,%s%n", result.strategyName(), result.vulnerable(), result.detail());
        }
    }
}
