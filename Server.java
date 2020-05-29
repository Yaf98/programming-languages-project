// packages requeried
import java.io.*;
import java.net.*;

// Server class 
public class Server {
    public static void main(String[] args) throws IOException {
               
        // server socket is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        System.out.println("Server started, waiting for connections...");
        
        // infinite loop to be always listenning
        while (true) {
            Socket s = null;

            try {
                // socket server accepting client's connections
                s = ss.accept();

                // new client is connected
                System.out.println("A new client is connected : " + s);

                // obtaining input and output streams for the socket
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // new thread is created for every client connection
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() to run the thread
                t.start();

            } catch (Exception e) {
                
                s.close();
                e.printStackTrace();
            }
        }
    }
}


