package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.ProductDB;
import Project.DataBase.SalesRepDB;
import Project.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Project.model.SalesRep;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditSpController implements Initializable {

    @FXML
    private TableView<SalesRep> table;
    @FXML
    private TableColumn<SalesRep,String> col_id;
    @FXML
    private TableColumn<SalesRep,String> col_firstName;
    @FXML
    private TableColumn<SalesRep,String> col_lastName;
    @FXML
    private TableColumn<SalesRep,String> col_UserName;
    @FXML
    private TableColumn<SalesRep,String> col_Password;
    @FXML
    private TextField idField;
    @FXML
    private Label deleteMessage;
    @FXML
    private Button editButton;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private Button close;
    ObservableList<SalesRep> oblist= FXCollections.observableArrayList();
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void viewAll() {
        table.getItems().clear();
        idField.clear();
        deleteMessage.setText("");
        firstnameField.clear();
        lastnameField.clear();
        usernameField.clear();
        PasswordField.clear();


        SalesRepDB salesRepDB = new SalesRepDB();
        ResultSet rs = null;

        try {
            rs = salesRepDB.updateTable();

            while(rs.next()) {
                SalesRep salesRep=new SalesRep();
                salesRep.setId(rs.getInt(1));
                salesRep.setFirstName(rs.getString(2));
                salesRep.setLastNAme(rs.getString(3));
                salesRep.setUsername(rs.getString(4));
                salesRep.setPassword(rs.getString(5));
                oblist.add(salesRep);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastNAme"));
        col_UserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
        table.setItems(oblist);
    }


    public void editSPonAction(ActionEvent event){

        SalesRepDB salesRepDB=new SalesRepDB();
        try {
           salesRepDB.updateSalesRep(Integer.parseInt(idField.getText()), firstnameField.getText(), lastnameField.getText(), usernameField.getText(), PasswordField.getText());
            deleteMessage.setText("Sales representative updated successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            deleteMessage.setText("Something went wrong..");
        }



    }
    public void updateTable() throws SQLException {

        table.getItems().clear();

        SalesRepDB salesRepDB=new SalesRepDB();

        SalesRep salesRep=salesRepDB.validateSalesRepById(Integer.parseInt(idField.getText()));

        if(salesRep==null){
            deleteMessage.setText("Sales representative doesn't exist");
        }else{
            SalesRep salesRep1=salesRepDB.validateByStatus(salesRep.getFirstName());
            if(salesRep1==null){
                oblist.add(salesRep);

                firstnameField.setText(salesRep.getFirstName());
                lastnameField.setText(salesRep.getLastNAme());
                usernameField.setText(salesRep.getUsername());
                PasswordField.setText(salesRep.getPassword());

                col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastNAme"));
                col_UserName.setCellValueFactory(new PropertyValueFactory<>("username"));
                col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
                table.setItems(oblist);
            }
            else{
                deleteMessage.setText("Sales representative doesn't exist");
            }
        }

    }


    public void clear() {
        table.getItems().clear();
        idField.clear();
        deleteMessage.setText("");
        firstnameField.clear();
        lastnameField.clear();
        usernameField.clear();
        PasswordField.clear();

    }
}
