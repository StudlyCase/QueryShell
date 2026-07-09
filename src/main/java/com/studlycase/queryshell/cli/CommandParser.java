package com.studlycase.queryshell.cli;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.core.ScanOrchestrator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(
        name = "queryshell",
        mixinStandardHelpOptions = true,
        version = "queryshell 0.1.0",
        description = "SQL pen-testing."
)
public final class CommandParser implements Callable<Integer> {

    @Option(names = {"-t", "--target"}, required = true, description = "Target host or connection string.")
    private String target;

    @Option(names = {"-p", "--port"}, defaultValue = "3306", description = "Target port.")
    private int port;

    @Option(names = {"-u", "--user"}, description = "Database user.")
    private String user;

    @Option(names = {"--authorized"}, required = true,
            description = "Confirms explicit authorization to test the given target.")
    private boolean authorized;

    @Option(names = {"-o", "--output"}, description = "Report output format (json|csv|table).", defaultValue = "table")
    private String outputFormat;

    private final TerminalUI ui = new TerminalUI();

    @Override
    public Integer call() {
        if (!authorized) {
            ui.printError("aborted: --authorized flag is mandatory.");
            return 1;
        }

        TargetProfile profile = new TargetProfile(target, port, user);
        ScanOrchestrator orchestrator = new ScanOrchestrator(profile, outputFormat);

        return orchestrator.run() ? 0 : 1;
    }
}