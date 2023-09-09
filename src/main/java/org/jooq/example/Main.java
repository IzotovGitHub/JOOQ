package org.jooq.example;

import org.flywaydb.core.Flyway;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.example.crm.datasource.DriverManagerDataSource;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.jooq.example.model.Tables.ADDRESSES;
import static org.jooq.example.model.Tables.CLIENTS;
import static org.jooq.impl.DSL.foreignKey;
import static org.jooq.impl.SQLDataType.BIGINT;
import static org.jooq.impl.SQLDataType.VARCHAR;

public class Main {
    
    private static final String URL = "jdbc:postgresql://localhost:5433/jooqDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    
    public static void main(String[] args) throws Exception {
        
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(managerDataSource);
        jooqGenerator();
        DSLContext context = DSL.using(managerDataSource.getConnection(), SQLDialect.POSTGRES);
        
        crateTable(context);
        jooqGenerator();
        insertInto(context);
        
    }
    
    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db.migration", "filesystem:db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
    
    private static void jooqGenerator() throws Exception {
        GenerationTool.generate(
                Files.readString(
                        Path.of("/Users/izotov_a/IdeaProjects/JOOQ/src/main/resources/jooq-config.xml")
                )
        );
    }
    
    private static void crateTable(DSLContext context) {
        context
                .createTable("test")
                .column("id", BIGINT.notNull())
                .primaryKey("id")
                .column("name", VARCHAR(255).notNull())
                .column("client_id", BIGINT)
                .constraint(
                        foreignKey("client_id").references(CLIENTS)
                )
                .execute();
    }
    
    private static void insertInto(DSLContext context) {
        context
                .insertInto(ADDRESSES, ADDRESSES.STREET)
                .values("Витебский проспект 87/2")
                .execute();
    }
}
