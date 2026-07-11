package com.studlycase.queryshell.db;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.util.ConnectionException;

import java.sql.Connection;

public interface DbConnectionFactory {

    Connection connect(TargetProfile profile) throws ConnectionException;

    String dbmsName();
}
