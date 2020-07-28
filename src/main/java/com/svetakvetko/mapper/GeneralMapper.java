//todo ЭТО ЗДЕСЬ ЕСТЬ, ЧТОБ Я ПОНИМАЛА, ЧТО МНЕ НУЖНО ЗАСУНУТЬ В МАПЕР В БУДУЩЕМ И ОНО МОГЛО ПОВТОРНО ИНИЦИАЛИЗИРОВАТЬСЯ ПРИ НЕОБХОДИМОСТИ
// СЮДА СМОТРЕТЬ НЕ НАДО!
//package com.svetakvetko.mapper;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Mapper
//public interface GeneralMapper {
//
//    private static final List<String> users = new ArrayList<>();
//    private static final List<String> role = new ArrayList<>();
//    private static List<String> user_roles = new ArrayList<>();
//
//    static {
//        Collections.addAll(users, "INSERT INTO  \"webapp\".\"USER\" "
//                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH )" + " VALUES"
//                + "(1,'kat17', 'драсте', 'cat1717@mail.ru', 'Анна', 'Иванова', 200,'10.10.1999')", "INSERT INTO  \"webapp\".\"USER\" "
//                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
//                + "(2,'leadss', 'fdeefe' , 'liveliver@gmail.com', 'Максим', 'Вешалкин', 550, '07.09.1990')", "INSERT INTO  \"webapp\".\"USER\"  "
//                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
//                + "(3,'great29', 'совсемнегениальныйпароль', 'football_player@gmail.com', 'Вадим', 'Бабурченков', 320, '17.01.1880')", "INSERT INTO  \"webapp\".\"USER\" "
//                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
//                + "(4,'honeyMOON', 'пароль', 'smirnovy@gmail.com', 'Василиса', 'Веббер', 400, '15.11.1988')", "INSERT INTO  \"webapp\".\"USER\"  "
//                + "(USERID,USERLOGIN,USERPASSWORD,USEREMAIL,USERNAME,USERSURNAME,USERSALARY,USERDATEOFBIRTH  )" + " VALUES"
//                + "(5,'Sveta', 'gfhjkm', 'svetlanka17@gmail.com', 'Светлана', 'Кветко', 100000, '06.04.2000')");
//        Collections.addAll(role, "INSERT INTO \"webapp\".\"role\" (rolename) VALUES ('user')",
//                "INSERT INTO \"webapp\".\"role\" (rolename) VALUES ('admin')");
//        Collections.addAll(user_roles, "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
//                + "VALUES (1,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
//                + "VALUES (2,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
//                + "VALUES (3,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
//                + "VALUES (4,1)", "INSERT INTO  \"webapp\".\"user_roles\" (user_id, role_id)"
//                + "VALUES (5,2)");
//
//    }
//
//     void createDataBase() {
//            PreparedStatement ps = connection.prepareStatement("CREATE DATABASE servletapp WITH OWNER postgres ;");
//    }
//
//     void createSchema() {
//            PreparedStatement ps = connection.prepareStatement("CREATE SCHEMA IF NOT EXISTS webapp");
//    }
//
//
//    public void createDbUserTable() {
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"USER\"("
//                + "USERID BIGSERIAL NOT NULL, "
//                + "USERNAME VARCHAR(20) NOT NULL, "
//                + "USERLOGIN VARCHAR(20) NOT NULL, "
//                + "USERSURNAME VARCHAR(20) NOT NULL, "
//                + "USERPASSWORD VARCHAR(30) NOT NULL, "
//                + "USEREMAIL VARCHAR(30) NOT NULL, "
//                + "USERDATEOFBIRTH VARCHAR(30) NOT NULL, "
//                + "USERSALARY FLOAT NOT NULL,"
//                + "PRIMARY KEY (USERID) "
//                + ")";
//
//    }
//
//     void createDbRoleTable() {
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"role\"("
//                + "ID bigSERIAL PRIMARY KEY, "
//                + "ROLENAME TEXT NOT NULL "
//                + ")";
//    }
//
//    void createDbUserRoleTable() {
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS \"webapp\".\"user_roles\"("
//                + "user_id serial NOT NULL,"
//                + "role_id serial NOT NULL,"
//                + "FOREIGN KEY (user_id) REFERENCES \"webapp\".\"USER\" (USERID) ON DELETE CASCADE,"
//                + "FOREIGN KEY (role_id) REFERENCES \"webapp\".\"role\" (ID),"
//                + "PRIMARY KEY (user_id, role_id)"
//                + ")";
//    }
//
//     void insertDefaultDataInDbUserTable() {
//            for (String role : role) {
//                statement.executeUpdate(role);
//            }
//            for (String user : users) {
//                statement.executeUpdate(user);
//            }
//            for (String user_roles : user_roles) {
//                statement.executeUpdate(user_roles);
//            }
//}
