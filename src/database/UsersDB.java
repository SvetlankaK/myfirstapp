package database;

import domain.User;

import java.sql.*;
import java.util.*;


public class UsersDB {

    private static Connection connection = DataBaseConfiguration.getDBConnection();
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
    }


    private static User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("userId"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setDateOfBirth(rs.getString("DateOfBirth"));
        user.setSalary(rs.getDouble("salary"));
        user.setUserLogin(rs.getString("userLogin"));
        user.setRole(rs.getString("role"));
        return user;
    }

    private static void setUserPreparedStatements(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getName());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getSurname());
        ps.setDouble(4, user.getSalary());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getDateOfBirth());
        ps.setString(7, user.getUserLogin());
        ps.setString(8, user.getRole());
        ps.setLong(9, user.getUserId());
    }

    public static User findById(Long id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE userId=" + id);
        if (rs.next()) {
            return extractUserFromResultSet(rs);
        }
        return null
    }

    public static User findByLogin(String userLogin) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE userLogin=" + userLogin);
        if (rs.next()) {
            return extractUserFromResultSet(rs);
        }


    }

    public static Set<User> findAll() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        Set<User> users = new HashSet<>();
        while (rs.next()) {
            User user = extractUserFromResultSet(rs);
            users.add(user);
        }
        return users;

    }

    public static void create(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO user VALUES (NULL, ?, ?, ?,?,?,?,?,?,?)");
        setUserPreparedStatements(user, ps);
        ps.executeUpdate();
    }


    public static User update(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE user SET name=?, password=?, surname=?,salary=?,email=?,dateOfBirth=?, UserId =?, userLogin=?,role=? WHERE userId=?");
        setUserPreparedStatements(user, ps);
        ps.executeUpdate();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE userId=" + user.getUserId());
        return extractUserFromResultSet(rs);
    }

    public static void deleteUser(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM user WHERE userId=" + id);
    }

    public static boolean isExist(String userLogin) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE userLogin=" + userLogin);
        if (rs.next()) {
            return true;
        }
        return false;
    }
}