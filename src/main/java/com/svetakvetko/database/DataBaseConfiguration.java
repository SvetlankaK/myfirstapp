package com.svetakvetko.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class DataBaseConfiguration implements AutoCloseable { //TODO remove wtf


    @Value("${database.connection.url}")
    private String DB_URL;

    @Value("${database.userName}")
    private String USER;

    @Value("${database.password}")
    private String PASS;

    @Value("${database.driver}")
    private String DB_Driver;


    private static final List<String> users = new ArrayList<>();
    private static final List<String> role = new ArrayList<>();
    private static List<String> user_roles = new ArrayList<>();

    private static Logger log = Logger.getLogger(DataBaseConfiguration.class.getName());

    static {
        Collections.addAll(users, "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH )" + " VALUES"
                + "(1,'kat17', 'драсте', 'cat1717@mail.ru', 'Анна', 'Иванова', 200,'10.10.1999')", "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(2,'leadss', 'fdeefe' , 'liveliver@gmail.com', 'Максим', 'Вешалкин', 550, '07.09.1990')", "INSERT INTO  \"webapp\".\"USER\"  "
                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(3,'great29', 'совсемнегениальныйпароль', 'football_player@gmail.com', 'Вадим', 'Бабурченков', 320, '17.01.1880')", "INSERT INTO  \"webapp\".\"USER\" "
                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(4,'honeyMOON', 'пароль', 'smirnovy@gmail.com', 'Василиса', 'Веббер', 400, '15.11.1988')", "INSERT INTO  \"webapp\".\"USER\"  "
                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
                + "(5,'Sveta', 'gfhjkm', 'svetlanka17@gmail.com', 'Светлана', 'Кветко', 100000, '06.04.2000')");
        Collections.addAll(role, "INSERT INTO \"webapp\".\"role\" (rolename) VALUES ('user')",
                "INSERT INTO \"webapp\".\"role\" (rolename) VALUES ('admin')");
        Collections.addAll(user_roles, "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
                + "VALUES (1,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
                + "VALUES (2,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
                + "VALUES (3,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
                + "VALUES (4,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
                + "VALUES (5,2)");

    }


    public Connection getDBConnection() {
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

    public void closeDBConnection(Connection connection) {
        try {
            connection.close();
            log.log(Level.INFO, "Successfully disconnected");
        } catch (SQLException e) {
            System.out.println("Disconnection failed");
            log.log(Level.WARNING, "Exception: ", e);
        }
    }
    public void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
            log.log(Level.INFO, "Statement successfully closed");
        } catch (SQLException e) {
            System.out.println("Close attempt failed");
            log.log(Level.WARNING, "Exception: ", e);
        }
    }
    public void createDataBase() {
        try (Connection connection = getDBConnection();
             PreparedStatement ps = connection.prepareStatement("CREATE DATABASE servletapp WITH OWNER postgres ;")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }

    }

    public void createSchema() {
        try (Connection connection = getDBConnection();
        ) {
            PreparedStatement ps = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS webapp");
            ps.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }


    }


    public void createDbUserTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"USER\"("
                + "USERID BIGSERIAL NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "USERLOGIN VARCHAR(20) NOT NULL, "
                + "USERSURNAME VARCHAR(20) NOT NULL, "
                + "USERPASSWORD VARCHAR(30) NOT NULL, "
                + "USEREMAIL VARCHAR(30) NOT NULL, "
                + "USERDATEOFBIRTH VARCHAR(30) NOT NULL, "
                + "USERSALARY FLOAT NOT NULL,"
                + "PRIMARY KEY (USERID) "
                + ")";

        try (Connection connection = getDBConnection()) {
            PreparedStatement ps = connection.prepareStatement(createTableSQL);
            ps.execute();
            log.log(Level.INFO, "Table \"user\" is created!");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

    public void createDbRoleTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"role\"("
                + "ID bigSERIAL PRIMARY KEY, "
                + "ROLENAME TEXT NOT NULL "
                + ")";

        try (Connection connection = getDBConnection()) {
            PreparedStatement ps = connection.prepareStatement(createTableSQL);
            ps.execute();
            log.log(Level.INFO, "Table \"role\" is created!");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

    public void createDbUserRoleTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"user_roles\"("
                + "user_id serial NOT NULL,"
                + "role_id serial NOT NULL,"
                + "FOREIGN KEY (user_id) REFERENCES \"webapp\".\"USER\" (USERID) ON DELETE CASCADE,"
                + "FOREIGN KEY (role_id) REFERENCES \"webapp\".\"role\" (ID),"
                + "PRIMARY KEY (user_id, role_id)"
                + ")";

        try (Connection connection = getDBConnection()) {
            PreparedStatement ps = connection.prepareStatement(createTableSQL);
            ps.execute();
            log.log(Level.INFO, "Table \"user_roles\" is created!");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

    public void insertDefaultDataInDbUserTable() {
        try (Connection connection = getDBConnection();
             Statement statement = connection.createStatement()) {
            for (String role : role) {
                statement.executeUpdate(role);
            }
            for (String user : users) {
                statement.executeUpdate(user);
            }
            for (String user_roles : user_roles) {
                statement.executeUpdate(user_roles);
            }
            log.log(Level.INFO, "Пользователи успешно добавлены");
        } catch (SQLException e) {
            log.log(Level.WARNING, "Exception: ", e);
        }
    }

    @Override
    public void close() throws Exception {
        log.log(Level.INFO, "Successfully disconnected");
    }

}