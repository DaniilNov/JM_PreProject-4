package dao;

import util.DBHelper;
import util.PropertiesReader;

import java.io.IOException;
import java.util.Properties;

// Новая фабрика сделанная Синглтоном
public class UserDaoFactoryImpl implements UserDaoFactory {
    private static UserDaoFactoryImpl ourInstance = new UserDaoFactoryImpl();

    public static UserDaoFactoryImpl getInstance() {
        return ourInstance;
    }

    private UserDaoFactoryImpl() {
    }

    @Override
    public UserDao getUserDao() {
        UserDao userDao;
        String usedTech = PropertiesReader.propFactory("usedTech");

        if (usedTech.equals("jdbc")) {
            userDao = new UserDaoJDBCImpl();
        } else if (usedTech.equals("hibernate")) {
            userDao = new UserDaoHibernateImpl();
        } else {
//На случай ошибки в файле
            userDao = new UserDaoHibernateImpl();
        }
        return userDao;
    }
}
