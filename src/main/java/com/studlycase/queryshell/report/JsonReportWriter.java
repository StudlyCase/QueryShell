package com.studlycase.queryshell.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studlycase.queryshell.detection.TestResult;

import java.util.List;

public final class JsonReportWriter implements ReportGenerator {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void write(List<TestResult> results) {
        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(results));
        } catch (Exception e) {
            System.err.println("Failed to serialize report: " + e.getMessage());
        }
    }
}
