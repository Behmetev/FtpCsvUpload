import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FtpCsvUpload {
    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {

        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = args[0];
        String user = args[1];
        String pass = args[2];
        String filePath = "C:\\Users\\User\\Desktop\\sale_basket.xls";
        String uploadPath = "public_html/csv_catalog/spisok-tovarov2.csv";

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
