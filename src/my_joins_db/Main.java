package my_joins_db;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT name, phone, adress FROM "
                            + "employee, personal_data"
                            + " WHERE "
                            + "employee.id = personal_data.employee_id"
            );

            System.out.println ("name \t phone \t adress");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String adress = resultSet.getString("adress");

                System.out.println(name + " " + phone
                        + " " + adress);
            }

        } catch (SQLException  e) {
            e.printStackTrace();
        }  finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
