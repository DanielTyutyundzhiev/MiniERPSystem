package Project.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDB {
    public String validateLogin(String usernameDB,String passwordDB) throws SQLException {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        String verifyLoginAdmin = "SELECT * FROM admin_account WHERE username = '" + usernameDB + "' AND password ='" + passwordDB + "'";
        String verifyLoginSalesRep = "SELECT * FROM sales_representative WHERE username = '" + usernameDB + "' AND password ='" + passwordDB + "'";


        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginAdmin);
            if (queryResult.next()) {
                return "admin";
            }

            ResultSet queryResult1 = statement.executeQuery(verifyLoginSalesRep);
            if (queryResult1.next()&&queryResult1.getInt(6)==1) {
                return "salesRep";
            } else return "notfound";
        }finally {
            connectDB.close();
        }

    }
    public int getId(String usernameDB, String passwordDB) throws SQLException {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
       try {


           String verifyLoginSalesRep = "SELECT * FROM sales_representative WHERE username = '" + usernameDB + "' AND password ='" + passwordDB + "'";
           Statement statement = connectDB.createStatement();
           ResultSet queryResult = statement.executeQuery(verifyLoginSalesRep);
           int id=0;
           if(queryResult.next()) {
               id = queryResult.getInt(1);
           }
           return id;
       }finally {
           connectDB.close();
       }


    }
}
