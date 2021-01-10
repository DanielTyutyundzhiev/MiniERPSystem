package Project.gui;

import Project.DataBase.DatabaseConnection;
import Project.DataBase.LoginDB;
import Project.FileLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    int spID;

    @FXML
    private Button cancel;
    @FXML
    private Label labelMessage;
    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private CheckBox isSalesRep;

    public void loginButton(javafx.event.ActionEvent event) throws SQLException {
        labelMessage.setText("Trying to login...");
        if(username.getText().isBlank()==false&&password.getText().isBlank()==false){
            labelMessage.setText("Trying to login...");
            validateLogin();

        }else{
            labelMessage.setText("Please enter username and password..");
        }




    }


    public void cancelOnAction(javafx.event.ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }
    public void validateLogin() throws SQLException {
        String username1=username.getText();
        String password1=password.getText();
        LoginDB loginDB=new LoginDB();
        String loginResult=loginDB.validateLogin(username1,password1);
        if(loginResult.equals("admin")&&loginResult!=null){
            labelMessage.setText("Successfully logged in!");
            createAdminMenu();
            clear();
        }else if(loginResult.equals("salesRep")&&loginResult!=null){
            labelMessage.setText("Successfully logged in!");
            salesRepMenu();
            clear();
            int srId= loginDB.getId(username1,password1);
            FileLoader file=new FileLoader();
            file.writeFile(srId);



        }else{
            labelMessage.setText("Wrong credentials!");
        }
    }
    public void salesRepMenu(){
        try{
            URL url = new File("src/Project/gui/fxml/salesRepMenu.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 600, 400));
            addSPStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAdminMenu(){
        try{
            URL url = new File("src/Project/gui/fxml/adminMenu.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 600, 400));
            addSPStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void clear(){
        username.clear();
        password.clear();
    }
}
