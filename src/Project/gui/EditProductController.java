package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.ProductDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Project.model.Product;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product, String> col_id;
    @FXML
    private TableColumn<Product, String> col_productName;
    @FXML
    private TableColumn<Product, String> col_productType;
    @FXML
    private TableColumn<Product, String> col_productBrand;
    @FXML
    private TableColumn<Product, String> col_productPrice;
    @FXML
    private TableColumn<Product, String> col_productQuantity;
    @FXML
    private TextField idField;
    @FXML
    private Label deleteMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Button editButton;
    @FXML
    private Button close;


    ObservableList<Product> oblist = FXCollections.observableArrayList();

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void viewAll() {
        table.getItems().clear();
        idField.clear();
        deleteMessage.setText("");
        nameField.clear();
        typeField.clear();
        brandField.clear();
        priceField.clear();
        quantityField.clear();
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

    public void editSPonAction(ActionEvent event) {
        ProductDB productDB = new ProductDB();
        try {
            productDB.updateProduct(Integer.parseInt(idField.getText()), nameField.getText(), typeField.getText(), brandField.getText(), priceField.getText(), quantityField.getText());
            deleteMessage.setText("Product updated successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            deleteMessage.setText("Something went wrong..");
        }

    }

    public void updateTable() throws SQLException {
        table.getItems().clear();
        ProductDB pdb = new ProductDB();
        Product product = pdb.getProduct(Integer.parseInt(idField.getText()));
        if(product==null){
            deleteMessage.setText("Product doesn't exist");
        }else
        {
            Product product1=pdb.validateByStatus(product.getProductName());
            if(product1==null){
                oblist.add(product);

                nameField.setText(product.getProductName());
                typeField.setText(product.getProductType());
                brandField.setText(product.getProductBrand());
                priceField.setText(product.getProductPrice());
                quantityField.setText(product.getProductQuantity());

                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
                col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
                col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
                col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
                table.setItems(oblist);
            }
            else{
                deleteMessage.setText("Product doesn't exist");
            }
        }


    }

    public void clear() {
        table.getItems().clear();
        idField.clear();
        deleteMessage.setText("");
        nameField.clear();
        typeField.clear();
        brandField.clear();
        priceField.clear();
        quantityField.clear();

    }


}
