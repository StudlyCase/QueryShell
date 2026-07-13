package com.studlycase.queryshell.detection;

import java.sql.Connection;

public interface InjectionTestStrategy {

    TestResult evaluate(Connection connection, String targetParameter);

    String name();
}
