package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.InteractionServer;
import model.inquiryMySQL;

import java.io.IOException;


public class Controller {
    InteractionServer interactionServer = new InteractionServer();
    inquiryMySQL inquiryMySQL = new inquiryMySQL();

    @FXML
    public TextArea fieldText = new TextArea();
    @FXML
    public TextArea enterText = new TextArea();
    @FXML
    public Button enterBut = new Button();
    @FXML
    public Button butConnect = new Button();
    @FXML
    public TextArea yourName = new TextArea();
    @FXML
    public Button registerNewUser = new Button();
    @FXML
    public Pane enterPane = new Pane();
    @FXML
    public Button butRegister = new Button();
    @FXML
    public TextArea con_Login = new TextArea();
    @FXML
    public PasswordField con_Pas = new PasswordField();
    @FXML
    public Button butExit = new Button();
    @FXML
    public Button backMenu = new Button();
    @FXML
    public ListView<String> listUsers = new ListView<String>();

    // Список пользователей находящихся в чате
    public ObservableList<String> items = FXCollections.observableArrayList();


    public void registerNewUser(){
        backMenu.setVisible(true);
        registerNewUser.setVisible(false);

        butConnect.setVisible(false);
        butRegister.setVisible(true);
    }

    public void backMenu(){
        backMenu.setVisible(false);
        registerNewUser.setVisible(true);

        butConnect.setVisible(true);
        butRegister.setVisible(false);
    }
    public void buttonRegister(){
        String login = con_Login.getText();
        String password = con_Pas.getText();

        backMenu.setVisible(false);
        registerNewUser.setVisible(true);

        butConnect.setVisible(true);
        butRegister.setVisible(false);

        inquiryMySQL.createUser(login,password);
    }



    public void enter(){
            String s = enterText.getText();
            interactionServer.enter(yourName.getText() + " : " + s + "\n");
            enterText.setText("");
    }

    
    public void buttonConnection() throws IOException {
        
        String login = con_Login.getText();
        String password = con_Pas.getText();
        boolean isAccess = inquiryMySQL.connectUser(login, password);
        
        if (isAccess == true) {
            interactionServer.connectServer(fieldText, items, listUsers, login);
            inquiryMySQL.addUserList(con_Login.getText());
            inquiryMySQL.checkUsersList(items, listUsers);
            inquiryMySQL.updateListUsers();
            yourName.setText(login);

            enterPane.setDisable(true);
            enterPane.setVisible(false);

        }
    }

    public void buttonExit(){
            inquiryMySQL.deleteUserList(con_Login.getText());
            System.exit(1);
    }

}

