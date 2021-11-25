

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
    public HashMap<String, ClientHandler> users = new HashMap();
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Server server = new Server(serverSocket);
            server.startServer();
        } catch (Exception var3) {
            var3.printStackTrace();
            System.err.println("Fehler. Der Server konnte nicht gestartet werden.");
        }

    }

    public void singleMessage(String user, String message) {
        this.users.get(user).writer.println(message);
    }

    public void messageForAllUsers(String message) {

        for (ClientHandler clientHandler : this.users.values()) {
            clientHandler.writer.println(message);
        }

    }


    public boolean addClient(ClientHandler clientHandler) {
        if (this.users.containsKey(clientHandler.username)) {
            return false;
        } else {
            this.users.put(clientHandler.username, clientHandler);
            return true;
        }
    }

    public boolean checkName(String username) {
        return !this.users.containsKey(username);
    }

    public void startServer() {
        while(true) {
            try {
                if (!this.serverSocket.isClosed()) {
                    Socket socket = this.serverSocket.accept();
                    System.out.println("A new client has connected");
                    ClientHandler clientHandler = new ClientHandler(socket, this);
                    Thread thread = new Thread(clientHandler);
                    thread.start();
                    continue;
                }
            } catch (IOException var4) {
                var4.printStackTrace();
            }

            return;
        }
    }

}