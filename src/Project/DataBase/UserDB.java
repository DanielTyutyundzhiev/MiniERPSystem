package Project.DataBase;

import Project.FileLoader;
import Project.model.SalesRep;
import Project.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB {
    public void addUser(int id,String firstName,String lastName,String email) throws SQLException{
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        int status=1;
        Statement statement= null;
        try {
            statement = connectDB.createStatement();
            statement.executeUpdate("INSERT INTO users(user_id,first_nameU,last_nameU,email,status) VALUES ('"+id+"','"+firstName+"','"+lastName+"','"+email+"',+1)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public User validateBy(String name) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        String query="SELECT * FROM users WHERE first_nameU='"+name+"'";
        ResultSet rs = connectDB.createStatement().executeQuery(query);
        User userSearch=new User();
        try {
            if(rs.next()) {
                if (rs.getInt(6) == 0) {
                    userSearch.setUserID(rs.getInt(1));
                    userSearch.setSalesRepID(rs.getString(2));
                    userSearch.setFirstName(rs.getString(3));
                    userSearch.setLastName(rs.getString(4));
                    userSearch.setEmail(rs.getString(5));
                    userSearch.setStatus(1);
                    return userSearch;
                } else {
                    return null;
                }
            }else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public User validateUserByEmail(String email) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM users WHERE email= '" +email+"'");
        User user=new User();
        if(rs.next()){
            user.setUserID(rs.getInt(1));
            user.setSalesRepID(rs.getString(2));
            user.setFirstName(rs.getString(3));
            user.setLastName(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setStatus(6);
            return user;
        }
        else return null;
    }

    public void updateStatus(int id,int status) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String updateStatus= "UPDATE users SET status= '"+status+"' WHERE u_id="+id;
        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(updateStatus);
        }catch (Exception e){
            System.out.println();
        }finally {
            connectDB.close();
        }

    }

    public ResultSet updateTable() throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        FileLoader fileLoader=new FileLoader();
        int srID=fileLoader.readFile();
        ResultSet rs=connectDB.createStatement().executeQuery("SELECT * FROM users WHERE status=1 AND user_id="+srID);
        return rs;
    }

    public User validateUserById(int id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM users WHERE u_id="+id);
        User user=new User();
        if(rs.next()){
            user.setUserID(rs.getInt(1));
            user.setSalesRepID(rs.getString(2));
            user.setFirstName(rs.getString(3));
            user.setLastName(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setStatus(rs.getInt(6));
            return user;
        }
        else return null;
    }

    public void updateUser(int id,String firstName,String lastName,String email) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement=connectDB.createStatement();
        statement.executeUpdate("UPDATE users SET first_nameU= '"+firstName+"', last_nameU='"+lastName+"',email='"+email+"' WHERE u_id="+id );
    }





}
