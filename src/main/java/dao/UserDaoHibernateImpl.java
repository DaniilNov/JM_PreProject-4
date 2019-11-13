package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import user.User;
import util.DBHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {


    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            users = session.createQuery("From User").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            transaction.rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            transaction.rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public void deleteUser(long id) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        try {
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void editUser(User user) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserById(long id) {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        try {
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            Query query = session.createQuery("FROM User WHERE name =:name");
            query.setParameter("name", name);
            user = (User) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }


        return user;
    }

}
