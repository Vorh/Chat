package model;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

public class InteractionServer implements Runnable {

    private DataInputStream inStream;
    private DataOutputStream outStream;
    private Socket socket;
    private String login;
    private String ip = "localhost";
    private int port = 2074;

    private TextArea text;
    private ListView<String> listUsers;
    private   ObservableList<String> items;



    // Читает сообщение с сервера и добавляет в чат
    public void run() {
        try {
            while (true) {
                String line = inStream.readUTF();
                text.appendText(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void connectServer(TextArea textArea, ObservableList<String> items, ListView<String> listUsers, String login) throws IOException {
        text = textArea;
        this.login = login;
        this.listUsers = listUsers;
        this.items = items;
        System.out.println("Соединение установлено");
        Socket sockets = new Socket("localhost", port);
        socket = sockets;

        try {
            inStream = new DataInputStream(new BufferedInputStream(sockets.getInputStream()));
            outStream = new DataOutputStream(new BufferedOutputStream(sockets.getOutputStream()));

            // Запускаем новый поток для чтения сообщений с сервера
            (new Thread(this)).start();

        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (outStream != null) outStream.close();
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException ex3) {
                ex3.printStackTrace();
            }
        }

    }

    // Отправка сообщения на сервер
    public void enter(String message ) {
        try {
            outStream.writeUTF(message);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
