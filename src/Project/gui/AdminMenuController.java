package Project.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AdminMenuController {
    @FXML
    private Button logouButt;
    @FXML
    private Button addSPButt;


    public void logoutOnAction(ActionEvent event){
        Stage stage=(Stage) logouButt.getScene().getWindow();
        stage.close();
    }
    public void addSP(){
        try{
            URL url = new File("src/Project/gui/fxml/addSP.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 564, 540));
            addSPStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void deleteSP(){
        try{
            URL url = new File("src/Project/gui/fxml/deleteSp.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 620, 411));
            addSPStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void editSP() throws IOException {
        try {


            URL url = new File("src/Project/gui/fxml/editSp.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 760, 511));
            addSPStage.show();
        }
        catch (Exception e){
            e.getCause();
        }

    }
    public void addProduct(){
        try {


            URL url = new File("src/Project/gui/fxml/addProduct.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 564, 511));
            addSPStage.show();
        }
        catch (Exception e){
            e.getCause();
        }
    }
    public void deleteProduct(){
        try {


            URL url = new File("src/Project/gui/fxml/deleteProduct.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 820, 410));
            addSPStage.show();
        }
        catch (Exception e){
            e.getCause();
        }
    }
    public void editProduct()
    {
        try {


            URL url = new File("src/Project/gui/fxml/editProduct.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 820, 410));
            addSPStage.show();
        }
        catch (Exception e){
            e.getCause();
        }
    }
    public void previewSales(){
        try {


            URL url = new File("src/Project/gui/fxml/previewSalesAdmin.fxml").toURL();
            Parent root = FXMLLoader.load(url);
            Stage addSPStage=new Stage();
            addSPStage.setTitle("Admin Menu");
            addSPStage.setScene(new Scene(root, 893, 686));
            addSPStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
