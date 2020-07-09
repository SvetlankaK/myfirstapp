package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBaseConfiguration {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String DB_Driver = "org.postgresql.Driver";
    private static Connection connection = getDBConnection();
    private static final List<String> users = new ArrayList<>();

    static {
        Collections.addAll(users, "INSERT INTO USER "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH )" + " VALUES"
                + "(1,'kat17', 'драсте', USER_ACCESS.getName(), 'cat1717@mail.ru', 'Анна', 'Иванова', 200,'10.10.1999')", "INSERT INTO USER "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(1,'leadss', 'fdeefe', USER_ACCESS.getName(), 'liveliver@gmail.com', 'Максим', 'Вешалкин', 550, '07.09.1990')", "INSERT INTO USER "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(3,'great29', 'совсемнегениальныйпароль', USER_ACCESS.getName(), 'football_player@gmail.com', 'Вадим', 'Бабурченков', 320, '17.01.1880')", "INSERT INTO USER "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(4,'honeyMOON', 'пароль', USER_ACCESS.getName(), 'smirnovy@gmail.com', 'Василиса', 'Веббер', 400, '15.11.1988')", "INSERT INTO USER "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(5,'Sveta', 'gfhjkm', ADMIN_ACCESS.getName(), 'svetlanka17@gmail.com', 'Светлана', 'Кветко', 100000, '06.04.2000')");
        insertDefaultDataInDbUserTable(connection);
    }

    static Connection getDBConnection() {
        try {
            Class.forName(DB_Driver);

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver successfully connected");
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }

    private static void configureDataBase(Connection connection) {
        String sql = "CREATE DATABASE servletApp WITH OWNER postgres ;";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            sql = "CREATE SCHEMA webApp";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void createDbUserTable(Connection connection) {
        String createTableSQL = "CREATE TABLE \"USER\"("
                + "USERID BIGSERIAL NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "USERLOGIN VARCHAR(20) NOT NULL, "
                + "USERSURNAME VARCHAR(20) NOT NULL, "
                + "USERPASSWORD VARCHAR(20) NOT NULL, "
                + "USERROLE VARCHAR(20) NOT NULL, "
                + "USEREMAIL VARCHAR(20) NOT NULL, "
                + "USERDATEOFBIRTH VARCHAR(20) NOT NULL, "
                + "USERSALARY FLOAT NOT NULL,"
                + "PRIMARY KEY (USERID) "
                + ")";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table \"user\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insertDefaultDataInDbUserTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            for (String user : users) {
                statement.executeUpdate(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}