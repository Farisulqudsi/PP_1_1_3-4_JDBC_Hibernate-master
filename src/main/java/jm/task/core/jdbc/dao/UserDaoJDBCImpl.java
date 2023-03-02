package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {

    Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        connection = Util.getConnection();

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS users (
                      `id` INT NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NOT NULL,
                      `lastname` VARCHAR(45) NOT NULL,
                      `age` INT NULL,
                      PRIMARY KEY (`id`));""");
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        connection = Util.getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу user");
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        connection = Util.getConnection();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO users (name, lastname, age) VALUES(?, ?, ?)")) {

            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);

            pStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("При сохранении пользователя произошла ошибка");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        connection = Util.getConnection();

        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("При удалении пользователя произошла ошибка");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        connection = Util.getConnection();

        List <User> userList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT name, lastName, age FROM users"
            );

            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.err.println("При получении списка пользователей произошла ошибка");
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        connection = Util.getConnection();

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.err.println("При очистке таблицы произошла ошибка");
            e.printStackTrace();
        }

    }
}
