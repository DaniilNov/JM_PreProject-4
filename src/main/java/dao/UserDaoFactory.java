package dao;

import util.DBHelper;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    UserDao userDao;


    public UserDaoFactory()  {
        DBHelper dbHelper = DBHelper.getInstance();
        Properties prop = new Properties();
        try {
            prop.load(dbHelper.getClass().getClassLoader().getResourceAsStream("used.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String usedTech = prop.getProperty("usedTech");
        if (usedTech.equals("jdbc")){
            this.userDao = new UserDaoJDBCImpl();
        }
        else if (usedTech.equals("hibernate")){
            this.userDao = new UserDaoHibernateImpl();
        }

    }

    public UserDao getUserDao() {
        return userDao;
    }
}

