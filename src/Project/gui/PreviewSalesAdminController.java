package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.SalesDB;
import Project.model.Product;
import Project.model.SalesRep;
import Project.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Project.model.Sales;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PreviewSalesAdminController implements Initializable {

    @FXML
    private TableView<Sales> table;
    @FXML
    private TableColumn<Sales,String> col_salesID;
    @FXML
    private TableColumn<Sales,String> col_date;
    @FXML
    private TableColumn<Sales,String> col_productName;
    @FXML
    private TableColumn<Sales,String> col_Quantity;
    @FXML
    private TableColumn<Sales,String> col_finalPrice;
    @FXML
    private TableColumn<Sales,String> col_salesRepID;
    @FXML
    private TableColumn<Sales,String> col_UserID;
    @FXML
    private Label spId;
    @FXML
    private Label spFirstName;
    @FXML
    private Label spLastName;
    @FXML
    private Label productId;
    @FXML
    private Label productName;
    @FXML
    private Label productType;
    @FXML
    private Label productBrand;
    @FXML
    private Label productPrice;
    @FXML
    private Label productQuantity;
    @FXML
    private Label userId;
    @FXML
    private Label userFirstName;
    @FXML
    private Label userLastName;
    @FXML
    private Label userEmail;
    @FXML
    private TextField idField;

    ObservableList<Sales> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SalesDB salesDB=new SalesDB();
        ResultSet resultSet = null;

        try {
             resultSet=salesDB.previewSales();
            while (resultSet.next()){
                Sales sales=new Sales();
                sales.setSalesID(resultSet.getInt(1));
                sales.setDate(String.valueOf(resultSet.getDate(2)));
                sales.setProductName(resultSet.getString(3));
                sales.setQuantity(resultSet.getString(4));
                sales.setFinalPrice(resultSet.getString(5));
                sales.setSalesRepID(resultSet.getInt(6));
                sales.setUserID(resultSet.getInt(7));

                oblist.add(sales);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        col_salesID.setCellValueFactory(new PropertyValueFactory<>("salesID"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
        col_salesRepID.setCellValueFactory(new PropertyValueFactory<>("salesRepID"));
        col_UserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        table.setItems(oblist);

    }
    public void previewDetails() throws SQLException {


        if(!idField.getText().isBlank()){
            int id= Integer.parseInt(idField.getText());

            SalesDB salesDB=new SalesDB();
            ResultSet validate=salesDB.validateById(id);

            if(validate==null){
                System.out.println("Sale doesnt exist");
            }else{
                SalesRep salesRep=salesDB.getSalesRepDetails(id);
                Product product=salesDB.getProductDetails(id);
                User user=salesDB.getUSerDetails(id);

                spId.setText(String.valueOf(salesRep.getId()));
                spFirstName.setText(salesRep.getFirstName());
                spLastName.setText(salesRep.getLastNAme());


                productId.setText(String.valueOf(product.getId()));
                productName.setText(product.getProductName());
                productType.setText(product.getProductType());
                productBrand.setText(product.getProductBrand());
                productPrice.setText(product.getProductPrice());
                productQuantity.setText(product.getProductQuantity());

                userId.setText(String.valueOf(user.getUserID()));
                userFirstName.setText(user.getFirstName());
                userLastName.setText(user.getLastName());
                userEmail.setText(user.getEmail());



            }

        }
    }

}
