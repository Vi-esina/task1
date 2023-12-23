package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "root";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(Util.DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", URL);
                configuration.setProperty("hibernate.connection.driver_class", DRIVER);
                configuration.setProperty("hibernate.connection.username", USER);
                configuration.setProperty("hibernate.connection.password", PASSWORD);
                configuration.setProperty("hibernate.current_session_context_class", "thread");
                configuration.setProperty("hibernate.show_sql", "true");
                configuration.setProperty("format_sql", "true");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update");
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
