package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.SalesDB;
import Project.DataBase.SalesRepDB;
import Project.DataBase.UserDB;
import Project.FileLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

public class EditUserController implements Initializable {
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
    @FXML
    private Button editButton;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;

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
    public void editUser(){
        int id= Integer.parseInt(idField.getText());
        UserDB user=new UserDB();
        try {
            user.updateUser(id, firstnameField.getText(), lastnameField.getText(), emailField.getText());
            deleteMessage.setText("User updated successfully!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            deleteMessage.setText("Something went wrong..");
        }

    }

    public void updateTable() throws SQLException {
        table.getItems().clear();
        UserDB userDB=new UserDB();

        User user=userDB.validateUserById(Integer.parseInt(idField.getText()));
        FileLoader fileLoader= new FileLoader();
        int srID=fileLoader.readFile();

        if(user==null){
            deleteMessage.setText("User doesn't exist");
        }else{
            if(user.getStatus()==1&&Integer.parseInt(user.getSalesRepID())==srID){
                oblist.add(user);
                firstnameField.setText(user.getFirstName());
                lastnameField.setText(user.getLastName());
                emailField.setText(user.getEmail());

                col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
                col_salesRepID.setCellValueFactory(new PropertyValueFactory<>("salesRepID"));
                col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
                col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
                col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

                table.setItems(oblist);

            }
            else{
                deleteMessage.setText("User doesn't exist");
            }

        }


    }

    public void viewAll(){
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
