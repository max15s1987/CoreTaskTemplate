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

    private SessionFactory sessionFactory;
    private Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                                                "(ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                                                    "NAME VARCHAR(50), LASTNAME VARCHAR(50), AGE INT)")
                                .addEntity(User.class);;

        query.executeUpdate();

        transaction.commit();
        closeSession();

    }

    @Override
    public void dropUsersTable() {

        openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("DROP TABLE IF EXISTS User").addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        closeSession();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(new User(name, lastName, age));

        transaction.commit();
        closeSession();

    }

    @Override
    public void removeUserById(long id) {

        openSession();

        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setId(id);

        session.delete(user);

        transaction.commit();
        closeSession();

    }

    @Override
    public List<User> getAllUsers() {

        openSession();
        List<User> users = session.createCriteria(User.class).list();
        closeSession();

//        List<User> users = (List<User>) sessionFactory.openSession().createQuery("From User").list();


        return users;
    }

    @Override
    public void cleanUsersTable() {

        openSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("TRUNCATE TABLE User").addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        closeSession();

    }

    public void openSession() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public void closeSession() {
        session.close();
    }

}
