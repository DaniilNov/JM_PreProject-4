package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import user.User;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static DBHelper instance;
//    private static final String URL = "jdbc:mysql://localhost:3306/jm_schema";
//    private static final String PASSWORD = "root";
//    private static final String LOGIN = "root";
    private static Connection connection;
    private static SessionFactory sessionFactory;


    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        DBHelper dbHelper = new DBHelper();
//        Properties prop = new Properties();

        try {

//            prop.load(dbHelper.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
            String URL = PropertiesReader.getProperties("db.url");
            String PASSWORD = PropertiesReader.getProperties("db.username");
            String LOGIN = PropertiesReader.getProperties("db.password");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Ошибка в подключении драйвера или соединении");
            e.getStackTrace();
        }
        return connection;
    }

    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("password"));
        configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hbm2ddl.auto"));
        return configuration;
    }

//    private static String getProperties(String name) {
//        String value = null;
//        Properties properties = new Properties();
//        DBHelper dbHelper = new DBHelper();
//        try {
//            InputStream is = dbHelper.getClass().getClassLoader().getResourceAsStream("hibernate.properties");
//            properties.load(is);
//            value = properties.getProperty(name);
//            is.close();
//        } catch (IOException e) {
//            System.err.println("Проблема в проперти");
//        }
//        return value;
//    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        org.hibernate.service.ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);

    }

}
