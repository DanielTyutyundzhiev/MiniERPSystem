package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.ProductDB;
import Project.DataBase.SalesDB;
import Project.DataBase.UserDB;
import Project.model.Sales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Project.model.Product;
import Project.model.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MakeSaleController implements Initializable {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User,String> col_idUser;
    @FXML
    private TableColumn<User,String> col_firstName;
    @FXML
    private TableColumn<User,String> col_lastName;
    @FXML
    private TableColumn<User,String> col_email;

    @FXML
    private TableView<Product> table2;
    @FXML
    private TableColumn<Product,String> col_id2;
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
    private Label deleteMessage;
    @FXML
    private ComboBox<Integer> comboUserId;
    @FXML
    private ComboBox<Integer> comboProductId;
    @FXML
    private TextField fieldQuantity;
    @FXML
    private Label messageLabel;

    ObservableList<User> oblist= FXCollections.observableArrayList();
    ObservableList<Product> oblist1= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDB userDB=new UserDB();

        ResultSet rs=null;
        List<Integer> listUserId=new ArrayList<>();
        try {
            rs = userDB.updateTable();
            while (rs.next()) {

                User user =new User();
                user.setUserID(rs.getInt(1));
                listUserId.add(rs.getInt(1));
                user.setSalesRepID(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setEmail(rs.getString(5));
                oblist.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_idUser.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(oblist);
        //-----------------------------------------------------------

        ProductDB productDB = new ProductDB();  
        ResultSet rs1 = null;
        List<Integer> listId=new ArrayList<>();

        try {
            rs1 = productDB.updateTable();
            while (rs1.next()) {


                Product product = new Product();
                product.setId(rs1.getInt(1));
                listId.add(rs1.getInt(1));
                product.setProductName(rs1.getString(2));
                product.setProductType(rs1.getString(3));
                product.setProductBrand(rs1.getString(4));
                product.setProductPrice(rs1.getString(5));
                product.setProductQuantity(rs1.getString(6));
                oblist1.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_productType.setCellValueFactory(new PropertyValueFactory<>("productType"));
        col_productBrand.setCellValueFactory(new PropertyValueFactory<>("productBrand"));
        col_productPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        col_productQuantity.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        table2.setItems(oblist1);
        ObservableList obList = FXCollections.observableList(listId);
        ObservableList obList1 = FXCollections.observableList(listUserId);
        comboProductId.setItems(obList);
        comboUserId.setItems(obList1);

    }

    public void sell(){

        int userId=comboUserId.getValue();
        int productId=comboProductId.getValue();
        int productQuantity= Integer.parseInt(fieldQuantity.getText());

        String price="";
        int quantity=0;
        double finalPrice=0;

        SalesDB salesDB=new SalesDB();
        try {
            ResultSet rs=salesDB.getProductPriceAndQuant(productId);

            if(rs.next()){
                price=rs.getString(5);
                quantity=rs.getInt(6);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(productQuantity>quantity){
            deleteMessage.setText("Insufficient quantity");
        }else{
            finalPrice=Double.parseDouble(price)*productQuantity;
            SalesDB salesDB1=new SalesDB();
            try {
                salesDB1.makeSale(userId,productId,productQuantity,finalPrice,quantity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            deleteMessage.setText("Sale successfully made!");
        }






    }





}
