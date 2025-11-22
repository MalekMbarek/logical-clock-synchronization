import java.io.*;
import java.net.*;

class ClientHandler extends Thread {

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    int id;

    public ClientHandler(Socket socket, int id) throws Exception {
        this.socket = socket;
        this.id = id;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            dos.writeUTF("Welcome! You are client " + id);
            dos.writeUTF("" + id);

            while (true) {
                String flag = dis.readUTF(); // 1 or 0

                if (flag.equals("1")) {
                    int a = Integer.parseInt(dis.readUTF());
                    int b = Integer.parseInt(dis.readUTF());
                    int c = Integer.parseInt(dis.readUTF());

                    // Broadcast immediately
                    Server.broadcast("cn" + id, a, b, c);
                }
            }

        } catch (Exception e) {
            System.out.println("Client " + id + " disconnected");
        }
    }
}
