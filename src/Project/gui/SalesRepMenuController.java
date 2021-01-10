package Project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class SalesRepMenuController {
    @FXML
    private Button logoutButton;

    public void logoutOnAction(ActionEvent event){
        Stage stage=(Stage) logoutButton.getScene().getWindow();
        stage.close();

    }
    public void addUser(){
        try {


            URL url = new File("src/Project/gui/fxml/addUser.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Add User");
            addSPStage.setScene(new Scene(root, 600, 577));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void deleteUser(){
        try {


            URL url = new File("src/Project/gui/fxml/deleteUser.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Delete User");
            addSPStage.setScene(new Scene(root, 800, 400));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void editUser(){
        try {


            URL url = new File("src/Project/gui/fxml/editUser.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Edit User");
            addSPStage.setScene(new Scene(root, 800, 400));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void makeSale(){
        try {


            URL url = new File("src/Project/gui/fxml/makeSale.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Make a Sale");
            addSPStage.setScene(new Scene(root, 800, 530));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void previewSales(){
        try {


            URL url = new File("src/Project/gui/fxml/previewSalesUser.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Preview Sales");
            addSPStage.setScene(new Scene(root, 740, 600));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void cancelOnAction(ActionEvent event) {
        Stage stage=(Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
}
