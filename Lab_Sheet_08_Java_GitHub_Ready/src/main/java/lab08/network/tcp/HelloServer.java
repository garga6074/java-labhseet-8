package lab08.network.tcp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/** Waits for a client, reports the connection, and prints its message. */
public class HelloServer {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5000;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("HelloServer listening on port " + port + "...");
            try (Socket client = server.accept();
                 BufferedReader reader = new BufferedReader(
                     new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8))) {
                System.out.println("Client connected");
                String message = reader.readLine();
                if (message != null) {
                    System.out.println("Received from client: " + message);
                }
            }
        } catch (Exception exception) {
            System.err.println("HelloServer error: " + exception.getMessage());
        }
    }
}
