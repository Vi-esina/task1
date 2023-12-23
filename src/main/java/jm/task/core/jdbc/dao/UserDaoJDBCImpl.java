package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {




    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection( Util.URL, Util.USER, Util.PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "lastName VARCHAR(50) NOT NULL," +
                    "age SMALLINT NOT NULL" +
                    ")";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void dropUsersTable() {
            try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
                 Statement statement = connection.createStatement()) {
                String query = "DROP TABLE IF EXISTS users";
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(id, name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "TRUNCATE TABLE users";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

