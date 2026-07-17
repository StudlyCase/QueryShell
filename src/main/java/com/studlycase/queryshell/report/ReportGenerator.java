package com.studlycase.queryshell.report;

import com.studlycase.queryshell.detection.TestResult;

import java.util.List;

public interface ReportGenerator {

    void write(List<TestResult> results);
}
