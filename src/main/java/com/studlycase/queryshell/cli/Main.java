package com.studlycase.queryshell.cli;

import picocli.CommandLine;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CommandParser()).execute(args);
        System.exit(exitCode);
    }
}