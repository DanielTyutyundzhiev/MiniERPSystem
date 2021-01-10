package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.UserDB;
import Project.FileLoader;
import Project.model.Product;
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
import Project.model.User;

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

public class DeleteUserController implements Initializable {
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User,String> col_userID;
    @FXML
    private TableColumn<User,String> col_salesRepID;
    @FXML
    private TableColumn<User,String> col_firstName;
    @FXML
    private TableColumn<User,String> col_lastName;
    @FXML
    private TableColumn<User,String> col_email;
    @FXML
    private TextField idField;
    @FXML
    private Label deleteMessage;

    ObservableList<User> oblist= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserDB userDB=new UserDB();

        ResultSet rs=null;

        try {
            rs = userDB.updateTable();
            while (rs.next()) {

                User user =new User();
                user.setUserID(rs.getInt(1));
                user.setSalesRepID(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setEmail(rs.getString(5));
                oblist.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_salesRepID.setCellValueFactory(new PropertyValueFactory<>("salesRepID"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(oblist);
    }
    public void removeOnAction(ActionEvent event) throws SQLException {
        String id=idField.getText();
        UserDB userDB=new UserDB();
        FileLoader fileLoader=new FileLoader();
        int srID=fileLoader.readFile();
        User user=userDB.validateUserById(Integer.parseInt(id));
        if(user==null){
            deleteMessage.setText("User not Found!");
        }else{
            if(user.getStatus()==1&&Integer.parseInt(user.getSalesRepID())==srID){
                userDB.updateStatus(user.getUserID(),0);
                deleteMessage.setText("User Removed");
            }else{
                deleteMessage.setText("User not Found!");
            }
        }
    }
    public void updateTable(){

        table.getItems().clear();
        UserDB userDB=new UserDB();

        ResultSet rs=null;

        try {
            rs = userDB.updateTable();
            while (rs.next()) {

                User user =new User();
                user.setUserID(rs.getInt(1));
                user.setSalesRepID(rs.getString(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setEmail(rs.getString(5));
                oblist.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_salesRepID.setCellValueFactory(new PropertyValueFactory<>("salesRepID"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.setItems(oblist);

    }
}
