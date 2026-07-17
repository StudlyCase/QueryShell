package com.studlycase.queryshell.report;

import com.studlycase.queryshell.detection.TestResult;

import java.util.List;

public final class TerminalTableWriter implements ReportGenerator {

    @Override
    public void write(List<TestResult> results) {
        System.out.printf("%-20s %-12s %s%n", "Strategy", "Vulnerable", "Detail");
        for (TestResult result : results) {
            String marker = result.vulnerable() ? "\u2717 yes" : "\u2713 no";
            System.out.printf("%-20s %-12s %s%n", result.strategyName(), marker, result.detail());
        }
    }
}
