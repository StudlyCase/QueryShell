package com.studlycase.queryshell.core;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.detection.TestResult;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class ScanSession {

    private final TargetProfile profile;
    private final Instant startedAt;
    private final List<TestResult> results = new ArrayList<>();

    public ScanSession(TargetProfile profile) {
        this.profile = profile;
        this.startedAt = Instant.now();
    }

    public TargetProfile profile() {
        return profile;
    }

    public Instant startedAt() {
        return startedAt;
    }

    public void addResults(List<TestResult> newResults) {
        results.addAll(newResults);
    }

    public List<TestResult> results() {
        return results;
    }
}
