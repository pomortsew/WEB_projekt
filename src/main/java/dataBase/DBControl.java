package dataBase;

import java.sql.*;
import java.util.ArrayList;

public class DBControl {
    private static final String URL_DATA_BASE = "jdbc:mysql://127.0.0.1:3306/test_site?useUnicode=true&characterEncoding=utf8";
    private static final String LOGIN_DATA_BASE = "root";
    private static final String PASSWORD_DATA_BASE = "devel";
    private static final String ERROR_LOADING_DBMYSQL = "невозможно загрузить драйвер MySQL";

    public static void main(String args[]) {

        System.out.println(userVerification("pomortsew", "asdfg"));
    }


    // метод возвращает соединение с базой данных
    public static Connection getConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connectionMySQL = DriverManager.getConnection(URL_DATA_BASE, LOGIN_DATA_BASE, PASSWORD_DATA_BASE);
            return connectionMySQL;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // метод добавления Пользователя в базу данных
    public static void addInfo(String login, String password, String name, String surname) {
        System.out.println("начало метода");
        try {
            PreparedStatement preparedStatement = getConection()
                    .prepareStatement("INSERT INTO users (login,pasword, uname,surname) VALUES (?,?,?,?)");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }


    // метод возвращает лист с информацией о пользователе из базы данных
    public static ArrayList<String> sampling(String login, String password) {
        ArrayList<String> data = new ArrayList<>();


        try {
            PreparedStatement prs = getConection().prepareStatement("SELECT * FROM test_site.users WHERE login=? && pasword=?");
            prs.setString(1, login);
            prs.setString(2, password);
            ResultSet resultSet = prs.executeQuery();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
                data.add(resultSet.getString(2));
                data.add(resultSet.getString(3));
                data.add(resultSet.getString(4));

            }

            System.out.println(data.toString());
            System.out.println(data.get(0));
            System.out.println(data.get(1));
            System.out.println(data.get(2));
            System.out.println(data.get(3));
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return data;
    }


    public static boolean scaningLogin(String login) {
        boolean flag = false;

        try {
            ResultSet resultSet;

            PreparedStatement preparedStatement = getConection()
                    .prepareStatement("SELECT * FROM test_site.users WHERE login=?");
            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next() && resultSet.getString("login") .equals( login)) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static boolean userVerification(String login, String password) {
        boolean flag = false;

        try {
            ResultSet resultSet;
            PreparedStatement preparedStatement;
            preparedStatement = getConection()
                    .prepareStatement("SELECT * FROM test_site.users WHERE  login=?");
            preparedStatement.setString(1,login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                if (password.equals(resultSet.getString("pasword"))) {
                    flag = true;
                    return flag;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
//    public static void extractionFromDatabase(String login) {
//
//
//        try {
//            Statement statement=getConection().createStatement();
//ResultSet resultSet;
////             resultSet=statement.executeQuery("SELECT * FROM test_site.users ");
////            resultSet=statement.executeQuery("SHOW TABLES ");
////            resultSet=statement.executeQuery("SHOW DATABASES "); //вывод всех баз данных
////            resultSet=statement.executeQuery("SHOW TABLES IN test_site");// вывод всех таблиц из базы (test_site)
////            resultSet=statement.executeQuery("SELECT * FROM test_site.users"); //вывод все таблицы
////            resultSet=statement.executeQuery("SELECT * FROM users WHERE login='rybkov'"); //вывод строки по значению (rybkov)
////            resultSet=statement.executeQuery("SELECT  * from test_site.users");
//            resultSet=statement.executeQuery("SELECT *from test_site.users WHERE login='rybkov' && pasword='qwerty'");//выводит строку из базы где login = rybkov и pasword = qwerty
//
//          while (resultSet.next()) {
//
//
//              String result = resultSet.getString("uname");
//              String result1 = resultSet.getString("surname");
//
//
//              System.out.println(result+"   "+result1);
//
//          }
//          statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
}



