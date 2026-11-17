package lab08.network.web;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebReader {
    public static void main(String[] args) {
        String address = args.length > 0 ? args[0] : "http://example.com";
        HttpURLConnection connection = null;
        try {
            URL url = URI.create(address).toURL();
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent", "MCA-Lab08-WebReader/1.0");

            int status = connection.getResponseCode();
            System.out.println("HTTP status: " + status);
            InputStream content = status >= 400
                ? connection.getErrorStream() : connection.getInputStream();
            if (content != null) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(content, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
        } catch (Exception exception) {
            System.err.println("WebReader error: " + exception.getMessage());
            System.err.println("Check internet connectivity, DNS, proxy and URL.");
        } finally {
            if (connection != null) connection.disconnect();
        }
    }
}
