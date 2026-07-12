package com.studlycase.queryshell.db;

import com.studlycase.queryshell.config.TargetProfile;
import com.studlycase.queryshell.util.ConnectionException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public final class MySqlConnector implements DbConnectionFactory {

    @Override
    public Connection connect(TargetProfile profile) throws ConnectionException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + profile.host() + ":" + profile.port() + "/");
        config.setUsername(profile.user());
        config.setMaximumPoolSize(1);

        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            dataSource.close();
            throw new ConnectionException("Failed to connect to MySQL target: " + profile.host(), e);
        }
    }

    @Override
    public String dbmsName() {
        return "MySQL";
    }
}
