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

public class DeleteSpController implements Initializable {
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
    private TextField idField;
    @FXML
    private Label deleteMessage;
    @FXML
    private Button close;
    ObservableList<SalesRep> oblist= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SalesRepDB salesRepDB=new SalesRepDB();
        ResultSet rs = null;

        try {
            rs = salesRepDB.updateTable();
            while (rs.next()) {


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
        table.setItems(oblist);
    }
    public void closeButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    public void removeOnAction(ActionEvent event) throws SQLException {
        int id= Integer.parseInt(idField.getText());
        SalesRepDB salesRepDB=new SalesRepDB();
        SalesRep salesRep=salesRepDB.validateSalesRepById(id);
        if(salesRep==null){
            deleteMessage.setText("Sales Representative not found");
        }else{
            if(salesRepDB.validateByStatus(salesRep.getFirstName())==null) {
                deleteMessage.setText("Sales Representative not found");
                salesRepDB.removeSalesRep(id);
                deleteMessage.setText("Sales Representative Removed");
            }else {
                deleteMessage.setText("Sales Representative not found");
            }
        }

    }
    public void updateTable(){
        table.getItems().clear();
        deleteMessage.setText("");

        SalesRepDB salesRepDB=new SalesRepDB();
        ResultSet rs = null;

        try {
            rs = salesRepDB.updateTable();
            while (rs.next()) {


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
        table.setItems(oblist);

    }

}
