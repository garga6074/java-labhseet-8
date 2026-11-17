package lab08.network.web;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileDownloader {
    public static void main(String[] args) {
        String address = "https://www.example.com/sample.txt";
        Path destination = args.length > 0
            ? Paths.get(args[0]) : Paths.get("downloads", "sample.txt");
        URLConnection connection = null;

        try {
            URL url = URI.create(address).toURL();
            connection = url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(15000);
            connection.setRequestProperty("User-Agent", "MCA-Lab08-FileDownloader/1.0");

            if (connection instanceof HttpURLConnection) {
                HttpURLConnection http = (HttpURLConnection) connection;
                int status = http.getResponseCode();
                if (status < 200 || status >= 300) {
                    throw new IOException("HTTP status " + status
                        + "; the requested sample.txt resource may not exist.");
                }
            }

            Path parent = destination.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            try (InputStream input = connection.getInputStream()) {
                Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("Downloaded to: " + destination.toAbsolutePath());
            System.out.println("Bytes saved: " + Files.size(destination));
        } catch (Exception exception) {
            System.err.println("FileDownloader error: " + exception.getMessage());
            System.err.println("The specified example URL may not host sample.txt.");
        } finally {
            if (connection instanceof HttpURLConnection) {
                ((HttpURLConnection) connection).disconnect();
            }
        }
    }
}
