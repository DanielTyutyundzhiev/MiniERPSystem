package Project.DataBase;

import Project.DataBase.DatabaseConnection;
import Project.model.Product;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class ProductDB {
    public void addProduct(Product product,boolean status) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        if(!status) {
            try {


                String insertFields = "INSERT INTO products(product_name,product_type,product_brand,product_price,product_quantity,status) VALUES ('" + product.getProductName() + "','" + product.getProductType() + "','" + product.getProductBrand() + "','" + product.getProductPrice() + "', '" + product.getProductQuantity() + "',+1)";
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertFields);
            } finally {
                connectDB.close();
            }
        }
        else {
            int id=product.getId();
           String updateStatus= "UPDATE products SET product_name= '"+product.getProductName()+"', product_type='"+product.getProductType()+"', product_brand = '"+product.getProductBrand()+"', product_price = '"+product.getProductPrice()+"', product_quantity = '"+product.getProductQuantity()+"', status ='" +1+"' WHERE product_id="+id;
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(updateStatus);
        }
    }

    public Product validateByStatus(String name) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        String query="SELECT * FROM products WHERE product_name='"+name+"'";
        ResultSet rs = connectDB.createStatement().executeQuery(query);
        Product product=new Product();
        try {
            if(rs.next()) {
                if (rs.getInt(7) == 0) {
                    product.setId(rs.getInt(1));
                    product.setProductName(rs.getString(2));
                    product.setProductType(rs.getString(3));
                    product.setProductBrand(rs.getString(4));
                    product.setProductPrice(rs.getString(5));
                    product.setProductQuantity(rs.getString(6));
                    product.setStatus(1);
                    return product;
                } else {
                    return null;
                }
            }else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public Product validateProductByName(String name) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM products WHERE product_name= '" + name+"'");
        if(rs.next()){
            Product product=new Product();
            product.setId(rs.getInt(1));
            product.setProductName(rs.getString(2));
            product.setProductType(rs.getString(3));
            product.setProductBrand(rs.getString(4));
            product.setProductPrice(rs.getString(5));
            product.setProductQuantity(rs.getString(6));

            return product;
        }
        else return null;
    }
    public Product validateProductById(int id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM products WHERE product_id="+id);
        if(rs.next()){
            Product product=new Product();

            product.setProductName(rs.getString(2));
            product.setProductType(rs.getString(3));
            product.setProductBrand(rs.getString(4));
            product.setProductPrice(rs.getString(5));
            product.setProductQuantity(rs.getString(6));
            return product;
        }
        else return null;
    }
    public void updateProduct(int id,String productName,String productType,String productBrand,String productPrice,String productQuantity) throws SQLException {

        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Statement statement=connectDB.createStatement();
        statement.executeUpdate("UPDATE products SET product_name= '"+productName+"', product_type='"+productType+"', product_brand = '"+productBrand+"', product_price = '"+productPrice+"', product_quantity = '"+productQuantity+"' WHERE product_id="+id );



    }
    public Product getProduct(int id) throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();
        ResultSet rs=connectDB.createStatement().executeQuery("SELECT * FROM products WHERE product_id="+id);
        Product product=new Product();
        if(rs.next()) {
            product.setId(rs.getInt(1));
            product.setProductName(rs.getString(2));
            product.setProductType(rs.getString(3));
            product.setProductBrand(rs.getString(4));
            product.setProductPrice(rs.getString(5));
            product.setProductQuantity(rs.getString(6));
            product.setStatus(1);
            return product;
        }else return null;

    }

    public ResultSet updateTable() throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();

            ResultSet rs=connectDB.createStatement().executeQuery("SELECT * FROM products WHERE status="+1);
            return rs;

    }

    public void removeProduct(int id) throws SQLException {
        DatabaseConnection connection=new DatabaseConnection();
        Connection connectDB=connection.getConnection();

        String insertToDelete="UPDATE products SET status="+0+" WHERE product_id="+id;

        Statement statement=connectDB.createStatement();
        statement.executeUpdate(insertToDelete);

    }
}
