

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public final Server SERVER;
    public final Socket SOCKET;
    public String username;
    public BufferedReader reader;
    public PrintWriter writer;

    public ClientHandler(Socket socket, Server server) {
        this.SERVER = server;
        this.SOCKET = socket;

        try {
            this.writer = new PrintWriter(socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = this.reader.readLine();
            if (name != null && !name.equals("") && server.checkName(name)) {
                this.username = name;
                this.SERVER.messageForAllUsers("Server: " + name + " joined the chat room.");
                this.SERVER.addClient(this);
                this.writer.println("Server: You joined the chat room.");
            } else {
                name = this.newUsername();
                this.username = name;
                this.SERVER.messageForAllUsers("Server: " + name + " joined the chat room.");
                this.SERVER.addClient(this);
                this.writer.println("Server: You joined the chat room.");
            }
        } catch (Exception var4) {
            this.closeEverything(socket, this.reader, this.writer);
            var4.printStackTrace();
        }

    }

    public String newUsername() {
        try {
            String name;
            do {
                this.writer.println("SERVER: This username was not accepted. Please enter a new one");
                name = this.reader.readLine();
            } while(name.isEmpty() || name.isBlank() || name == null || !this.SERVER.checkName(name));

            return name;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public void run() {
        while(true) {
            if (this.SOCKET.isConnected()) {
                try {
                    String messageFromClient = this.reader.readLine();
                    if (messageFromClient == null) {
                        this.closeEverything(this.SOCKET, this.reader, this.writer);
                    } else if (!messageFromClient.toLowerCase().matches("^[a-zA-Z0-9!@#$%^&*)(]+:\\s\\bbye\\b")) {
                        int i = messageFromClient.indexOf(" ");
                        String command = "";
                        String message = "";
                        if (!messageFromClient.equals("") && "/".equals(String.valueOf(messageFromClient.charAt(0))) && i > -1) {
                            command = messageFromClient.substring(0, i);
                            message = messageFromClient.substring(i + 1);
                        } else {
                            command = messageFromClient;
                        }

                        if (!messageFromClient.equals("") && (String.valueOf(messageFromClient.charAt(0)).equals("/") || "bye".equalsIgnoreCase(messageFromClient))) {
                            String var5 = command.toUpperCase();
                            byte var6 = -1;
                            switch(var5.hashCode()) {
                                case 66254:
                                    if (var5.equals("BYE")) {
                                        var6 = 1;
                                    }
                                    break;
                                case 1476818:
                                    if (var5.equals("/MSG")) {
                                        var6 = 2;
                                    }
                                    break;
                                case 45619184:
                                    if (var5.equals("/HELP")) {
                                        var6 = 3;
                                    }
                                    break;
                            }

                            switch(var6) {
                                case 1:
                                    this.SERVER.messageForAllUsers(this.username + " has left the Chat.");
                                    this.SERVER.users.remove(this.username);
                                    this.closeEverything(this.SOCKET, this.reader, this.writer);
                                case 2:
                                    this.privateMessage(message);
                                    continue;
                                case 3:
                                    this.SERVER.singleMessage(this.username, "/HELP : Shows all commands.");
                                    this.SERVER.singleMessage(this.username, "/MSG <username> : Sends a privat message to a single user.");
                                    this.SERVER.singleMessage(this.username, "BYE : Leave the chatroom.");
                                    continue;
                                default:
                                    this.SERVER.singleMessage(this.username, "Invalid Command");
                                    continue;
                            }
                        }

                        this.SERVER.messageForAllUsers(this.username + ": " + messageFromClient);
                        continue;
                    }
                } catch (IOException var7) {
                    this.closeEverything(this.SOCKET, this.reader, this.writer);
                }
            }

            return;
        }
    }

    public void privateMessage(String message) {
        int splitPoint = message.indexOf(" ");
        if (splitPoint > 0) {
            String msg_target = message.substring(0, splitPoint);
            String privat_msg = message.substring(splitPoint + 1);
            if (!this.SERVER.checkName(msg_target)) {
                this.SERVER.singleMessage(this.username, this.username + " @" + msg_target + ": " + privat_msg);
                this.SERVER.singleMessage(msg_target, this.username + " @" + msg_target + ": " + privat_msg);
            } else {
                this.SERVER.singleMessage(this.username, "Invalid Username: " + msg_target);
            }
        } else {
            this.SERVER.singleMessage(this.username, "Target Name or Message is missing.");
        }

    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, PrintWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (bufferedWriter != null) {
                bufferedWriter.close();
            }

            if (socket != null) {
                socket.close();
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }
}

