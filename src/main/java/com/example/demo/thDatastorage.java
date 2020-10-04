package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class thDatastorage {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://43.255.154.48:3306/testrubby",
                "test4321", "Passion@411");
    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public void storeUserDetails(String firstName,String lastName,String email,String status, String password) throws ClassNotFoundException {
        String sql = "INSERT INTO usertable (firstName,lastName,email,status,password) VALUES ('"+firstName+"','"+lastName+"','"+email+"','"+status+"','"+password+"');";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();
            // System.out.println("Executed"+email);
            System.out.println("Fine");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public String updateprofilepic(String email) throws ClassNotFoundException {
        String sql = "update usertable set profilepic='yes' where email='"+email+"';";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();
            // System.out.println("Executed"+email);
            System.out.println("Fine");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return "yes";
    }

    public void storeTeacherDetails(String email,String subject,String experience,String city) throws ClassNotFoundException {
        String sql = "INSERT INTO teacherstable (email,subject,experience,city) VALUES ('"+email+"','"+subject+"','"+experience+"','"+city+"');";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();
            // System.out.println("Executed"+email);
            System.out.println("Fine");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void storeConnection(String studentemail,String teacheremail) throws ClassNotFoundException {
        String sql = "INSERT INTO connections (studentemail,teacheremail) VALUES ('"+studentemail+"','"+teacheremail+"');";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();
            // System.out.println("Executed"+email);
            System.out.println("Fine");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public void storePosts(String userEmail,String postheading, String postcontent) throws ClassNotFoundException {
        String sql = "INSERT INTO posts (email,heading,content) VALUES ('"+userEmail+"','"+postheading+"','"+postcontent+"');";

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int resultSet = statement.executeUpdate();
            // System.out.println("Executed"+email);
            System.out.println("Fine");

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    public userInfo validateUserDetails(String email,String password) throws ClassNotFoundException {

        String dbUsername, dbPassword,dbStatus="";
        boolean login = false;
        String sql = "SELECT * FROM usertable;";
        String sqlupdate = "";
       // String sqlUser = "SELECT usertable.firstName,usertable.lastName,usertable.email,usertable.status,usertable.password,teacherstable.subject,teacherstable.experience,teacherstable.city FROM usertable LEFT JOIN teacherstable ON usertable.email = teacherstable.email where usertable.email='"+email+"';";
        userInfo ui = new userInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rs = statement.getResultSet();
            while(rs.next()){
                dbUsername = rs.getString("email");
                dbPassword = rs.getString("password");



                if(dbUsername.equals(email) && dbPassword.equals(password)){
                    System.out.println(dbUsername);
                    System.out.println(dbPassword);
                    System.out.println("OK");
                    login = true;
                     sqlupdate = "update usertable set logged='online' where email='"+dbUsername+"'";
                    PreparedStatement statement1 = connection.prepareStatement(sqlupdate);
                    statement1.executeUpdate();
                    dbStatus = rs.getString("status");
                    //ui = new userInfo(rs.getString("firstName"),rs.getString("lastName"),rs.getString(""),rs.getString("email"),rs.getString("password"));
                }

            }
            if(login){
                String sqlUser = "SELECT usertable.firstName,usertable.lastName,usertable.email,usertable.status,usertable.password,usertable.profilepic,teacherstable.subject,teacherstable.experience,teacherstable.city,usertable.logged FROM usertable LEFT JOIN teacherstable ON usertable.email = teacherstable.email where usertable.email='"+email+"';";


                PreparedStatement statementUser = connection.prepareStatement(sqlUser);
                statementUser.executeQuery(sqlUser);
                ResultSet rsUser = statementUser.getResultSet();

               // System.out.println( rsUser.first().getString("status")+"fetched ");
                while(rsUser.next()){
                   System.out.println( rsUser.getString("logged")+"fetched loggedStatus");

                    ui = new userInfo(rsUser.getString("status"),rsUser.getString("firstName"),rsUser.getString("lastName"),rsUser.getString("email"),rsUser.getString("subject"),rsUser.getString("experience"),rsUser.getString("city"),rsUser.getString("password"),rsUser.getString("logged"),rsUser.getString("profilepic"));

                }

            }else{
                ui = new userInfo("","","","","","","","","","");


            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(login);

        System.out.println(ui.getProfilepic()+" profile pic status");

        return ui;
    }
    public Set<userInfo> logout(String email) throws ClassNotFoundException {

        String dbemail, dbPassword,dbStatus="";
        Set<userInfo> ul = new HashSet<>();
        boolean login = false;
        String sql = "update usertable set logged='offline' where email='"+email+"';";
        userInfo ui = new userInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(ui.firstname);

        return ul;
    }

    public Set<userInfo> searchTeacher(String subject) throws ClassNotFoundException {

        String dbSubject, dbPassword,dbStatus="";
        Set<userInfo> ul = new HashSet<>();
        boolean login = false;
        String sql = "SELECT usertable.firstName,usertable.lastName,usertable.email,usertable.status,usertable.password,teacherstable.subject,teacherstable.experience,teacherstable.city,usertable.logged FROM usertable right JOIN teacherstable ON usertable.email = teacherstable.email ;\n";
        userInfo ui = new userInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rsUser = statement.getResultSet();
            while(rsUser.next()){
                dbSubject = rsUser.getString("subject");
                System.out.println(dbSubject+" subject is");
                System.out.println(subject+" subject entered");
                if(dbSubject.equalsIgnoreCase(subject)){
                    System.out.println("Subject matched");
                    System.out.println(rsUser.getString("firstName"));
                    ui = new userInfo(rsUser.getString("status"),rsUser.getString("firstName"),rsUser.getString("lastName"),rsUser.getString("email"),rsUser.getString("subject"),rsUser.getString("experience"),rsUser.getString("city"),"",rsUser.getString("logged"),"");
                    ul.add(ui);

                }

            }


        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(ui.firstname);

        return ul;
    }

    public List<userInfo> getTeacher(String student) throws ClassNotFoundException {

        String dbStudent, dbPassword,dbStatus="";
        List<userInfo> ul = new ArrayList<userInfo>();
        boolean login = false;
        String sql = "SELECT usertable.firstName,usertable.lastName,usertable.email,usertable.status,usertable.password,teacherstable.subject,teacherstable.experience,teacherstable.city,usertable.logged FROM usertable left JOIN teacherstable ON usertable.email = teacherstable.email inner join connections on usertable.email = connections.teacheremail where connections.studentemail='"+student+"';";
        userInfo ui = new userInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rsUser = statement.getResultSet();
            while(rsUser.next()){

                    ui = new userInfo(rsUser.getString("status"),rsUser.getString("firstName"),rsUser.getString("lastName"),rsUser.getString("email"),rsUser.getString("subject"),rsUser.getString("experience"),rsUser.getString("city"),"",rsUser.getString("logged"),"");
                    ul.add(ui);


            }


        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(ui.firstname);

        return ul;
    }

    public List<userInfo> getStudents(String Teacher) throws ClassNotFoundException {

        String dbTeacher, dbPassword,dbStatus="";
        List<userInfo> ul = new ArrayList<userInfo>();
        boolean login = false;
        String sql = "SELECT usertable.firstName,usertable.lastName,usertable.email,usertable.status,usertable.password,teacherstable.subject,teacherstable.experience,teacherstable.city,usertable.logged FROM usertable left JOIN teacherstable ON usertable.email = teacherstable.email inner join connections on usertable.email = connections.studentemail where connections.teacheremail='"+Teacher+"';";
        userInfo ui = new userInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rsUser = statement.getResultSet();
            while(rsUser.next()){

                ui = new userInfo(rsUser.getString("status"),rsUser.getString("firstName"),rsUser.getString("lastName"),rsUser.getString("email"),rsUser.getString("subject"),rsUser.getString("experience"),rsUser.getString("city"),"",rsUser.getString("logged"),rsUser.getString("profilepic"));
                ul.add(ui);


            }


        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(ui.firstname);

        return ul;
    }

    public List<postInfo> getTeacherPosts(String teacher) throws ClassNotFoundException {

        String dbTeacher, dbPassword,dbStatus="";
        List<postInfo> ul = new ArrayList<postInfo>();
        boolean login = false;
        String sql = "select * from posts where email='"+teacher+"';";
        postInfo pi = new postInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rsUser = statement.getResultSet();
            while(rsUser.next()){

                pi = new postInfo(rsUser.getString("email"),rsUser.getString("heading"),rsUser.getString("content"));
                ul.add(pi);


            }


        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(pi.email);

        return ul;
    }

    public List<postInfo> getStudentPosts(String teacher) throws ClassNotFoundException {

        String dbTeacher, dbPassword,dbStatus="";
        List<postInfo> ul = new ArrayList<postInfo>();
        boolean login = false;
        String sql = "SELECT usertable.firstname , posts.heading,posts.content FROM usertable left JOIN teacherstable ON usertable.email = teacherstable.email inner join connections on usertable.email = connections.teacheremail inner join posts on usertable.email = posts.email  where connections.studentemail='"+teacher+"' ;";
        postInfo pi = new postInfo();

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
            //Statement stmt = (Statement) con.createStatement();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery(sql);
            ResultSet rsUser = statement.getResultSet();
            while(rsUser.next()){

                pi = new postInfo(rsUser.getString("firstname"),rsUser.getString("heading"),rsUser.getString("content"));
                ul.add(pi);


            }


        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        System.out.println(pi.email);

        return ul;
    }


}
