import java.io.IOException;

//package master piece;

public class BdHandler {

    // database name: test_db
    // file downloaded: server-bd.sql
    // file uploaded: copy-sever

    // import file to database
    public void uploadDb() throws IOException {
        Process p = Runtime.getRuntime().exec("cmd /c mysql -h 127.0.0.1 -u root beneficiarios < copy_server.sql");   
    }

    // export database 
    public void downloadDb() throws IOException {
        Process p = Runtime.getRuntime().exec("cmd /c mysqldump -u root beneficiarios > server_bd.sql");   
    }
    
}