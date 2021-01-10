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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PreviewSalesUserController implements Initializable {
    @FXML
    private TableView<Sales> table;
    @FXML
    private TableColumn<Sales, String> col_salesID;
    @FXML
    private TableColumn<Sales, String> col_date;
    @FXML
    private TableColumn<Sales, String> col_productName;
    @FXML
    private TableColumn<Sales, String> col_Quantity;
    @FXML
    private TableColumn<Sales, String> col_finalPrice;

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

    ObservableList<Sales> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SalesDB salesDB=new SalesDB();

        ResultSet rs=salesDB.populateSales();
        try {
            while (rs.next()) {
                Sales sales = new Sales();
                sales.setSalesID(rs.getInt(5));
                sales.setQuantity(rs.getString(6));
                sales.setFinalPrice(rs.getString(7));
                sales.setDate(String.valueOf(rs.getDate(8)));
                sales.setProductName(rs.getString(11));
                oblist.add(sales);

                col_salesID.setCellValueFactory(new PropertyValueFactory<>("salesID"));
                col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
                col_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                col_finalPrice.setCellValueFactory(new PropertyValueFactory<>("finalPrice"));
                table.setItems(oblist);

            }
        }catch (Exception e){
            System.out.println("Error with query 1");
        }
    }

    public void previewDetails() throws SQLException {
        if (!idField.getText().isBlank()) {
            int id = Integer.parseInt(idField.getText());
            SalesDB sdb = new SalesDB();
            ResultSet validate = sdb.validateById(id);

            if(validate==null) {
                System.out.println("No such Sale");
            }else{

                User user=sdb.getUSerDetails(id);
                Product product=sdb.getProductDetails(id);

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
