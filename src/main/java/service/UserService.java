package service;

import user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List getAllUsers() throws SQLException, ClassNotFoundException;
    void addUser(User user);
    void deleteUser(long id);
    void editUser(User user);
    User getUserById(long id);
    User getUserByName(String name) throws SQLException;
}
