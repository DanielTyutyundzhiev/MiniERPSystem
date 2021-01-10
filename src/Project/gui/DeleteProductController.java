package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.ProductDB;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Project.model.Product;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteProductController implements Initializable {
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product,String> col_id;
    @FXML
    private TableColumn<Product,String> col_productName;
    @FXML
    private TableColumn<Product,String> col_productType;
    @FXML
    private TableColumn<Product,String> col_productBrand;
    @FXML
    private TableColumn<Product,String> col_productPrice;
    @FXML
    private TableColumn<Product,String> col_productQuantity;
    @FXML
    private TextField idField;
    @FXML
    private Label deleteMessage;
    ObservableList<Product> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductDB productDB = new ProductDB();
        ResultSet rs = null;
        try {
            rs = productDB.updateTable();
            while (rs.next()) {


                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductType(rs.getString(3));
                product.setProductBrand(rs.getString(4));
                product.setProductPrice(rs.getString(5));
                product.setProductQuantity(rs.getString(6));
                oblist.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        table.setItems(oblist);
    }

    public void removeOnAction(ActionEvent event) throws SQLException {
        int id= Integer.parseInt(idField.getText());
        ProductDB pdb=new ProductDB();
        Product product=pdb.validateProductById(id);
        if(product==null){
            deleteMessage.setText("Product not found");
        }else{
            if(pdb.validateByStatus(product.getProductName())==null) {
                deleteMessage.setText("Product not found");
                pdb.removeProduct(id);
                deleteMessage.setText("Product Removed");
            }else {
                deleteMessage.setText("Product not found");
            }
        }


    }
    public void refresh(){
        table.getItems().clear();

        ProductDB productDB = new ProductDB();
        ResultSet rs = null;
        try {
            rs = productDB.updateTable();
            while (rs.next()) {


                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setProductName(rs.getString(2));
                product.setProductType(rs.getString(3));
                product.setProductBrand(rs.getString(4));
                product.setProductPrice(rs.getString(5));
                product.setProductQuantity(rs.getString(6));
                oblist.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        table.setItems(oblist);
    }
}
