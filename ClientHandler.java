
// packages requeried
import java.io.*;
import java.net.*;

class ClientHandler extends Thread {

    private OutputStream os = null;
    private Socket s = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private final String FILE = "server_bd.sql";
    private BdHandler bdh = null;
    private FileManager fm = null;

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.fm = new FileManager();
        this.bdh = new BdHandler();

    }

    @Override
    public void run() {
        byte[] data;

        while (true) {
            try {

                // export current database from mysql. Returns a file with the database
                bdh.downloadDb();

                // get size of said file
                int size = fm.getFileSize(FILE);

                // read the file to write it in the stream
                data = fm.readFile(s, FILE, size);

                // write it to the stream
                os = s.getOutputStream();
                os.write(data, 0, fm.getFileSize(FILE));

                // print feedback and sleep Thread for 8 seconds
                System.out.println("Sending file (" + data.length + " bytes)");
                Thread.sleep(8000);

            } catch (IOException e) {
                System.out.println("Client disconnected...Continue");
                
                try {
                    os.close();
                    break;
                } catch (IOException e1) {
                   
                   // e1.printStackTrace();
                }
                
                // e.printStackTrace();
            } catch (InterruptedException e) {
                
            } catch(IndexOutOfBoundsException iob){
                
            }
        }

    }
}
