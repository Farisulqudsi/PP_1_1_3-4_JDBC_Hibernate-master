package jm.task.core.jdbc.util;

import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static String connectionUrl = "jdbc:mysql://localhost:3306/user";
    static String userName = "root";
    static String password = "root";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
                settings.setProperty(Environment.DRIVER, DRIVER);
                settings.setProperty(Environment.URL, connectionUrl);
                settings.setProperty(Environment.USER, userName);
                settings.setProperty(Environment.PASS, password);
                settings.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.setProperty(Environment.SHOW_SQL, "true");

                sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(settings).buildSessionFactory();


                System.out.println("_______БД подключена через Hibernate_______");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Не могу настроить подключение к БД" + e);
            }
        }
        return sessionFactory;
    }
}
