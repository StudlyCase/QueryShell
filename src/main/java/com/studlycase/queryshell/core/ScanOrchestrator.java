package com.studlycase.queryshell.core;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.db.ConnectionPool;
import com.studlycase.queryshell.db.MySqlConnector;
import com.studlycase.queryshell.detection.BooleanBasedStrategy;
import com.studlycase.queryshell.detection.DetectionEngine;
import com.studlycase.queryshell.detection.ErrorBasedStrategy;
import com.studlycase.queryshell.detection.TimeBasedStrategy;
import com.studlycase.queryshell.payloads.PayloadRepository;
import com.studlycase.queryshell.report.CsvReportWriter;
import com.studlycase.queryshell.report.JsonReportWriter;
import com.studlycase.queryshell.report.ReportGenerator;
import com.studlycase.queryshell.report.TerminalTableWriter;
import com.studlycase.queryshell.util.ConnectionException;

import java.sql.Connection;

public final class ScanOrchestrator {

    private final TargetProfile profile;
    private final String outputFormat;

    public ScanOrchestrator(TargetProfile profile, String outputFormat) {
        this.profile = profile;
        this.outputFormat = outputFormat;
    }

    public boolean run() {
        ScanSession session = new ScanSession(profile);

        ConnectionPool pool = new ConnectionPool();
        pool.register(new MySqlConnector());

        try (Connection connection = pool.acquire("MySQL", profile)) {
            PayloadRepository payloadRepository = new PayloadRepository();

            DetectionEngine engine = new DetectionEngine();
            engine.register(new ErrorBasedStrategy(payloadRepository));
            engine.register(new BooleanBasedStrategy(payloadRepository));
            engine.register(new TimeBasedStrategy(payloadRepository));

            session.addResults(engine.runAll(connection, profile.user()));

            resolveReportGenerator().write(session.results());
            return true;
        } catch (ConnectionException | java.sql.SQLException e) {
            System.err.println("Scan failed: " + e.getMessage());
            return false;
        }
    }

    private ReportGenerator resolveReportGenerator() {
        return switch (outputFormat.toLowerCase()) {
            case "json" -> new JsonReportWriter();
            case "csv" -> new CsvReportWriter();
            default -> new TerminalTableWriter();
        };
    }
}
