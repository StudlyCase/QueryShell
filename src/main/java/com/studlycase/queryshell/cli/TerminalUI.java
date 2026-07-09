package com.studlycase.queryshell.cli;

public final class TerminalUI {

    public void printInfo(String message) {
        System.out.println("[i] " + message);
    }

    public void printSuccess(String message) {
        System.out.println("[\u2713] " + message);
    }

    public void printWarning(String message) {
        System.out.println("[\u26A0] " + message);
    }

    public void printError(String message) {
        System.err.println("[\u2717] " + message);
    }
}
