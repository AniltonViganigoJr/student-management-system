package edu.myschoolapp.aluno.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado no classpath");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar db.properties", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password"));
    }
}