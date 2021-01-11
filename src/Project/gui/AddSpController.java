package Project.gui;

import Project.DataBase.ProductDB;
import Project.DataBase.SalesRepDB;
import Project.model.Product;
import Project.model.SalesRep;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Project.DataBase.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSpController {
    @FXML
    private Button closeButton;
    @FXML
    private Button addSPButton;
    @FXML
    private Label messageLabel;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private PasswordField repeatpwTF;
    @FXML
    private Label confirmPasswordMessage;
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField usernameTF;



    public void addSPOnAction(ActionEvent event){
        if(isValidPassword(passwordTF.getText())){
            if(passwordTF.getText().equals(repeatpwTF.getText())){
                addSP();
            }else{
                confirmPasswordMessage.setText("Password does not match!");
            }
        }
        else{
            confirmPasswordMessage.setText("Password does not match the criteria");
        }
    }


    public void closeButtonOnAction(ActionEvent event){
        Stage stage=(Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void addSP(){
        SalesRep salesRep=new SalesRep();
        SalesRepDB salesRepDB=new SalesRepDB();
        salesRep.setFirstName(firstnameTF.getText());
        salesRep.setLastNAme(lastnameTF.getText());
        salesRep.setUsername(usernameTF.getText());
        if(passwordTF.getText().equals(repeatpwTF.getText())) {
            salesRep.setPassword(passwordTF.getText());
        }
        else messageLabel.setText("Passwords don't match");

        try {
            SalesRep search = salesRepDB.validateSalesRepByName(firstnameTF.getText());
            boolean status;
            if(search==null) {
                status = false;
                try {
                    salesRepDB.addSalesRep(salesRep, status);
                    messageLabel.setText("Sales representative Added!");
                } catch (Exception e) {
                    messageLabel.setText("An error while adding a sales representative has occurred!");
                }
            }else{

                salesRep= salesRepDB.validateByStatus(salesRep.getFirstName());
                if(salesRep==null) {
                    messageLabel.setText("Sales representative already exists!");
                }else{
                    status=true;
                    salesRep.setStatus(1);
                    salesRepDB.addSalesRep(salesRep,status);
                    messageLabel.setText("Sales representative added");
                }
            }

            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static boolean isValidPassword(String password)
    {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=_])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);


        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}
