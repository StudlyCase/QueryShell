package com.studlycase.queryshell.db;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.util.ConnectionException;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ConnectionPool {

    private final Map<String, DbConnectionFactory> factories = new ConcurrentHashMap<>();

    public void register(DbConnectionFactory factory) {
        factories.put(factory.dbmsName(), factory);
    }

    public Connection acquire(String dbmsName, TargetProfile profile) throws ConnectionException {
        DbConnectionFactory factory = factories.get(dbmsName);
        if (factory == null) {
            throw new ConnectionException("No connector registered for DBMS: " + dbmsName, null);
        }
        return factory.connect(profile);
    }
}
