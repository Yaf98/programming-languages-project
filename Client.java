// Java implementation for a client 
// Save file as Client.java 

import java.io.*;
import java.net.*;
import java.util.Scanner;


// Client class 
public class Client {
	public final static int SOCKET_PORT = 5056;
	public final static int FILE_SIZE = 6022386;
	public  static String SERVER = ""; // will hava ip address

	public static void main(String[] args) throws IOException {
		InputStream is = null;
		BdHandler bdh = new BdHandler();
		OutputStream os =  null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			Scanner scn = new Scanner(System.in);
			System.out.println("Ingresa la dirección IP del server");
			SERVER = scn.nextLine();

			// getting localhost ip
			InetAddress ip = InetAddress.getByName(SERVER);

			// establish the connection with server port 5056
			Socket s = new Socket(ip, SOCKET_PORT);

			// obtaining input and out streams
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());

			// the following loop performs the exchange of
			// information between client and client handler
			while (true) {
				// getting updates every
				System.out.println("Receiving file...");
				byte[] data = new byte[12090];
				is = s.getInputStream();
				is.read(data, 0, data.length);
				///FileWriter writer = new FileWriter("the-file-name.txt");
				os = new FileOutputStream("copy_server.sql");

				// Starts writing the bytes in it
				os.write(data);

				// import database
				bdh.uploadDb();
				
				
			}

		} catch (Exception e) {
			System.out.println("Quitting...\nConnection closed...");
			dis.close();
			dos.close();
			os.close();
			is.close();
		}

	}
}
