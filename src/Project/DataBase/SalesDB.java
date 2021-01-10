package Project.DataBase;

import Project.FileLoader;
import Project.model.Product;
import Project.model.Sales;
import Project.model.SalesRep;
import Project.model.User;

import java.sql.*;

public class SalesDB {

    public ResultSet previewSales() throws SQLException {

        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        ResultSet rs=connectDB.createStatement().executeQuery("SELECT\n" +
                "a.sale_id,a.date,b.product_name,a.quantity,a.final_price,c.user_id,d.u_id\n" +
                "FROM sales a\n" +
                "JOIN products b on a.product_id=b.product_id\n" +
                "JOIN users d on a.u_id=d.u_id\n" +
                "JOIN sales_representative c on d.user_id=c.user_id");
        return  rs;
    }

    public SalesRep getSalesRepDetails(int id) throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();

        Statement statement = null;
        ResultSet resultSet1=null;
        try {
            statement = connectDB.createStatement();
             resultSet1 = statement.executeQuery("SELECT\n" +
                    "a.u_id,b.u_id,b.user_id,c.user_id,c.firstName,c.secondName\n" +
                    "FROM sales a\n" +
                    "JOIN  users b on a.u_id=b.u_id\n" +
                    "JOIN sales_representative c on b.user_id=c.user_id\n" +
                    "WHERE sale_id="+id);
        }finally {
        }
        SalesRep salesRep=new SalesRep();
        if(resultSet1.next()){

            salesRep.setFirstName(resultSet1.getString(5));
            salesRep.setLastNAme(resultSet1.getString(6));
            salesRep.setId(resultSet1.getInt(4));
        }
        return salesRep;

    }

    public ResultSet validateById(int id) throws SQLException {

        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        ResultSet resultSet=null;

        try {
            Statement statement = connectDB.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sales WHERE sale_id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultSet.next()){
            return resultSet;
        }else return null;
    }

    public Product getProductDetails(int id) throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        Statement statement2=null;
        ResultSet resultSet2=null;

        try {
             statement2 = connectDB.createStatement();
            resultSet2 = statement2.executeQuery("SELECT \n" +
                    "a.product_id,a.product_name,a.product_type,a.product_brand,a.product_price,a.product_quantity,b.product_id\n" +
                    "FROM products a JOIN sales b on a.product_id=b.product_id\n" +
                    "WHERE sale_id="+id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Product product=new Product();
        if(resultSet2.next()){
            product.setId(resultSet2.getInt(1));
            product.setProductName(resultSet2.getString(2));
            product.setProductType(resultSet2.getString(3));
            product.setProductBrand(resultSet2.getString(4));
            product.setProductPrice(resultSet2.getString(5));
            product.setProductQuantity(resultSet2.getString(6));
        }
        return product;


    }

    public User getUSerDetails(int id) throws SQLException{
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        Statement statement2=null;
        ResultSet resultSet2=null;
        try {
            statement2 = connectDB.createStatement();
            resultSet2 = statement2.executeQuery("SELECT \n" +
                    "a.u_id,a.first_nameU,a.last_nameU,a.email,b.u_id\n" +
                    "FROM users a\n" +
                    "JOIN sales b on a.u_id=b.u_id\n" +
                    "WHERE sale_id="+id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        User user=new User();

        if(resultSet2.next()){
            user.setUserID(resultSet2.getInt(1));
            user.setFirstName(resultSet2.getString(2));
            user.setLastName(resultSet2.getString(3));
            user.setEmail(resultSet2.getString(4));
        }
        return user;

    }

    public ResultSet populateSales(){

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        FileLoader fileLoader=new FileLoader();
        int srID=fileLoader.readFile();
        ResultSet rs=null;
        try {
             rs = connectDB.createStatement().executeQuery("SELECT\n" +
                    "a.u_id,b.u_id,b.user_id,c.user_id,a.sale_id,a.quantity,a.final_price,a.date,a.product_id,d.product_id,d.product_name\n" +
                    "FROM sales_representative c\n" +
                    "JOIN  users b on c.user_id=b.user_id\n" +
                    "JOIN sales a on a.u_id=b.u_id\n" +
                    "JOIN products d on a.product_id=d.product_id\n" +
                    "WHERE c.user_id=" + srID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;

    }

    public ResultSet getProductPriceAndQuant(int prodId) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement = connectDB.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE product_id=" + prodId);
        return resultSet;

    }

    public void makeSale(int userId,int prodId,int quantity,double finalPrice,int productQuantity) throws SQLException{

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        java.util.Date date=new java.util.Date();

        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        PreparedStatement ps= null;
        try {
            ps = connectDB.prepareStatement("INSERT INTO sales (u_id,product_id,quantity,final_price,date) VALUES(?,?,?,?,?)");
            ps.setInt(1,userId);
            ps.setInt(2,prodId);
            ps.setInt(3,quantity);
            ps.setString(4, String.valueOf(finalPrice));
            ps.setDate(5,sqlDate);
            int i =  ps.executeUpdate();
            Statement statement1=connectDB.createStatement();

            int dab1=productQuantity-quantity;
            statement1.executeUpdate("UPDATE products SET product_quantity = '"+dab1+"' WHERE product_id="+prodId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }





}
