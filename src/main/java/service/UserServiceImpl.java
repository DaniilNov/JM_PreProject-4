package service;

import dao.UserDao;
import dao.UserDaoFactoryImpl;
import user.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl ourInstance = new UserServiceImpl();
//    private UserDao userDao = new UserDaoFactoryOld().getUserDao();
    private UserDao userDao = UserDaoFactoryImpl.getInstance().getUserDao();
    public static UserServiceImpl getInstance() {
        return ourInstance;
    }

    private UserServiceImpl() {
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);

    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }
}
