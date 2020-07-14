package com.svetakvetko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConfiguration implements AutoCloseable {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/servletapp";
    static final String USER = "postgres";
    static final String PASS = "postgres";
    static final String DB_Driver = "org.postgresql.Driver";
    private static final List<String> users = new ArrayList<>();
    private static Logger log = Logger.getLogger(DataBaseConfiguration.class.getName());

    static {
        Collections.addAll(users, "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH )" + " VALUES"
                + "(1,'kat17', 'драсте', 'USER', 'cat1717@mail.ru', 'Анна', 'Иванова', 200,'10.10.1999')", "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(2,'leadss', 'fdeefe', 'USER', 'liveliver@gmail.com', 'Максим', 'Вешалкин', 550, '07.09.1990')", "INSERT INTO  \"webapp\".\"USER\"  "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(3,'great29', 'совсемнегениальныйпароль', 'USER', 'football_player@gmail.com', 'Вадим', 'Бабурченков', 320, '17.01.1880')", "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(4,'honeyMOON', 'пароль', 'USER', 'smirnovy@gmail.com', 'Василиса', 'Веббер', 400, '15.11.1988')", "INSERT INTO  \"webapp\".\"USER\"  "
                + "(USERID,USERLOGIN,USERPASSWORD,USERROLE,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(5,'Sveta', 'gfhjkm', 'ADMIN', 'svetlanka17@gmail.com', 'Светлана', 'Кветко', 100000, '06.04.2000')");

    }

    static Connection getDBConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            log.log(Level.INFO, "PostgreSQL JDBC Driver successfully connected");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            log.log(Level.WARNING, "Exception: ", e);
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            log.log(Level.WARNING, "Exception: ", e);
        }
        if (connection != null) {
            log.log(Level.INFO, "You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }

    static void closeDBConnection(Connection connection) {
        try {
            connection.close();
            log.log(Level.INFO, "Successfully disconnected");
        } catch (SQLException e) {
            System.out.println("Disconnection failed");
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

    private static void createDataBase() {
        try (Connection connection = DataBaseConfiguration.getDBConnection();
             PreparedStatement ps = connection.prepareStatement("CREATE DATABASE servletApp WITH OWNER postgres ;")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
//         finally {
//            closeDBConnection(connection);
//        }

    }

    private static void createSchema() {
        try (Connection connection = DataBaseConfiguration.getDBConnection();
        ) {
            PreparedStatement ps = connection.prepareStatement("CREATE SCHEMA webApp");
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
//        } finally {
//            closeDBConnection(connection);
//        }

    }


    private static void createDbUserTable() {
        String createTableSQL = "CREATE TABLE \"webapp\".\"USER\"("
                + "USERID BIGSERIAL NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "USERLOGIN VARCHAR(20) NOT NULL, "
                + "USERSURNAME VARCHAR(20) NOT NULL, "
                + "USERPASSWORD VARCHAR(30) NOT NULL, "
                + "USERROLE VARCHAR(20) NOT NULL, "
                + "USEREMAIL VARCHAR(30) NOT NULL, "
                + "USERDATEOFBIRTH VARCHAR(30) NOT NULL, "
                + "USERSALARY FLOAT NOT NULL,"
                + "PRIMARY KEY (USERID) "
                + ")";

        try (Connection connection = DataBaseConfiguration.getDBConnection()) {
            PreparedStatement ps = connection.prepareStatement(createTableSQL);
            ps.execute();
            log.log(Level.INFO, "Table \"user\" is created!");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
//        } finally {
//            closeDBConnection(connection);
//        }
    }

    private static void insertDefaultDataInDbUserTable() {
        try (Connection connection = DataBaseConfiguration.getDBConnection();
             Statement statement = connection.createStatement()) {
            for (String user : users) {
                statement.executeUpdate(user);
            }
            log.log(Level.INFO, "Пользователи успешно добавлены");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
//        } finally {
//            closeDBConnection(connection);
//        }
    }

    @Override
    public void close() throws Exception {
        log.log(Level.INFO, "Successfully disconnected");
    }
}