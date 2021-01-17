package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try {
            openSession();

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                    "(ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "NAME VARCHAR(50), LASTNAME VARCHAR(50), AGE INT)")
                    .addEntity(User.class);
            ;

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession();
        }

    }

    @Override
    public void dropUsersTable() {

        try {
            openSession();

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("DROP TABLE IF EXISTS User").addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try {
            openSession();

            transaction = session.beginTransaction();

            session.persist(new User(name, lastName, age));

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession();
        }

    }

    @Override
    public void removeUserById(long id) {

        try {
            openSession();

            transaction = session.beginTransaction();

            User user = new User();
            user.setId(id);

            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession();
        }

    }

    @Override
    public List<User> getAllUsers() {

        openSession();
        List<User> users = (List<User>) session.createQuery("From User").list();
        closeSession();

        //  List<User> users = session.createCriteria(User.class).list();
        return users;
    }

    @Override
    public void cleanUsersTable() {

        try {
            openSession();

            transaction = session.beginTransaction();

            Query query = session.createSQLQuery("TRUNCATE TABLE User").addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession();
        }

    }

    public void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void closeSession() {
        session.close();
    }

}
