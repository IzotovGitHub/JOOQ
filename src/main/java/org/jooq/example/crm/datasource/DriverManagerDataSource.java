package org.jooq.example.crm.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class DriverManagerDataSource implements DataSource {
    
    private DataSource dataSourcePool;
    
    public DriverManagerDataSource(String url, String usr, String pwd) {
        dataSourcePool = createConnectionPool(url, usr, pwd);
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        return dataSourcePool.getConnection();
    }
    
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }
    
    private DataSource createConnectionPool(String url, String user, String pwd) {
        var config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setConnectionTimeout(3000); //ms
        config.setIdleTimeout(60000); //ms
        config.setMaxLifetime(600000);//ms
        config.setAutoCommit(false);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setPoolName("DemoHiPool");
        config.setRegisterMbeans(true);
        
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        
        config.setUsername(user);
        config.setPassword(pwd);
        
        return new HikariDataSource(config);
    }
}
