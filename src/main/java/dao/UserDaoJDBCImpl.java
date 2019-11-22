package dao;

import user.User;
import util.DBHelper;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    DBHelper dbHelper = DBHelper.getInstance();
    //language=SQL
    private final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    //language=SQL
    private final String SQL_ADD_USER = "INSERT INTO users(name, password,role) VALUES (?,?,?)";
    //language=SQL
    private final String SQL_GET_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
    //language=SQL
    private final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    //language=SQL
    private final String SQL_DELETE_USER = "DELETE FROM users WHERE id = ?";
    //language=SQL
    private final String SQL_EDIT_USER = "UPDATE users SET name=?, password=?,role=? WHERE id=?";


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {

            PreparedStatement stmt = dbHelper.getConnection().prepareStatement(SQL_GET_ALL_USERS);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String password = resultSet.getString(3);
                String role = resultSet.getString(4);
                User user = new User(id, name, password,role);
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
        }

        return users;
    }

    public void addUser(User user) {
        String name = user.getName();
        try {
            if (getUserByName(name) == null) {
                PreparedStatement statement = dbHelper.getConnection().prepareStatement(SQL_ADD_USER);
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getPassword());
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void deleteUser(long id) {
        try {
            PreparedStatement statement = dbHelper.getConnection().prepareStatement(SQL_DELETE_USER);
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void editUser(User user) {
        try {
            PreparedStatement statement = dbHelper.getConnection().prepareStatement(SQL_EDIT_USER);

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id) {

        try {
            PreparedStatement statement = dbHelper.getConnection().prepareStatement(SQL_GET_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            long id2 = result.getLong(1);
            String name = result.getString(2);
            String password = result.getString(3);
            String role = result.getString(4);
            User user = new User(id2, name, password,role);
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }


    }

    public User getUserByName(String name) throws SQLException {
        try {
            PreparedStatement stmt = dbHelper.getConnection().prepareStatement(SQL_GET_USER_BY_NAME);
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            result.next();
            long id = result.getLong(1);
            String names = result.getString(2);
            String password = result.getString(3);
            String role = result.getString(4);
            User user = new User(id, names, password,role);
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }

    }
}
