package Project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Project.DataBase.DatabaseConnection;
import Project.model.Product;
import Project.DataBase.ProductDB;

import java.sql.Connection;
import java.sql.SQLException;

public class AddProductController {
    @FXML
    private TextField prnameField;
    @FXML
    private TextField prtypeField;
    @FXML
    private TextField prbrandField;
    @FXML
    private TextField prpriceField;
    @FXML
    private TextField prquantityField;
    @FXML
    private Button closeBtn;
    @FXML
    private Label messageLabel;

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void addProduct() throws SQLException {

        Product product=new Product();
        ProductDB productDB = new ProductDB();

        product.setProductName(prnameField.getText());
        product.setProductType(prtypeField.getText());
        product.setProductBrand(prbrandField.getText());
        product.setProductPrice(prpriceField.getText());
        product.setProductQuantity(prquantityField.getText());

        try {
            Product search = productDB.validateProductByName(prnameField.getText());
            boolean status;
            if(search==null){
                status=false;
                try {
                productDB.addProduct(product,status);
                messageLabel.setText("Product Added!");
                }catch (Exception e){
                    messageLabel.setText("An error while adding a product has occurred!");
                }
            }else{

         product= productDB.validateByStatus(product.getProductName());
               if(product==null) {
                   messageLabel.setText("Product already exists!");
               }else{
                   status=true;
                   productDB.addProduct(product,status);
               }
            }


        }catch (Exception e){
            e.getCause();
        }




        prnameField.clear();
        prtypeField.clear();
        prbrandField.clear();
        prpriceField.clear();
        prquantityField.clear();
    }
}
