package model;


import com.mysql.fabric.jdbc.FabricMySQLDriver;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.sql.*;

public class inquiryMySQL {
    private String user = "root";
    private String pas = "root";
    private String URL = "jdbc:mysql://localhost:3306/maim";

    private String insert = "INSERT INTO users(name , password) VALUES";
    private String log = "select * from users;";
    private String addUserList = "INSERT INTO list(name) VALUES";
    private String deleteUserList = "delete from list WHERE name=";

    private Driver driver;

    // Для отображения списка пользователей онлайн
    private ListView<String> listUsers;
    private ObservableList<String> items;

    public inquiryMySQL(){
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String login, String password) {
        try (Connection connection = DriverManager.getConnection(URL, user, pas); Statement statement = connection.createStatement();) {
            String ins = insert + "(" + "'" + login + "'" + "," + "'" + password + "'" + ")" + ";";
            System.out.println(ins);
            statement.execute(ins);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean connectUser(String login, String password) {
        try {
            Connection connection = DriverManager.getConnection(URL, user, pas);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(log);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                if (login.equals(name)) {
                    String pas = resultSet.getString("password");
                    if (password.equals(pas)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addUserList(String login) {
        try (Connection connection = DriverManager.getConnection(URL, user, pas); Statement statement = connection.createStatement();) {
            addUserList = addUserList + "('" + login + "');";
            statement.execute(addUserList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserList(String login) {
        try (Connection connection = DriverManager.getConnection(URL, user, pas); Statement statement = connection.createStatement();) {
            deleteUserList = deleteUserList + "'" + login + "';";
            System.out.println(deleteUserList);
            statement.execute(deleteUserList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateListUsers() {
        try (Connection connection = DriverManager.getConnection(URL, user, pas); Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from list;");
            while (resultSet.next()) {
                items.add(resultSet.getString("name"));
                listUsers.setItems(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkUsersList(ObservableList<String> items, ListView<String> listUsers) {
        this.items = items;
        this.listUsers = listUsers;
    }
}