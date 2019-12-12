package dao;

import user.User;

import java.util.List;

public interface UserDao {
    List getAllUsers();
    void addUser(User user);
    void deleteUser(long id);
    void editUser(User user);
    User getUserById(long id);
    User getUserByName(String name);

}
