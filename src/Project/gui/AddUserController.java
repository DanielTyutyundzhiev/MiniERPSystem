package Project.gui;

import Project.DataBase.UserDB;
import Project.FileLoader;
import Project.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Project.DataBase.DatabaseConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUserController {
    @FXML
    private TextField firstnameTF;
    @FXML
    private TextField lastnameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private Label messageLabel;

    public void addUser() throws IOException, SQLException {
        String fisrtName=firstnameTF.getText();
        String lastName=lastnameTF.getText();
        String email=emailTF.getText();
       FileLoader fileLoader=new FileLoader();
        int id= fileLoader.readFile();
        UserDB userDB=new UserDB();
        User userSearch=userDB.validateUserByEmail(email);
        if(userSearch==null) {

            userDB.addUser(id, fisrtName, lastName, email);
            messageLabel.setText("User Added!");
        }else{
            if(userSearch.getStatus()==1){
                messageLabel.setText("User Already in the list!");
            }else{
                userDB.updateStatus(userSearch.getUserID(),1);
                messageLabel.setText("User Added!");
            }
        }

    }
}
