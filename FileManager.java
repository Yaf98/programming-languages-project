import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class FileManager {

    // to access the file
    private File file = null;

    // to read file
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;

    // to write file
    private FileOutputStream fos = null;
    private BufferedOutputStream bos = null;


    // read in the server and write it to the stream
    private InputStream is = null;

    public int getFileSize(String path) {
        this.file = new File(path);
        return (int) this.file.length();
    }

    // read file
    public byte[] readFile(Socket s, String path, int size) throws IOException {
        
        byte[] data = new byte[(int)file.length()];
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            bis.read(data, 0, (int)file.length());
            
            fis.close();
            bis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        } catch(IndexOutOfBoundsException iob){
            System.out.println("Client disconnected");
        }

        return data;
    }

    // write file to stream
    public void write(Socket s, String path, int size) throws IOException {
        int count;
        byte[] data = new byte[size];
        is = s.getInputStream();
        fos = new FileOutputStream(path);

        while ((count = is.read(data)) > 0) {
            fos.write(data, 0, count);
        }

        fos.close();
        is.close();
    }


}