package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(int port) throws IOException {

        ServerSocket service = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = service.accept();
                System.out.println("Новое соединение: " + socket.getInetAddress());
                SocketDispatcher socketDispatcher = new SocketDispatcher(socket);
                socketDispatcher.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            service.close();
        }
    }

    public static void main(String[] args) {
        try {
            int port = 2074;
            new Server(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
