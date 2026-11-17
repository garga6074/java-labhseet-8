package lab08.network.tcp;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HelloClient {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "127.0.0.1";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;
        try (Socket socket = new Socket(host, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true,
                                                  StandardCharsets.UTF_8)) {
            writer.println("Hello Server");
            System.out.println("Sent: Hello Server");
        } catch (Exception exception) {
            System.err.println("HelloClient error: " + exception.getMessage());
        }
    }
}
