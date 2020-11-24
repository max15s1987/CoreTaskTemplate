package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS User " +
                                "(ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                                "NAME VARCHAR(50), LASTNAME VARCHAR(50), AGE INT)";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sqlCreateTable);

        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sqlDropTable = "DROP TABLE IF EXISTS User";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sqlDropTable);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {


        String sqlAddUser = "INSERT INTO User (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlAddUser)){

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

        String sqlRemoveByID = "DELETE FROM User WHERE ID = " + id;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sqlRemoveByID);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

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

        String sqlCleanTable = "TRUNCATE TABLE User";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            statement.execute(sqlCleanTable);

        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }
}
