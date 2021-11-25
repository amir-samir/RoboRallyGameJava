import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    Scanner scanner;

    public Client(Socket socket) {
        this.scanner = new Scanner(System.in);

        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException var3) {
            this.closeEverything(socket, this.bufferedReader, this.bufferedWriter);
        }

    }

    public static void main(String[] args) {
        try {
            System.out.println("Enter your username for the group chat: ");
            Socket socket = new Socket("localhost", 1234);
            Client client = new Client(socket);
            client.listenForMessage();
            client.sendMessage();
        } catch (Exception var3) {
            var3.printStackTrace();
            System.err.println("Dieser Client konnte nicht erstellt werden.");
        }

    }

    public void sendMessage() {
        while(true) {
            try {
                if (this.socket.isConnected()) {
                    String messageToSend = this.scanner.nextLine();
                    if (messageToSend.equalsIgnoreCase("bye")) {
                        this.bufferedWriter.write(messageToSend);
                        this.bufferedWriter.newLine();
                        this.bufferedWriter.flush();
                        this.closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
                        System.exit(0);
                    }

                    this.bufferedWriter.write(messageToSend);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                    continue;
                }
            } catch (IOException var2) {
                this.closeEverything(this.socket, this.bufferedReader, this.bufferedWriter);
            }

            return;
        }
    }

    public void listenForMessage() {
        (new Thread(() -> {
            while(Client.this.socket.isConnected()) {
                try {
                    String msgFromGroupChat = Client.this.bufferedReader.readLine();
                    if (msgFromGroupChat != null) {
                        System.out.println(msgFromGroupChat);
                    }
                } catch (IOException var3) {
                    Client.this.closeEverything(Client.this.socket, Client.this.bufferedReader, Client.this.bufferedWriter);
                }
            }

        })).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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