import java.net.*;
import java.util.*;

public class Server {
	

    // Store client handlers
    static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(1404);
        System.out.println("Server listening...");

        int id = 1;

        while (true) {
            Socket s = ss.accept();
            System.out.println("Client " + id + " connected");

            ClientHandler ch = new ClientHandler(s, id);
            clients.add(ch);

            ch.start();
            id++;
        }
    }

    // Broadcast message to all clients
    public static synchronized void broadcast(String label, int a, int b, int c) {
        try {
            for (ClientHandler cHandler : clients) {
                cHandler.dos.writeUTF(label);
                cHandler.dos.writeUTF("" + a);
                cHandler.dos.writeUTF("" + b);
                cHandler.dos.writeUTF("" + c);
            }
        } catch (Exception e) {
            System.out.println("Broadcast error");
        }
    }
}
