package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

//    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute("CREATE TABLE IF NOT EXISTS User " +
                                    "(ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                                        "NAME VARCHAR(50), LASTNAME VARCHAR(50), AGE INT)");

        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }


    }

    public void dropUsersTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute("DROP TABLE IF EXISTS User");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User (NAME, LASTNAME, AGE) VALUES (?, ?, ?)")){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Такой таблицы не существует");
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute("DELETE FROM User WHERE ID = " + id);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");

            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute("TRUNCATE TABLE User");

        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }
}
